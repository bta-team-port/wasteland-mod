package teamport.wasteland.world.biome;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.weather.Weather;

public class BiomeWastesDesert extends Biome {
	public BiomeWastesDesert() {
		this.setBlockedWeathers(Weather.overworldRain, Weather.overworldSnow);
		this.setColor(16775936);
		this.setTopBlock(Block.sand.id);
		this.setFillerBlock(Block.sandstone.id);
		this.spawnableCreatureList.clear();
		this.spawnableAmbientCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}

	@Override
	public int getSkyColor(float temperature) {
		return 0;
	}
}
