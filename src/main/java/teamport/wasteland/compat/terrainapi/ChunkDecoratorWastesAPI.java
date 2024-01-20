package teamport.wasteland.compat.terrainapi;

import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.noise.PerlinNoise;
import org.jetbrains.annotations.ApiStatus;
import useless.terrainapi.config.ConfigManager;
import useless.terrainapi.generation.ChunkDecoratorAPI;
import useless.terrainapi.generation.Parameters;
import useless.terrainapi.generation.StructureFeatures;
import useless.terrainapi.generation.overworld.OverworldBiomeFeatures;
import useless.terrainapi.generation.overworld.OverworldOreFeatures;
import useless.terrainapi.generation.overworld.OverworldRandomFeatures;

import java.util.Random;
import java.util.function.Function;

public class ChunkDecoratorWastesAPI extends ChunkDecoratorAPI {
	public static TerrainWastesConfig config = ConfigManager.getConfig("wasteland", TerrainWastesConfig.class);
	public final PerlinNoise treeDensityNoise;
	public final int treeDensityOverride;
	public static StructureFeatures structureFeatures = new StructureFeatures();
	public static OverworldOreFeatures oreFeatures = new OverworldOreFeatures(config);
	public static OverworldRandomFeatures randomFeatures = new OverworldRandomFeatures();
	public static OverworldBiomeFeatures biomeFeatures = new OverworldBiomeFeatures();

	public ChunkDecoratorWastesAPI(World world, int treeDensityOverride) {
		super(world);
		this.treeDensityOverride = treeDensityOverride;
		this.treeDensityNoise = new PerlinNoise(world.getRandomSeed(), 8, 74);
	}

	public ChunkDecoratorWastesAPI(World world) {
		this(world, -1);
	}


	@ApiStatus.Internal
	public void decorateAPI() {
		int xCoord = this.parameterBase.chunk.xPosition * 16;
		int zCoord = this.parameterBase.chunk.zPosition * 16;
		this.generateStructures(this.parameterBase.biome, this.parameterBase.chunk, this.parameterBase.random);
		this.generateOreFeatures(this.parameterBase.biome, xCoord, zCoord, this.parameterBase.random, this.parameterBase.chunk);
		this.generateBiomeFeature(this.parameterBase.biome, xCoord, zCoord, this.parameterBase.random, this.parameterBase.chunk);
		this.generateRandomFeatures(this.parameterBase.biome, xCoord, zCoord, this.parameterBase.random, this.parameterBase.chunk);
		this.freezeSurface(xCoord, zCoord);
	}

	@ApiStatus.Internal
	public void generateStructures(Biome biome, Chunk chunk, Random random) {
		int featureSize = structureFeatures.featureFunctionList.size();

		for(int i = 0; i < featureSize; ++i) {
			((Function)structureFeatures.featureFunctionList.get(i)).apply(new Parameters(this.parameterBase, structureFeatures.featureParametersList.get(i)));
		}
	}

	@ApiStatus.Internal
	public void generateOreFeatures(Biome biome, int x, int z, Random random, Chunk chunk) {
		int featureSize = oreFeatures.featureFunctionsList.size();

		for(int i = 0; i < featureSize; ++i) {
			int density = (int) ((Function)oreFeatures.densityFunctionsList.get(i))
				.apply(new Parameters(this.parameterBase, oreFeatures.densityParametersList.get(i)));
			float startingRange = oreFeatures.startingRangeList.get(i);
			float endingRange = oreFeatures.endingRangeList.get(i);
			this.generateWithChancesUnderground(
				(Function)oreFeatures.featureFunctionsList.get(i),
				new Parameters(this.parameterBase, oreFeatures.featureParametersList.get(i)),
				(float)density,
				(int)(startingRange * (float)this.rangeY),
				(int)(endingRange * (float)this.rangeY),
				x,
				z,
				random
			);
		}
	}

	@ApiStatus.Internal
	public void generateRandomFeatures(Biome biome, int x, int z, Random random, Chunk chunk) {
		int featureSize = randomFeatures.featureFunctionsList.size();

		for(int i = 0; i < featureSize; ++i) {
			if (random.nextInt(randomFeatures.inverseProbabilityList.get(i)) == 0) {
				Function<Parameters, WorldFeature> featureFunction = (Function)randomFeatures.featureFunctionsList.get(i);
				int density = (int) ((Function)randomFeatures.densityFunctionsList.get(i))
					.apply(new Parameters(this.parameterBase, randomFeatures.densityParametersList.get(i)));
				float startingRange = randomFeatures.startingRangeList.get(i);
				float endingRange = randomFeatures.endingRangeList.get(i);
				if ((!(-1.01 <= (double)startingRange) || !((double)startingRange <= -0.99))
					&& (!(-1.01 <= (double)endingRange) || !((double)endingRange <= -0.99))) {
					this.generateWithChancesUnderground(
						featureFunction,
						new Parameters(this.parameterBase, randomFeatures.featureParametersList.get(i)),
						(float)density,
						(int)(startingRange * (float)this.rangeY),
						(int)(endingRange * (float)this.rangeY),
						x,
						z,
						8,
						8,
						random
					);
				} else {
					this.generateWithChancesSurface(
						featureFunction, new Parameters(this.parameterBase, randomFeatures.featureParametersList.get(i)), (float)density, x, z, 8, 8, random
					);
				}
			}
		}
	}

	@ApiStatus.Internal
	public void generateBiomeFeature(Biome biome, int x, int z, Random random, Chunk chunk) {
		int featureSize = biomeFeatures.featureFunctionsList.size();

		for(int i = 0; i < featureSize; ++i) {
			Function<Parameters, WorldFeature> featureFunction = (Function)biomeFeatures.featureFunctionsList.get(i);
			int density = (int) ((Function)biomeFeatures.densityFunctionsList.get(i))
				.apply(new Parameters(this.parameterBase, biomeFeatures.densityParametersList.get(i)));
			float startingRange = biomeFeatures.startingRangeList.get(i);
			float endingRange = biomeFeatures.endingRangeList.get(i);
			if ((!(-1.01 <= (double)startingRange) || !((double)startingRange <= -0.99))
				&& (!(-1.01 <= (double)endingRange) || !((double)endingRange <= -0.99))) {
				this.generateWithChancesUnderground(
					featureFunction,
					new Parameters(this.parameterBase, biomeFeatures.featureParametersList.get(i)),
					(float)density,
					(int)(startingRange * (float)this.rangeY),
					(int)(endingRange * (float)this.rangeY),
					x,
					z,
					8,
					8,
					random
				);
			} else {
				this.generateWithChancesSurface(
					featureFunction, new Parameters(this.parameterBase, biomeFeatures.featureParametersList.get(i)), (float)density, x, z, 8, 8, random
				);
			}
		}
	}
}
