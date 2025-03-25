package teamport.wasteland;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.worldtype.WorldTypeFXDispatcher;
import net.minecraft.client.render.worldtype.WorldTypeFXOverworld;
import turniplabs.halplibe.util.ClientStartEntrypoint;

@Environment(EnvType.CLIENT)
public class WastelandClient implements ClientStartEntrypoint {
	@Override
	public void beforeClientStart() {

	}

	@Override
	public void afterClientStart() {
		WorldTypeFXDispatcher.getInstance().addDispatch(new WorldTypeFXOverworld(Wasteland.worldType_Wasteland));
		WorldTypeFXDispatcher.getInstance().addDispatch(new WorldTypeFXOverworld(Wasteland.worldType_Wasteland_Extended));
	}
}
