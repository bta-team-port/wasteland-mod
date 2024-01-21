package teamport.wasteland;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import static teamport.wasteland.Wasteland.MOD_ID;

public class WastelandConfig {
	public static TomlConfigHandler cfg;

	static {
		Toml properties = new Toml("Wasteland's TOML Config");

		properties.addCategory("Config")
			.addEntry("HarderMobs", false)
			.addEntry("FiniteWater", false)
			.addEntry("BadSun", false);

		cfg = new TomlConfigHandler(MOD_ID, properties);
	}

	protected static void writeConfig() {
		cfg.writeConfig();
	}
}
