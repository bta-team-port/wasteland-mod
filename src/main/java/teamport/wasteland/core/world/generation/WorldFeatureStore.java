package teamport.wasteland.core.world.generation;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntityChest;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldFeatureStore extends WorldFeature {

	private ItemStack generateLoot(Random rand) {
		switch (rand.nextInt(17)) {
			case 16: return new ItemStack(Items.DYE, rand.nextInt(3) + 1, 0);
			case 15: return new ItemStack(Items.BOOK, rand.nextInt(3) + 1);
			case 14: return new ItemStack(Items.PAPER, rand.nextInt(6) + 1);
			case 13: return new ItemStack(Items.FEATHER_CHICKEN, rand.nextInt(2) + 1);
			case 12: return new ItemStack(Items.ARMOR_HELMET_LEATHER, 1, rand.nextInt(90));
			case 11: return new ItemStack(Items.ARMOR_CHESTPLATE_LEATHER, 1, rand.nextInt(90));
			case 10: return new ItemStack(Items.ARMOR_LEGGINGS_LEATHER, 1, rand.nextInt(90));
			case 9: return new ItemStack(Items.ARMOR_BOOTS_LEATHER, 1, rand.nextInt(90));
			case 8: return new ItemStack(Items.INGOT_IRON);
			case 7: return rand.nextInt(4) == 0 ? new ItemStack(Items.FOOD_PORKCHOP_RAW, 1) : null;
			case 6: return rand.nextInt(4) == 0 ? new ItemStack(Items.FOOD_FISH_RAW, 1) : null;
			case 5: return new ItemStack(Items.COAL, rand.nextInt(4) + 1);
			case 4: return new ItemStack(Items.BONE, rand.nextInt(8) + 1);
			case 3: return new ItemStack(Items.FOOD_CHERRY, 1);
			case 2: return new ItemStack(Items.EGG_CHICKEN, rand.nextInt(6) + 1);
			case 1: return new ItemStack(Items.FOOD_BREAD, 1);
			case 0: default: return null;
		}
	}

	private void generateShelf(World world, Random rand, int x, int y, int z) {
		for (int _x = x - 3; _x < x; _x++) {
			world.setBlockWithNotify(_x, y + 2, z, Blocks.SLAB_PLANKS_OAK.id());

			if (rand.nextInt(12) != 0) {
				world.setBlockAndMetadataWithNotify(_x, y, z, Blocks.STAIRS_PLANKS_OAK.id(), 2);
			} else {
				world.setBlockWithNotify(_x, y, z, Blocks.CHEST_PLANKS_OAK.id());
				TileEntityChest tile = (TileEntityChest) world.getTileEntity(_x, y, z);

				for (int i = 0; i < 3; i++) {
					ItemStack loot = generateLoot(rand);

					if (loot != null && tile != null) {
						tile.setItem(rand.nextInt(tile.getContainerSize()), loot);
					}
				}
			}

			if (rand.nextInt(12) != 0) {
				world.setBlockAndMetadataWithNotify(_x, y + 1, z, Blocks.STAIRS_PLANKS_OAK.id(), 2);
			} else {
				world.setBlockWithNotify(_x, y + 1, z, Blocks.CHEST_PLANKS_OAK.id());
				TileEntityChest tile = (TileEntityChest) world.getTileEntity(_x, y, z);

				for (int i = 0; i < 3; i++) {
					ItemStack loot = generateLoot(rand);

					if (loot != null && tile != null) {
						tile.setItem(rand.nextInt(tile.getContainerSize()), loot);
					}
				}
			}
		}
	}

	private void generateLongShelf(World world, Random rand, int x, int y, int z) {
		for (int _x = x - 4; _x < x; _x++) {
			world.setBlockWithNotify(_x, y + 2, z, Blocks.SLAB_PLANKS_OAK.id());

			if (rand.nextInt(12) != 0) {
				world.setBlockAndMetadataWithNotify(_x, y, z, Blocks.STAIRS_PLANKS_OAK.id(), 2);
			} else {
				world.setBlockWithNotify(_x, y, z, Blocks.CHEST_PLANKS_OAK.id());
				TileEntityChest tile = (TileEntityChest) world.getTileEntity(_x, y, z);

				for (int i = 0; i < 3; i++) {
					ItemStack loot = generateLoot(rand);

					if (loot != null && tile != null) {
						tile.setItem(rand.nextInt(tile.getContainerSize()), loot);
					}
				}
			}

			if (rand.nextInt(12) != 0) {
				world.setBlockAndMetadataWithNotify(_x, y + 1, z, Blocks.STAIRS_PLANKS_OAK.id(), 2);
			} else {
				world.setBlockWithNotify(_x, y + 1, z, Blocks.CHEST_PLANKS_OAK.id());
				TileEntityChest tile = (TileEntityChest) world.getTileEntity(_x, y, z);

				for (int i = 0; i < 18; i++) {
					ItemStack loot = generateLoot(rand);

					if (loot != null && tile != null) {
						tile.setItem(rand.nextInt(tile.getContainerSize()), loot);
					}
				}
			}
		}
	}

	private void generateRoom(World world, Random rand, int x, int y, int z) {
		for (int _x = x; _x < x + 5; _x++) {
			for (int _y = y; _y < y + 4; _y++) {
				for (int _z = z - 6; _z < z - 3; _z++) {
					world.setBlockWithNotify(x, _y, z - 3, Blocks.LIMESTONE_POLISHED.id());

					world.setBlockWithNotify(_x, _y, _z, rand.nextInt(3) != 0 ? Blocks.BRICK_LIMESTONE.id() : 0);
				}
			}
		}

		for (int _x = x + 1; _x < x + 5; _x++) {
			for (int _y = y; _y < y + 4; _y++) {
				for (int _z = z - 6; _z < z - 2; _z++) {
					world.setBlockWithNotify(_x, _y, _z, 0);
				}
			}
		}
		for (int _x = x + 1; _x < x + 5; _x++) {
			for (int _y = y; _y < y + 4; _y++) {
				world.setBlockWithNotify(_x, _y, z - 3, rand.nextInt(3) != 0 ? Blocks.BRICK_LIMESTONE.id() : 0);
			}
		}

		world.setBlockAndMetadataWithNotify(x + 3, y, z - 3, Blocks.DOOR_PLANKS_OAK_BOTTOM.id(), 3);
		world.setBlockAndMetadataWithNotify(x + 3, y + 1, z - 3, Blocks.DOOR_PLANKS_OAK_TOP.id(), 3);
	}

	private void generateBuilding(World world, Random rand, int x, int y, int z) {
		// Walls
		for (int _x = x - 7; _x < x + 7; _x++) {
			for (int _z = z - 7; _z < z + 7; _z++) {
				for (int _y = y; _y < y + 4; _y++) {
					if (rand.nextInt(3) != 0) {
						world.setBlockWithNotify(_x, _y, _z, Blocks.BRICK_LIMESTONE.id());
						world.setBlockWithNotify(_x, y + 4, _z, Blocks.BRICK_STONE.id());
					} else {
						world.setBlockWithNotify(_x, _y, _z, 0);
					}
					world.setBlockWithNotify(_x, y + 5, _z, Blocks.SLAB_BRICK_STONE.id());

					world.setBlockWithNotify(_x, y - 1, _z, rand.nextInt(3) == 0 ? Blocks.COBBLE_STONE.id() : Blocks.COBBLE_STONE_MOSSY.id());
				}

				for (int _y = y - 7; _y < y - 1; _y++) {
					world.setBlockWithNotify(_x, _y, _z, Blocks.STONE.id());
				}
			}
		}

		// Empty space
		for (int _x = x - 6; _x < x + 6; _x++) {
			for (int _y = y; _y < y + 4; _y++) {
				for (int _z = z - 6; _z < z + 6; _z++) {
					world.setBlockWithNotify(_x, _y, _z, 0);
					world.setBlockWithNotify(_x, y + 5, _z, 0);
				}
			}
		}

		// Shelves
		generateShelf(world, rand, x + 6, y, z + 3);
		generateShelf(world, rand, x - 3, y, z + 3);
		generateShelf(world, rand, x + 6, y, z + 5);
		generateShelf(world, rand, x - 3, y, z + 5);

		generateLongShelf(world, rand, x + 2, y, z + 3);
		generateLongShelf(world, rand, x + 2, y, z + 5);

		// Entrance
		for (int _y = y; _y < y + 3; _y++) {
			for (int _z = z - 2; _z < z + 2; _z++) {
				world.setBlockWithNotify(x - 7, _y, _z, Blocks.GLASS.id());
			}
		}

		world.setBlockAndMetadataWithNotify(x - 7, y, z - 1, Blocks.DOOR_PLANKS_OAK_BOTTOM.id(), 0);
		world.setBlockAndMetadataWithNotify(x - 7, y + 1, z - 1, Blocks.DOOR_PLANKS_OAK_TOP.id(), 0);
		world.setBlockAndMetadataWithNotify(x - 7, y, z, Blocks.DOOR_PLANKS_OAK_BOTTOM.id(), 15);
		world.setBlockAndMetadataWithNotify(x - 7, y + 1, z, Blocks.DOOR_PLANKS_OAK_TOP.id(), 15);

		generateRoom(world, rand, x, y, z);
	}

	private void generateCounterShelf(World world, Random rand, int x, int y, int z) {
		for (int i = 3; i > 0; i--) {
			world.setBlockAndMetadataWithNotify(x - i, y, z - 6, Blocks.SLAB_PLANKS_OAK.id(), 2);
		}

		world.setBlockWithNotify(x, y, z - 6, Blocks.BOOKSHELF_PLANKS_OAK.id());
		world.setBlockWithNotify(x, y + 1, z - 6, Blocks.BOOKSHELF_PLANKS_OAK.id());
		world.setBlockWithNotify(x - 4, y, z - 6, Blocks.BOOKSHELF_PLANKS_OAK.id());
		world.setBlockWithNotify(x - 4, y + 1, z - 6, Blocks.BOOKSHELF_PLANKS_OAK.id());

		for (int i = 5; i > 0; i--) {
			world.setBlockWithNotify(x - i + 1, y + 2, z - 6, Blocks.SLAB_PLANKS_OAK.id());
		}
	}

	@Override
	public boolean place(World world, Random rand, int x, int y, int z) {
		if (y < world.getHeightBlocks() && world.getBlock(x, y - 1, z) == Blocks.MUD_BAKED && world.getBlock(x, y, z) == null) {
			generateBuilding(world, rand, x, y, z);

			// Counter
			world.setBlockAndMetadataWithNotify(x - 6 , y, z - 4, Blocks.SLAB_STONE_POLISHED.id(), 1);
			for (int xFE = 5; xFE > 2; xFE--) {
				world.setBlockAndMetadataWithNotify(x - xFE, y, z - 4, Blocks.SLAB_STONE_POLISHED.id(), 2);
			}
			world.setBlockAndMetadataWithNotify(x - 2, y, z - 4, Blocks.SLAB_STONE_POLISHED.id(), 1);

			generateCounterShelf(world, rand, x - 2, y, z);

			// Counter ladder
			for (int _y = y; _y < y + 4; _y++) {
				world.setBlockWithNotify(x - 1, _y, z - 7, Blocks.BRICK_LIMESTONE.id());
				world.setBlockAndMetadataWithNotify(x - 1, _y, z - 6, Blocks.LADDER_OAK.id(), 3);
				world.setBlockAndMetadataWithNotify(x - 1, y + 4, z - 6, Blocks.LADDER_OAK.id(), 3);
			}

			return true;
		}
		return false;
	}
}
