package teamport.wasteland.world;

import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.chunk.ChunkGenerator;
import net.minecraft.core.world.type.WorldTypeOverworldExtended;

public class WorldTypeWastelandExtended extends WorldTypeWasteland {

	public WorldTypeWastelandExtended(String languageKey) {
		super(languageKey);
	}

	public int getMinY() {
		return 0;
	}

	public int getMaxY() {
		return 255;
	}

	public int getOceanY() {
		return 128;
	}

	public float getCloudHeight() {
		return 236.0F;
	}
}
