package teamport.wasteland.core.world.biome;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancy;

import java.util.Random;

public class BiomeWastesCity extends Biome {
	public BiomeWastesCity() {
		super("wastes_city");
		this.setColor(16775936);
		this.setTopBlock(Block.mudBaked.id);
		this.setFillerBlock(Block.mudBaked.id);
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
		return new WorldFeatureTreeFancy(0, Block.logOak.id);
	}
}
