package teamport.wasteland;

import turniplabs.halplibe.util.toml.Toml;

public class WastelandConfig {

	public WastelandConfig() {
		Toml properties = new Toml("Tropicraft's TOML Config");


		properties.addCategory("Config")
			.addEntry("HarderMobs", false)
			.addEntry("FiniteWater", false);
	}
}
