package teamport.wasteland;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.type.WorldType;
import net.minecraft.core.world.type.WorldTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import teamport.wasteland.core.world.*;
import teamport.wasteland.core.world.biome.BiomeDeadForest;
import teamport.wasteland.core.world.biome.BiomeWasteland;
import teamport.wasteland.core.world.biome.BiomeWastesDesert;
import teamport.wasteland.core.world.biome.BiomeWastesTaiga;
import turniplabs.halplibe.util.GameStartEntrypoint;

public class Wasteland implements ModInitializer, GameStartEntrypoint {
    public static final String MOD_ID = "wasteland";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Biome BIOME_WASTES;
	public static Biome BIOME_DEADFOREST;
	public static Biome BIOME_WASTESTAIGA;
	public static Biome BIOME_WASTESDESERT;
	public static WorldType WASTELAND_WORLD_DEFAULT;
	public static WorldType WASTELAND_WORLD_EXTENDED;

	@Override
	public void onInitialize() {
		LOGGER.info("Wasteland initialized. Stay strong, survivor...");
		new WastelandConfig();
	}

	@Override
	public void beforeGameStart() {
		BIOME_WASTES = Biomes.register("biome.wastes", new BiomeWasteland());
		BIOME_DEADFOREST = Biomes.register("biome.wastes_forest", new BiomeDeadForest());
		BIOME_WASTESTAIGA = Biomes.register("biome.wastes_taiga", new BiomeWastesTaiga());
		BIOME_WASTESDESERT = Biomes.register("biome.wastes_desert", new BiomeWastesDesert());
		WASTELAND_WORLD_DEFAULT = WorldTypes.register("wasteland.world.default", new WorldTypeWasteland("wasteland.world.default"));
		WASTELAND_WORLD_EXTENDED = WorldTypes.register("wasteland.world.extended", new WorldTypeWastelandExtended("wasteland.world.extended"));
		BiomeProviderWasteland.init();
	}

	@Override
	public void afterGameStart() {

	}
}
