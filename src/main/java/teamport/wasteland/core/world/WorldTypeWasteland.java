package teamport.wasteland.core.world;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.provider.BiomeProvider;
import net.minecraft.core.world.config.season.SeasonConfig;
import net.minecraft.core.world.generate.chunk.ChunkGenerator;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.type.overworld.WorldTypeOverworld;
import net.minecraft.core.world.weather.Weathers;

public class WorldTypeWasteland extends WorldTypeOverworld {
	public WorldTypeWasteland(Properties properties) {
		super(properties);
	}

	public static Properties defaultProperties(String translationKey) {
		return Properties.of(translationKey)
			.defaultWeather(Weathers.OVERWORLD_FOG)
			.brightnessRamp(createLightRamp())
			.seasonConfig(SeasonConfig.builder().withSeasonInCycle(Seasons.OVERWORLD_SPRING, 14)
				.withSeasonInCycle(Seasons.OVERWORLD_SUMMER, 14)
				.withSeasonInCycle(Seasons.OVERWORLD_FALL, 14)
				.withSeasonInCycle(Seasons.OVERWORLD_WINTER, 14)
				.build()).oceanBlock(null)
			.fillerBlock(Blocks.STONE)
			.allowRespawn();
	}

	@Override
	public int getMinY() {
		return 0;
	}

	@Override
	public int getMaxY() {
		return 128;
	}

	@Override
	public int getOceanY() {
		return 64;
	}

	@Override
	public BiomeProvider createBiomeProvider(World world) {
		return new BiomeProviderWasteland(world.getRandomSeed(), this);
	}

	@Override
	public ChunkGenerator createChunkGenerator(World world) {
		return new ChunkGeneratorWastes(world);
	}
}
