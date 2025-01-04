package teamport.wasteland.core.world;

public class WorldTypeWastelandExtended extends WorldTypeWasteland {

	public WorldTypeWastelandExtended(Properties properties) {
		super(properties);
	}

	public int getMaxY() {
		return 255;
	}

	public int getOceanY() {
		return 128;
	}
}
