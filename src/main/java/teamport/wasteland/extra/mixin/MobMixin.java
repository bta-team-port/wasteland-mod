package teamport.wasteland.extra.mixin;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamport.wasteland.WastelandConfig;

@Mixin(value = Mob.class, remap = false)
public abstract class MobMixin extends Entity {
	@Shadow
	public int heartsHalvesLife;

	public MobMixin(World world) {
		super(world);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	private void wasteland_harderMobsHealth(World world, CallbackInfo ci) {
		if (WastelandConfig.cfg.getBoolean("Config.harderMobs")) heartsHalvesLife = 40;
	}
}
