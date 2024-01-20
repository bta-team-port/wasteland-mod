package teamport.wasteland.world.generation;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldFeatureWastesFire extends WorldFeature {

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		for(int i = 0; i < world.getHeightBlocks(); ++i) {
			int _x = x + random.nextInt(16) - random.nextInt(16);
			int _y = y + random.nextInt(8) - random.nextInt(8);
			int _z = z + random.nextInt(16) - random.nextInt(16);

			if (random.nextInt(16) == 0) {
				if (world.getBlockId(_x, _y - 1, _z) != Block.fire.id && !world.isAirBlock(_x, _y - 1, _z))
					world.setBlockWithNotify(_x, _y - 1, _z, Block.netherrack.id);

				if (world.isAirBlock(_x, _y, _z) && world.getBlockId(_x, _y - 1, _z) == Block.netherrack.id) {
					world.scheduledUpdatesAreImmediate = false;
					world.setBlockWithNotify(_x, _y, _z, Block.fire.id);
					world.scheduledUpdatesAreImmediate = true;
				}
			}
		}
		return true;
	}
}
