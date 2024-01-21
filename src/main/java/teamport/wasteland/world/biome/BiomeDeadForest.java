package teamport.wasteland.world.biome;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.weather.Weather;

public class BiomeDeadForest extends Biome {

	public BiomeDeadForest() {
		this.setColor(16775936);
		this.setTopBlock(Block.dirt.id);
		this.setFillerBlock(Block.dirt.id);
		this.spawnableCreatureList.clear();
		this.spawnableAmbientCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}

	@Override
	public int getSkyColor(float temperature) {
		return 0;
	}
}
