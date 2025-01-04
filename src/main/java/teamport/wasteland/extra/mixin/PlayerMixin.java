package teamport.wasteland.extra.mixin;

import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.weather.Weathers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamport.wasteland.WastelandConfig;
import teamport.wasteland.core.world.WorldTypeWasteland;

@Mixin(value = Player.class, remap = false)
public abstract class PlayerMixin extends Mob {

	@Shadow
	public Gamemode gamemode;

	@Shadow
	public ContainerInventory inventory;

	public PlayerMixin(World world) {
		super(world);
	}

	@Inject(method = "onLivingUpdate", at = @At("TAIL"))
	private void wasteland_playerBadSun(CallbackInfo ci) {
		if (world.getWorldType() instanceof WorldTypeWasteland && WastelandConfig.cfg.getBoolean("Config.badSun")) {
			long daysPassed = world.getWorldTime() / 24000;
			if (daysPassed > 14 && world.isDaytime() && inventory.armorInventory[3] == null && !gamemode.isImmuneToFire()) {
				float brightness = getBrightness(1);
				if (brightness > 0.7F
					&& world.canBlockSeeTheSky(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z))
					&& random.nextFloat() * 30.0F < (brightness - 0.4F) * 2
					&& (world.getCurrentWeather() != Weathers.OVERWORLD_FOG || world.weatherManager.getWeatherPower() < 0.75F)) {
					remainingFireTicks = 300;
				}
			}
		}
	}
}
