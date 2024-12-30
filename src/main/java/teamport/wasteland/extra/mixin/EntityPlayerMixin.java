package teamport.wasteland.extra.mixin;

import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.weather.Weather;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import teamport.wasteland.WastelandConfig;
import teamport.wasteland.core.world.WorldTypeWasteland;

@Mixin(value = EntityPlayer.class, remap = false)
public abstract class EntityPlayerMixin extends EntityLiving {
	@Shadow
	public InventoryPlayer inventory;

	@Shadow
	public Gamemode gamemode;

	public EntityPlayerMixin(World world) {
		super(world);
	}

	@Inject(method = "onLivingUpdate", at = @At("TAIL"))
	private void wasteland_playerBadSun(CallbackInfo ci) {
		if (world.getWorldType() instanceof WorldTypeWasteland && WastelandConfig.cfg.getBoolean("Config.badSun")) {
			long daysPassed = world.getWorldTime() / 24000;
			if (daysPassed > 14 && world.isDaytime() && inventory.armorInventory[3] == null && !gamemode.isImmuneToFire()) {
				float brightness = getBrightness(1.0F);
				if (brightness > 0.5F
					&& world.canBlockSeeTheSky(MathHelper.floor_double(x), MathHelper.floor_double(y), MathHelper.floor_double(z))
					&& random.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F
					&& (world.getCurrentWeather() != Weather.overworldFog || world.weatherManager.getWeatherPower() < 0.75F)) {
					remainingFireTicks = 300;
				}
			}
		}
	}
}
