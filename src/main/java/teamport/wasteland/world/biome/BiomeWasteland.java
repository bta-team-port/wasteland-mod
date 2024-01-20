package teamport.wasteland.world.biome;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.weather.Weather;

public class BiomeWasteland extends Biome {

	public BiomeWasteland() {
		this.setBlockedWeathers(Weather.overworldSnow);
		this.setColor(16775936);
		this.setTopBlock(Block.dirt.id);
		this.setFillerBlock(Block.dirt.id);
	}

	@Override
	public int getSkyColor(float temperature) {
		return 0;
	}
}
