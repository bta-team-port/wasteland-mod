package teamport.wasteland.mixin;

import net.minecraft.core.entity.EntityPathfinder;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.monster.IEnemy;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamport.wasteland.WastelandConfig;

@Mixin(value = EntityMonster.class, remap = false)
public abstract class EntityMonsterMixin extends EntityPathfinder implements IEnemy {
	public EntityMonsterMixin(World world) {
		super(world);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	private void wasteland_harderMobsHealth(World world, CallbackInfo ci) {
		if (WastelandConfig.cfg.getBoolean("Config.HarderMobs")) this.health = 40;
	}
}
