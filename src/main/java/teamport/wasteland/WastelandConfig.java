package teamport.wasteland;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import static teamport.wasteland.Wasteland.MOD_ID;

public class WastelandConfig {
	private static final Toml TOML = new Toml("Wasteland TOML Config");
	public static TomlConfigHandler cfg;

	static {
		TOML.addCategory("Config")
			.addEntry("harderMobs", false)
			.addEntry("finiteWater", false)
			.addEntry("badSun", false);

		cfg = new TomlConfigHandler(MOD_ID, TOML);
	}
}
