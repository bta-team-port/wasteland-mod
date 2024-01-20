package teamport.wasteland.world;

import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.provider.BiomeProvider;
import net.minecraft.core.world.config.season.SeasonConfigCycle;
import net.minecraft.core.world.generate.chunk.ChunkGenerator;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.type.WorldTypeOverworld;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.wind.WindManagerGeneric;

public class WorldTypeWasteland extends WorldTypeOverworld {
	public WorldTypeWasteland(String languageKey) {
		super(languageKey,
			Weather.overworldClear,
			new WindManagerGeneric(), SeasonConfigCycle.builder()
			.withSeasonInCycle(Seasons.OVERWORLD_SPRING, 14)
			.withSeasonInCycle(Seasons.OVERWORLD_SUMMER, 14)
			.withSeasonInCycle(Seasons.OVERWORLD_FALL, 14)
			.withSeasonInCycle(Seasons.OVERWORLD_WINTER, 14)
			.build());
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
	public int getOceanBlock() {
		return 0;
	}

	@Override
	public BiomeProvider createBiomeProvider(World world) {
		return new BiomeProviderWasteland(world.getRandomSeed(), this);
	}

	@Override
	public float getCloudHeight() {
		return 192;
	}

	@Override
	public ChunkGenerator createChunkGenerator(World world) {
		return new ChunkGeneratorWastes(world);
	}
}
