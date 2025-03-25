package teamport.wasteland.extra.mixin;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.animal.MobWolf;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import teamport.wasteland.WastelandConfig;
import teamport.wasteland.core.world.WorldTypeWasteland;

import java.util.List;
import java.util.Objects;

@Mixin(value = Mob.class, remap = false)
public abstract class MobMixin extends Entity {
	@Shadow
	public int heartsHalvesLife;

	@Shadow
	@Final
	@NotNull
	public List<WeightedRandomLootObject> mobDrops;

	@Shadow
	public abstract int getHealth();

	@Shadow
	public abstract void setHealthRaw(int health);

	public MobMixin(World world) {
		super(world);
	}

	@Inject(method = "getMaxHealth", at = @At("HEAD"), cancellable = true)
	private void wasteland_setHarderMobHealth(CallbackInfoReturnable<Integer> cir) {
		if (WastelandConfig.cfg.getBoolean("Config.harderMobs")) {
			cir.setReturnValue(40);
		}
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	private void wasteland_harderMobsHealth(World world, CallbackInfo ci) {
		if (WastelandConfig.cfg.getBoolean("Config.harderMobs")) {
			heartsHalvesLife = 40;
			setHealthRaw(40);
		}
	}

	@Inject(method = "onLivingUpdate", at = @At("TAIL"))
	private void wasteland_BadSun(CallbackInfo ci) {
		Mob thisAs = (Mob) (Object) this;

		if (world == null || !(world.getWorldType() instanceof WorldTypeWasteland)) {
			return;
		}

		if (!WastelandConfig.cfg.getBoolean("Config.badSun")) {
			return;
		}

		long daysPassed = world.getWorldTime() / 24000;
		if (daysPassed > 14 && world.isDaytime()) {
			float brightness = getBrightness(1);

			if (brightness <= 0.7F || !world.canBlockSeeTheSky(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z))) {
				return;
			}

			if (random.nextFloat() * 30.0F >= (brightness - 0.4F) * 2) {
				return;
			}

			if (world.weatherManager.getWeatherPower() >= 0.75F) {
				return;
			}

			if (thisAs instanceof Player) {
				Player player = (Player) thisAs;

				if ((player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].getItem() instanceof ItemArmor)
					|| player.gamemode.isImmuneToFire()) {
					return;
				}

				player.remainingFireTicks = 300;
			}

			if (thisAs instanceof MobWolf) {
				MobWolf wolf = (MobWolf) thisAs;

				if (wolf.getArmorMaterial() != null) {
					return;
				}

				wolf.remainingFireTicks = 300;
			}

			remainingFireTicks = 300;
		}
	}
}
