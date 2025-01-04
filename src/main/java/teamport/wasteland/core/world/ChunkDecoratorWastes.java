package teamport.wasteland.core.world;

import net.minecraft.core.block.*;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.ChunkDecorator;
import net.minecraft.core.world.generate.feature.*;
import net.minecraft.core.world.noise.PerlinNoise;
import net.minecraft.core.world.type.WorldTypes;
import teamport.wasteland.Wasteland;
import teamport.wasteland.core.world.generation.*;

import java.util.Random;

public class ChunkDecoratorWastes implements ChunkDecorator {
	private final World world;
	private final PerlinNoise treeDensityNoise;
	private final int treeDensityOverride;

	public ChunkDecoratorWastes(World world) {
		this.world = world;
		this.treeDensityOverride = -1;
		this.treeDensityNoise = new PerlinNoise(world.getRandomSeed(), 8, 74);
	}

	@Override
	public void decorate(Chunk chunk) {
		world.scheduledUpdatesAreImmediate = true;
		int chunkX = chunk.xPosition;
		int chunkZ = chunk.zPosition;
		int minY = world.getWorldType().getMinY();
		int maxY = world.getWorldType().getMaxY();
		int rangeY = maxY + 1 - minY;
		float oreHeightModifier = (float)rangeY / 128.0F;
		BlockLogicSand.fallInstantly = true;
		int x = chunkX * 16;
		int z = chunkZ * 16;
		int y = world.getHeightValue(x + 16, z + 16);
		Biome biome = world.getBlockBiome(x + 16, y, z + 16);
		Random rand = new Random(world.getRandomSeed());
		long l1 = rand.nextLong() / 2L * 2L + 1L;
		long l2 = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed((long)chunkX * l1 + (long)chunkZ * l2 ^ world.getRandomSeed());
		double d;

		// LAKES //
		if (rand.nextInt(4) == 0) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rand.nextInt(rangeY - rangeY / 16) + rangeY / 16);
			int zf = z + rand.nextInt(16) + 8;
			if (yf < minY + rangeY / 2 || rand.nextInt(10) == 0) {
				(new WorldFeatureLake(Blocks.FLUID_LAVA_STILL.id())).place(world, rand, xf, yf, zf);
			}
		}

		// DUNGEONS //
		for(int height = 0; (float)height < 8.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			if (rand.nextInt(2) == 0) {
				(new WorldFeatureDungeon(Blocks.BRICK_STONE.id(), Blocks.BRICK_STONE.id(), null)).place(world, rand, xf, yf, zf);
			} else {
				(new WorldFeatureDungeon(Blocks.COBBLE_STONE.id(), Blocks.COBBLE_STONE_MOSSY.id(), null)).place(world, rand, xf, yf, zf);
			}
		}

		for(int chance = 0; chance < 1; ++chance) {
			int xf = x + rand.nextInt(16) + 8;
			int zf = z + rand.nextInt(16) + 8;
			int yf = world.getHeightValue(xf, zf) - (rand.nextInt(2) + 2);
			if (rand.nextInt(5) == 0) {
				yf -= rand.nextInt(10) + 30;
			}

			if (rand.nextInt(1400) == 0) {
				Random lRand = chunk.getChunkRandom(75644760L);
				(new WorldFeatureLabyrinth()).place(world, lRand, xf, yf, zf);
			}
		}

		byte buildingByte = 0;
		if (biome == Wasteland.biomeWastesCity) {
			buildingByte = 48;
		}

		if (biome == Wasteland.biomeWastes) {
			buildingByte = 32;
		}

		// Generic ruins
		for (int chance = 0; chance < buildingByte; chance++) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			if (rand.nextInt(24) == 0) {
				new WorldFeatureRuins().place(world, rand, xf, yf, zf);
			}
		}

		// Skyscraper
		for (int chance = 0; chance < buildingByte; chance++) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			if (rand.nextInt(48) == 0 && biome == Wasteland.biomeWastesCity) {
				new WorldFeatureSkyscraperOne().place(world, rand, xf, yf, zf);
			}
		}

		// Store
		for (int chance = 0; chance < buildingByte; chance++) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			if (rand.nextInt(56) == 0 && biome == Wasteland.biomeWastesCity) {
				new WorldFeatureStore().place(world, rand, xf, yf, zf);
			}
		}

		// House
		for (int chance = 0; chance < buildingByte; chance++) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			if (rand.nextInt(32) == 0 && biome == Wasteland.biomeWastesCity) {
				new WorldFeatureHouse().place(world, rand, xf, yf, zf);
			}
		}

		// UNDERGROUND //
		for(int height = 0; (float)height < 20.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yz = minY + rand.nextInt(rangeY / 2);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Blocks.DIRT.id(), 32)).place(world, rand, xf, yz, zf);
		}

		for(int height = 0; (float)height < 10.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Blocks.GRAVEL.id(), 32)).place(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < 20.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(BlockLogicOreCoal.variantMap, 16)).place(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < 20.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 2);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(BlockLogicOreIron.variantMap, 8)).place(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < 2.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 4);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(BlockLogicOreGold.variantMap, 8)).place(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < 8.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 8);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(BlockLogicOreRedstone.variantMap, 7)).place(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 8);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(BlockLogicOreDiamond.variantMap, 7)).place(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 2);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(BlockLogicMoss.variantMap, 32)).place(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 8) + rand.nextInt(rangeY / 8);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(BlockLogicOreLapis.variantMap, 6)).place(world, rand, xf, yf, zf);
		}

		// TREES //
		d = 0.5;
		int treeRand = (int)((treeDensityNoise.get((double)x * d, (double)z * d) / (double)8.0F + rand.nextDouble() * (double)4.0F + (double)4.0F) / (double)3.0F);
		int treeDensity = 0;

		if (rand.nextInt(30) == 0) {
			++treeDensity;
		}

		if (biome == Wasteland.biomeWastes) {
			treeDensity = 1;
		}


		if (biome == Wasteland.biomeWastesDesert) {
			treeDensity = 0;
		}

		if (biome == Wasteland.biomeWastesForest) {
			treeDensity += treeRand + 3;
		}

		if (biome == Wasteland.biomewastesTaiga) {
			treeDensity += treeRand + 4;
		}

		if (treeDensityOverride != -1) {
			treeDensity = this.treeDensityOverride;
		}

		try {
			BlockLogicLeavesBase.enableDecay = false;

			for(int chance = 0; chance < treeDensity; ++chance) {
				int xf = x + rand.nextInt(16) + 8;
				int zf = z + rand.nextInt(16) + 8;
				WorldFeature feature = biome.getRandomWorldGenForTrees(rand);
				feature.init(1, 1, 1);

				if (rand.nextInt(3) == 0) {
					feature.place(world, rand, xf, world.getHeightValue(xf, zf), zf);
				}
			}
		} finally {
			BlockLogicLeavesBase.enableDecay = true;
		}

		// PLANTS //
		byte grassByte = 0;
		if (biome == Wasteland.biomewastesTaiga) {
			grassByte = 1;
		}

		for(int chance = 0; chance < grassByte; ++chance) {
			int type = rand.nextInt(3) == 0 ? Blocks.TALLGRASS.id() : Blocks.TALLGRASS_FERN.id();

			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			new WorldFeatureTallGrass(type).place(world, rand, xf, yf, zf);
		}


		byte bushByte = 0;
		if (biome == Wasteland.biomeWastes) {
			bushByte = 1;
		}

		if (biome == Wasteland.biomeWastesDesert) {
			bushByte = 2;
		}

		for (int chance = 0; chance < bushByte; ++chance) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureDeadBush(Blocks.DEADBUSH.id())).place(world, rand, xf, yf, zf);
		}

		byte cactusByte = 0;
		if (biome == Wasteland.biomeWastesDesert) {
			cactusByte += 5;
		}

		for (int chance = 0; chance < cactusByte; chance++) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			new WorldFeatureCactus().place(world, rand, xf, yf, zf);
		}

		if (rand.nextInt(4) == 0) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureFlowers(Blocks.MUSHROOM_BROWN.id(), 64, false)).place(world, rand, xf, yf, zf);
		}

		if (rand.nextInt(8) == 0) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureFlowers(Blocks.MUSHROOM_RED.id(), 64, false)).place(world, rand, xf, yf, zf);
		}

		// WASTES FIRE //
		byte fireByte = 3;

		if (biome == Wasteland.biomewastesTaiga) {
			fireByte = 0;
		}

		for (int chance = 0; chance < fireByte; chance++) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			new WorldFeatureWastesFire().place(world, rand, xf, yf, zf);
		}

		// RANDOM FLUIDS //
		for(int chance = 0; chance < 50; ++chance) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rand.nextInt(rangeY - rangeY / 16) + rangeY / 16) / 2;
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureLiquid(Blocks.FLUID_WATER_FLOWING.id())).place(world, rand, xf, yf, zf);
		}

		for(int chance = 0; chance < 20; ++chance) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rand.nextInt(rand.nextInt(rangeY - rangeY / 8) + rangeY / 16) + rangeY / 16);
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureLiquid(Blocks.FLUID_LAVA_FLOWING.id())).place(world, rand, xf, yf, zf);
		}

		int oceanY = world.getWorldType().getOceanY();

		for(int dx = x + 8; dx < x + 8 + 16; ++dx) {
			for(int dz = z + 8; dz < z + 8 + 16; ++dz) {
				int dy = world.getHeightValue(dx, dz);
				Biome localBiome = world.getBlockBiome(dx, dy, dz);
				if ((localBiome.hasSurfaceSnow() || world.worldType == WorldTypes.OVERWORLD_WINTER) && dy > 0 && dy < world.getHeightBlocks() && world.isAirBlock(dx, dy, dz) && world.getBlockMaterial(dx, dy - 1, dz).blocksMotion()) {
					world.setBlockWithNotify(dx, dy, dz, Blocks.LAYER_SNOW.id());
				}

				if ((localBiome.hasSurfaceSnow() || world.worldType == WorldTypes.OVERWORLD_WINTER) && (world.getBlockId(dx, oceanY - 1, dz) == Blocks.FLUID_WATER_STILL.id() || world.getBlockId(dx, oceanY - 1, dz) == Blocks.FLUID_WATER_FLOWING.id())) {
					world.setBlockWithNotify(dx, oceanY - 1, dz, Blocks.ICE.id());
				}
			}
		}

		BlockLogicSand.fallInstantly = false;
		world.scheduledUpdatesAreImmediate = false;
	}
}
