package teamport.wasteland.extra.mixin;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.monster.MobCreeper;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamport.wasteland.WastelandConfig;

@Mixin(value = MobCreeper.class, remap = false)
public abstract class MobCreeperMixin extends Mob {
	@Shadow
	public abstract boolean getPowered();

	public MobCreeperMixin(World world) {
		super(world);
	}

	@Redirect(method = "attackEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/entity/monster/MobCreeper;getPowered()Z"))
	private boolean wasteland_hardCreeper(MobCreeper instance) {
		MobCreeper thisAs = (MobCreeper) (Object) this;
		if (WastelandConfig.cfg.getBoolean("Config.harderMobs")) {
			world.createExplosion(thisAs, x, y, z, getPowered() ? 12 : 6);
			return true;
		}
		return false;
	}
}
