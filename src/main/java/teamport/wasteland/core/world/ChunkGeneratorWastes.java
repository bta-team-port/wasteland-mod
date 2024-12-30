package teamport.wasteland.core.world;

import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.MapGenCaves;
import net.minecraft.core.world.generate.chunk.perlin.ChunkGeneratorPerlin;
import net.minecraft.core.world.generate.chunk.perlin.overworld.SurfaceGeneratorOverworld;
import net.minecraft.core.world.generate.chunk.perlin.overworld.TerrainGeneratorOverworld;
import teamport.wasteland.extra.compat.terrainapi.ChunkDecoratorWastesAPI;

public class ChunkGeneratorWastes extends ChunkGeneratorPerlin {

	protected ChunkGeneratorWastes(World world) {
		super(world,
			new ChunkDecoratorWastesAPI(world),
			new TerrainGeneratorOverworld(world),
			new SurfaceGeneratorOverworld(world),
			new MapGenCaves(false));
	}
}
