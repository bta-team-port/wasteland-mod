package teamport.wasteland.core.world.biome;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;

public class BiomeWastesDesert extends Biome {
	public BiomeWastesDesert() {
		super("wastes_desert");
		this.setBlockedWeathers(Weathers.OVERWORLD_RAIN, Weathers.OVERWORLD_SNOW);
		this.setColor(16775936);
		this.setTopBlock(Blocks.SAND.id());
		this.setFillerBlock(Blocks.SANDSTONE.id());
		this.spawnableCreatureList.clear();
		this.spawnableAmbientCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
	}

	@Override
	public int getSkyColor(float temperature) {
		return 0x000000;
	}
}
