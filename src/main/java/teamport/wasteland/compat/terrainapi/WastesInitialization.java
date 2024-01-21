package teamport.wasteland.compat.terrainapi;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.generate.feature.WorldFeatureCactus;
import net.minecraft.core.world.generate.feature.WorldFeatureClay;
import net.minecraft.core.world.generate.feature.WorldFeatureDeadBush;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancy;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeTaigaBushy;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeTaigaTall;
import teamport.wasteland.Wasteland;
import teamport.wasteland.world.generation.WorldFeatureRuins;
import teamport.wasteland.world.generation.WorldFeatureWastesFire;
import useless.terrainapi.generation.StructureFeatures;
import useless.terrainapi.generation.overworld.OverworldBiomeFeatures;
import useless.terrainapi.generation.overworld.OverworldFunctions;
import useless.terrainapi.generation.overworld.OverworldOreFeatures;
import useless.terrainapi.generation.overworld.OverworldRandomFeatures;
import useless.terrainapi.initialization.BaseInitialization;

import java.util.Random;

import static teamport.wasteland.Wasteland.MOD_ID;

public class WastesInitialization extends BaseInitialization {
	private final Random treeRand = new Random();
	private static final TerrainWastesConfig config = ChunkDecoratorWastesAPI.config;
	public static final StructureFeatures structureFeatures = ChunkDecoratorWastesAPI.structureFeatures;
	public static final OverworldOreFeatures oreFeatures = ChunkDecoratorWastesAPI.oreFeatures;
	public static final OverworldRandomFeatures randomFeatures = ChunkDecoratorWastesAPI.randomFeatures;
	public static final OverworldBiomeFeatures biomeFeatures = ChunkDecoratorWastesAPI.biomeFeatures;

	@Override
	protected void initValues() {
		config.setOreValues(MOD_ID, Block.blockClay, 32, 20, 1.0F);
		config.addTreeDensity(Wasteland.BIOME_DEADFOREST, 3);
		config.addTreeDensity(Wasteland.BIOME_WASTESTAIGA, 5);

	}

	@Override
	protected void initStructure() {
		structureFeatures.addFeature(OverworldFunctions::generateLavaLakeFeature, null);
		structureFeatures.addFeature(OverworldFunctions::generateDungeons, null);
		structureFeatures.addFeature(OverworldFunctions::generateLabyrinths, null);
		structureFeatures.addFeature(OverworldFunctions::generateRandomFluid, new Object[]{20, Block.fluidLavaFlowing.id});
		structureFeatures.addFeature(OverworldFunctions::generateRandomFluid, new Object[]{1, Block.fluidWaterFlowing.id});
	}

	@Override
	protected void initOre() {
		String currentBlock = Block.blockClay.getKey();
		oreFeatures.addFeature(
			new WorldFeatureClay(config.clusterSize.get(currentBlock)),
			config.chancesPerChunk.get(currentBlock),
			config.verticalStartingRange.get(currentBlock)
		);
		oreFeatures.addManagedOreFeature(MOD_ID, Block.dirt, 32, 20, 1.0F, false);
		oreFeatures.addManagedOreFeature(MOD_ID, Block.gravel, 32, 10, 1.0F, false);
		oreFeatures.addManagedOreFeature(MOD_ID, Block.oreCoalStone, 16, 20, 1.0F, true);
		oreFeatures.addManagedOreFeature(MOD_ID, Block.oreIronStone, 8, 20, 0.5F, true);
		oreFeatures.addManagedOreFeature(MOD_ID, Block.oreGoldStone, 8, 2, 0.25F, true);
		oreFeatures.addManagedOreFeature(MOD_ID, Block.oreRedstoneStone, 7, 8, 0.125F, true);
		oreFeatures.addManagedOreFeature(MOD_ID, Block.oreDiamondStone, 7, 1, 0.125F, true);
		oreFeatures.addManagedOreFeature(MOD_ID, Block.oreLapisStone, 6, 1, 0.125F, true);
	}

	@Override
	protected void initRandom() {
		randomFeatures.addFeatureSurface(new WorldFeatureRuins(), 64);
		randomFeatures.addFeatureSurface(new WorldFeatureWastesFire(), 8);
		randomFeatures.addFeature(new WorldFeatureFlowers(Block.mushroomBrown.id), 4, 1.0F);
		randomFeatures.addFeature(new WorldFeatureFlowers(Block.mushroomRed.id), 8, 1.0F);
		randomFeatures.addFeatureSurface(new WorldFeatureTree(0, Block.logOak.id, treeRand.nextInt(7) + 4), 16);
		randomFeatures.addFeatureSurface(new WorldFeatureTreeFancy(0, Block.logOak.id, treeRand.nextInt(14) + 4), 16);
		randomFeatures.addFeatureSurface(new WorldFeatureDeadBush(Block.deadbush.id), 8);
	}

	@Override
	protected void initBiome() {
		biomeFeatures.addFeature(new WorldFeatureTreeFancy(0, Block.logOak.id, treeRand.nextInt(28) + 4), 2.0F, 5, new Biome[]{Wasteland.BIOME_DEADFOREST});
		biomeFeatures.addFeature(new WorldFeatureTreeTaigaTall(Block.leavesPine.id, Block.logPine.id), 1.0F, 5, new Biome[]{Wasteland.BIOME_WASTESTAIGA});
		biomeFeatures.addFeature(new WorldFeatureTreeTaigaBushy(Block.leavesPine.id, Block.logPine.id), 1.0F, 5, new Biome[]{Wasteland.BIOME_WASTESTAIGA});
		biomeFeatures.addFeature(new WorldFeatureCactus(), 1.0F, 10, new Biome[]{Wasteland.BIOME_WASTESDESERT});
	}
}
