package teamport.wasteland.core.world.biome;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancy;
import net.minecraft.core.world.weather.Weather;

import java.util.Random;

public class BiomeWasteland extends Biome {

	public BiomeWasteland() {
		super("wastes");
		this.setBlockedWeathers(Weather.overworldSnow);
		this.setColor(16775936);
		this.setTopBlock(Block.mudBaked.id);
		this.setFillerBlock(Block.mudBaked.id);
		this.spawnableCreatureList.clear();
		this.spawnableAmbientCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}

	@Override
	public int getSkyColor(float temperature) {
		return 0;
	}

	@Override
	public WorldFeature getRandomWorldGenForTrees(Random random) {
		return new WorldFeatureTreeFancy(0, Block.logOak.id);
	}
}
