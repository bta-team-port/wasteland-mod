package teamport.wasteland.compat.terrainapi;

import useless.terrainapi.api.TerrainAPI;

import static teamport.wasteland.Wasteland.MOD_ID;

public class TerrainAPIPlugin implements TerrainAPI {

	@Override
	public String getModID() {
		return MOD_ID;
	}

	@Override
	public void onInitialize() {
		new WastesInitialization().init();
	}
}
