package teamport.wasteland.world.biome;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.biome.Biome;

public class BiomeWastesTaiga extends Biome {
	public BiomeWastesTaiga() {
		this.setColor(16775936);
		this.setTopBlock(Block.dirt.id);
		this.setFillerBlock(Block.dirt.id);
		this.setSurfaceSnow();
	}

	@Override
	public int getSkyColor(float temperature) {
		return 0;
	}
}
