package teamport.wasteland.core.world;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLeavesBase;
import net.minecraft.core.block.BlockSand;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.ChunkDecorator;
import net.minecraft.core.world.generate.feature.*;
import net.minecraft.core.world.noise.PerlinNoise;
import net.minecraft.core.world.type.WorldTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import teamport.wasteland.Wasteland;
import teamport.wasteland.core.world.generation.WorldFeatureRuins;
import teamport.wasteland.core.world.generation.WorldFeatureWastesFire;

import java.util.Random;

public class ChunkDecoratorWastes implements ChunkDecorator {
	private static final Logger log = LoggerFactory.getLogger(ChunkDecoratorWastes.class);
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
		BlockSand.fallInstantly = true;
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
				(new WorldFeatureLake(Block.fluidLavaStill.id)).generate(world, rand, xf, yf, zf);
			}
		}

		// DUNGEONS //
		for(int height = 0; (float)height < 8.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			if (rand.nextInt(2) == 0) {
				(new WorldFeatureDungeon(Block.brickStone.id, Block.brickStone.id, null)).generate(world, rand, xf, yf, zf);
			} else {
				(new WorldFeatureDungeon(Block.cobbleStone.id, Block.cobbleStoneMossy.id, null)).generate(world, rand, xf, yf, zf);
			}
		}

		for(int chance = 0; chance < 1; ++chance) {
			int xf = x + rand.nextInt(16) + 8;
			int zf = z + rand.nextInt(16) + 8;
			int yf = world.getHeightValue(xf, zf) - (rand.nextInt(2) + 2);
			if (rand.nextInt(5) == 0) {
				yf -= rand.nextInt(10) + 30;
			}

			if (rand.nextInt(700) == 0) {
				Random lRand = chunk.getChunkRandom(75644760L);
				(new WorldFeatureLabyrinth()).generate(world, lRand, xf, yf, zf);
			}
		}

		for (int chance = 0; chance < 32; chance++) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			if (rand.nextInt(32) == 0) {
				new WorldFeatureRuins().generate(world, rand, xf, yf, zf);
			}
		}

		// UNDERGROUND //
		for(int height = 0; (float)height < 20.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yz = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Block.dirt.id, 32, false)).generate(world, rand, xf, yz, zf);
		}

		for(int height = 0; (float)height < 10.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Block.gravel.id, 32, false)).generate(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < 20.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Block.oreCoalStone.id, 16, true)).generate(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < 20.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 2);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Block.oreIronStone.id, 8, true)).generate(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < 2.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 4);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Block.oreGoldStone.id, 8, true)).generate(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < 8.0F * oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 8);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Block.oreRedstoneStone.id, 7, true)).generate(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 8);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Block.oreDiamondStone.id, 7, true)).generate(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 2);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Block.mossStone.id, 32, true)).generate(world, rand, xf, yf, zf);
		}

		for(int height = 0; (float)height < oreHeightModifier; ++height) {
			int xf = x + rand.nextInt(16);
			int yf = minY + rand.nextInt(rangeY / 8) + rand.nextInt(rangeY / 8);
			int zf = z + rand.nextInt(16);
			(new WorldFeatureOre(Block.oreLapisStone.id, 6, true)).generate(world, rand, xf, yf, zf);
		}

		// TREES //
		d = 0.5;
		int treeRand = (int)((treeDensityNoise.get((double)x * d, (double)z * d) / (double)8.0F + rand.nextDouble() * (double)4.0F + (double)4.0F) / (double)3.0F);
		int treeDensity = 0;

		if (rand.nextInt(30) == 0) {
			++treeDensity;
		}

		if (biome == Wasteland.BIOME_WASTES) {
			treeDensity = 1;
		}


		if (biome == Wasteland.BIOME_WASTESDESERT) {
			treeDensity = 0;
		}

		if (biome == Wasteland.BIOME_WASTESFOREST) {
			treeDensity += treeRand + 3;
		}

		if (biome == Wasteland.BIOME_WASTESTAIGA) {
			treeDensity += treeRand + 4;
		}

		if (treeDensityOverride != -1) {
			treeDensity = this.treeDensityOverride;
		}

		try {
			BlockLeavesBase.enableDecay = false;

			for(int chance = 0; chance < treeDensity; ++chance) {
				int xf = x + rand.nextInt(16) + 8;
				int zf = z + rand.nextInt(16) + 8;
				WorldFeature feature = biome.getRandomWorldGenForTrees(rand);
				feature.func_517_a(1, 1, 1);

				if (rand.nextInt(3) == 0) {
					feature.generate(world, rand, xf, world.getHeightValue(xf, zf), zf);
				}
			}
		} finally {
			BlockLeavesBase.enableDecay = true;
		}

		// PLANTS //
		byte grassByte = 0;
		if (biome == Wasteland.BIOME_WASTESTAIGA) {
			grassByte = 1;
		}

		for(int chance = 0; chance < grassByte; ++chance) {
			int type = rand.nextInt(3) == 0 ? Block.tallgrass.id : Block.tallgrassFern.id;

			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			new WorldFeatureTallGrass(type).generate(world, rand, xf, yf, zf);
		}


		byte bushByte = 0;
		if (biome == Wasteland.BIOME_WASTES) {
			bushByte = 1;
		}

		if (biome == Wasteland.BIOME_WASTESDESERT) {
			bushByte = 2;
		}

		for (int chance = 0; chance < bushByte; ++chance) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureDeadBush(Block.deadbush.id)).generate(world, rand, xf, yf, zf);
		}

		byte cactusByte = 0;
		if (biome == Wasteland.BIOME_WASTESDESERT) {
			cactusByte += 5;
		}

		for (int chance = 0; chance < cactusByte; chance++) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			new WorldFeatureCactus().generate(world, rand, xf, yf, zf);
		}

		if (rand.nextInt(4) == 0) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureFlowers(Block.mushroomBrown.id)).generate(world, rand, xf, yf, zf);
		}

		if (rand.nextInt(8) == 0) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureFlowers(Block.mushroomRed.id)).generate(world, rand, xf, yf, zf);
		}

		// WASTES FIRE //
		byte fireByte = 3;

		if (biome == Wasteland.BIOME_WASTESTAIGA) {
			fireByte = 0;
		}

		for (int chance = 0; chance < fireByte; chance++) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rangeY);
			int zf = z + rand.nextInt(16) + 8;
			new WorldFeatureWastesFire().generate(world, rand, xf, yf, zf);
		}

		// RANDOM FLUIDS //
		for(int chance = 0; chance < 50; ++chance) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rand.nextInt(rangeY - rangeY / 16) + rangeY / 16) / 2;
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureLiquid(Block.fluidWaterFlowing.id)).generate(world, rand, xf, yf, zf);
		}

		for(int chance = 0; chance < 20; ++chance) {
			int xf = x + rand.nextInt(16) + 8;
			int yf = minY + rand.nextInt(rand.nextInt(rand.nextInt(rangeY - rangeY / 8) + rangeY / 16) + rangeY / 16);
			int zf = z + rand.nextInt(16) + 8;
			(new WorldFeatureLiquid(Block.fluidLavaFlowing.id)).generate(world, rand, xf, yf, zf);
		}

		int oceanY = world.getWorldType().getOceanY();

		for(int dx = x + 8; dx < x + 8 + 16; ++dx) {
			for(int dz = z + 8; dz < z + 8 + 16; ++dz) {
				int dy = world.getHeightValue(dx, dz);
				Biome localBiome = world.getBlockBiome(dx, dy, dz);
				if ((localBiome.hasSurfaceSnow() || world.worldType == WorldTypes.OVERWORLD_WINTER) && dy > 0 && dy < world.getHeightBlocks() && world.isAirBlock(dx, dy, dz) && world.getBlockMaterial(dx, dy - 1, dz).blocksMotion()) {
					world.setBlockWithNotify(dx, dy, dz, Block.layerSnow.id);
				}

				if ((localBiome.hasSurfaceSnow() || world.worldType == WorldTypes.OVERWORLD_WINTER) && (world.getBlockId(dx, oceanY - 1, dz) == Block.fluidWaterStill.id || world.getBlockId(dx, oceanY - 1, dz) == Block.fluidWaterFlowing.id)) {
					world.setBlockWithNotify(dx, oceanY - 1, dz, Block.ice.id);
				}
			}
		}

		BlockSand.fallInstantly = false;
		world.scheduledUpdatesAreImmediate = false;
	}
}
