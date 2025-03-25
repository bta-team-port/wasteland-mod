package teamport.wasteland.extra.mixin;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.monster.MobCreeper;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamport.wasteland.WastelandConfig;

@Mixin(value = MobCreeper.class, remap = false)
public abstract class MobCreeperMixin extends Mob {
	@Shadow
	public abstract boolean getPowered();

	public MobCreeperMixin(World world) {
		super(world);
	}

	@Inject(method = "attackEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/monster/MobCreeper;getPowered()Z"), cancellable = true)
	private void wasteland_hardCreeper(Entity entity, float distance, CallbackInfo ci) {
		if (WastelandConfig.cfg.getBoolean("Config.harderMobs")) {
			MobCreeper creeper = (MobCreeper) (Object) this;
			
			world.createExplosion(creeper, x, y, z, getPowered() ? 9 : 4.5f);
			remove();
			ci.cancel();
		}
	}
}
