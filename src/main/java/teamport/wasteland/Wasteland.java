package teamport.wasteland;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.type.WorldType;
import net.minecraft.core.world.type.WorldTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import teamport.wasteland.world.*;
import teamport.wasteland.world.biome.BiomeDeadForest;
import teamport.wasteland.world.biome.BiomeWasteland;
import teamport.wasteland.world.biome.BiomeWastesDesert;
import teamport.wasteland.world.biome.BiomeWastesTaiga;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;

public class Wasteland implements GameStartEntrypoint, ClientStartEntrypoint {
    public static final String MOD_ID = "wasteland";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Biome BIOME_WASTES;
	public static Biome BIOME_DEADFOREST;
	public static Biome BIOME_WASTESTAIGA;
	public static Biome BIOME_WASTESDESERT;
	public static WorldType WASTELAND_WORLD_DEFAULT;
	public static WorldType WASTELAND_WORLD_EXTENDED;

	@Override
	public void beforeGameStart() {
		BIOME_WASTES = Biomes.register("wasteland.biome.wastes", new BiomeWasteland());
		BIOME_DEADFOREST = Biomes.register("wasteland.biomes.forest", new BiomeDeadForest());
		BIOME_WASTESTAIGA = Biomes.register("wasteland.biomes.taiga", new BiomeWastesTaiga());
		BIOME_WASTESDESERT = Biomes.register("wasteland.biomes.desert", new BiomeWastesDesert());
		WASTELAND_WORLD_DEFAULT = WorldTypes.register("wasteland.world.default", new WorldTypeWasteland("wasteland.world.default"));
		WASTELAND_WORLD_EXTENDED = WorldTypes.register("wasteland.world.extended", new WorldTypeWastelandExtended("wasteland.world.extended"));
		BiomeProviderWasteland.init();

		if (WastelandConfig.cfg == null) WastelandConfig.writeConfig();
		else WastelandConfig.cfg.loadConfig();
		LOGGER.info("Wasteland initialized. Stay strong, survivor...");
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void beforeClientStart() {

	}

	@Override
	public void afterClientStart() {

	}
}
