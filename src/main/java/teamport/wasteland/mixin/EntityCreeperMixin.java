package teamport.wasteland.mixin;

import net.minecraft.core.entity.monster.EntityCreeper;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamport.wasteland.WastelandConfig;

@Mixin(value = EntityCreeper.class, remap = false)
public abstract class EntityCreeperMixin extends EntityMonster {
	@Shadow
	public abstract boolean getPowered();

	public EntityCreeperMixin(World world) {
		super(world);
	}

	@Redirect(method = "attackEntity", at = @At(value = "INVOKE", target = "net/minecraft/core/entity/monster/EntityCreeper.getPowered ()Z"))
	private boolean wasteland_hardCreeper(EntityCreeper instance) {
		if (WastelandConfig.cfg.getBoolean("Config.HarderMobs")) {
			if (getPowered()) {
				this.world.createExplosion(this, this.x, this.y, this.z, 12.0F);
			} else {
				this.world.createExplosion(this, this.x, this.y, this.z, 6.0F);
			}
			return true;
		}
		return false;
	}
}
