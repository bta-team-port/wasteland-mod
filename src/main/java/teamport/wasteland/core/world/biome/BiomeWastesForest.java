package teamport.wasteland.core.world.biome;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancy;

import java.util.Random;

public class BiomeWastesForest extends Biome {

	public BiomeWastesForest() {
		super("wastes_forest");
		this.setColor(16775936);
		this.setTopBlock(Block.dirtScorched.id);
		this.setFillerBlock(Block.dirtScorched.id);
		this.spawnableCreatureList.clear();
		this.spawnableAmbientCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}

	@Override
	public int getSkyColor(float temperature) {
		return 0x000000;
	}

	@Override
	public WorldFeature getRandomWorldGenForTrees(Random random) {
		if (random.nextInt(20) == 0) {
			return new WorldFeatureTree(0, Block.logBirch.id, 10);
		} else {
			return new WorldFeatureTreeFancy(0, Block.logOak.id);
		}
	}
}
