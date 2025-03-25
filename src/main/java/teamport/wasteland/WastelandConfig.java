package teamport.wasteland;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import static teamport.wasteland.Wasteland.MOD_ID;

public class WastelandConfig {
	private static final Toml TOML = new Toml("Wasteland TOML Config");
	public static TomlConfigHandler cfg;

	static {
		TOML.addCategory("Config")
			.addEntry("harderMobs", "More health and bigger creeper explosions. WARNING: CREEPERS MAY DELETE ITEMS!", false)
			.addEntry("finiteWater", "No infinite water sources. (4x4 or 1x3)", false)
			.addEntry("badSun", "The sun will deal damage after 14 days have passed. Blocked by intense weather.", false);

		cfg = new TomlConfigHandler(MOD_ID, TOML);
	}
}
