package teamport.wasteland.core.world;

public class WorldTypeWastelandExtended extends WorldTypeWasteland {

	public WorldTypeWastelandExtended(String languageKey) {
		super(languageKey);
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
