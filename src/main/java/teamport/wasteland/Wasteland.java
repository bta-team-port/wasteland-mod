package teamport.wasteland;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.type.WorldType;
import net.minecraft.core.world.type.WorldTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import teamport.wasteland.core.world.*;
import teamport.wasteland.core.world.biome.*;
import turniplabs.halplibe.util.GameStartEntrypoint;

public class Wasteland implements ModInitializer, GameStartEntrypoint {
    public static final String MOD_ID = "wasteland";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Biome biomeWastes;
	public static Biome biomeWastesForest;
	public static Biome biomewastesTaiga;
	public static Biome biomeWastesDesert;
	public static Biome biomeWastesCity;
	public static WorldType worldType_Wasteland;
	public static WorldType worldType_Wasteland_Extended;

	@Override
	public void onInitialize() {
		LOGGER.info("Wasteland initialized. Stay strong, survivor...");
		new WastelandConfig();
	}

	@Override
	public void beforeGameStart() {
		biomeWastes = Biomes.register("biome.wastes", new BiomeWasteland());
		biomeWastesForest = Biomes.register("biome.wastes_forest", new BiomeWastesForest());
		biomewastesTaiga = Biomes.register("biome.wastes_taiga", new BiomeWastesTaiga());
		biomeWastesDesert = Biomes.register("biome.wastes_desert", new BiomeWastesDesert());
		biomeWastesCity = Biomes.register("biome.wastes_city", new BiomeWastesCity());
		worldType_Wasteland = WorldTypes.register("wasteland.world.default", new WorldTypeWasteland("wasteland.world.default"));
		worldType_Wasteland_Extended = WorldTypes.register("wasteland.world.extended", new WorldTypeWastelandExtended("wasteland.world.extended"));
		BiomeProviderWasteland.init();
	}

	@Override
	public void afterGameStart() {
	}
}
