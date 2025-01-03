package teamport.wasteland.core.world.generation;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityChest;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldFeatureStore extends WorldFeature {

	private ItemStack generateLoot(Random rand) {
		switch (rand.nextInt(17)) {
			case 16: return new ItemStack(Item.dye, rand.nextInt(3) + 1, 0);
			case 15: return new ItemStack(Item.book, rand.nextInt(3) + 1);
			case 14: return new ItemStack(Item.paper, rand.nextInt(6) + 1);
			case 13: return new ItemStack(Item.featherChicken, rand.nextInt(2) + 1);
			case 12: return new ItemStack(Item.armorHelmetLeather, 1, rand.nextInt(90));
			case 11: return new ItemStack(Item.armorChestplateLeather, 1, rand.nextInt(90));
			case 10: return new ItemStack(Item.armorLeggingsLeather, 1, rand.nextInt(90));
			case 9: return new ItemStack(Item.armorBootsLeather, 1, rand.nextInt(90));
			case 8: return new ItemStack(Item.ingotIron);
			case 7: return rand.nextInt(4) == 0 ? new ItemStack(Item.foodPorkchopRaw, 1) : null;
			case 6: return rand.nextInt(4) == 0 ? new ItemStack(Item.foodFishRaw, 1) : null;
			case 5: return new ItemStack(Item.coal, rand.nextInt(4) + 1);
			case 4: return new ItemStack(Item.bone, rand.nextInt(8) + 1);
			case 3: return new ItemStack(Item.foodCherry, 1);
			case 2: return new ItemStack(Item.eggChicken, rand.nextInt(6) + 1);
			case 1: return new ItemStack(Item.foodBread, 1);
			case 0: default: return null;
		}
	}

	private void generateShelf(World world, Random rand, int x, int y, int z) {
		for (int _x = x - 3; _x < x; _x++) {
			world.setBlockWithNotify(_x, y + 2, z, Block.slabPlanksOak.id);

			if (rand.nextInt(12) != 0) {
				world.setBlockAndMetadataWithNotify(_x, y, z, Block.stairsPlanksOak.id, 2);
			} else {
				world.setBlockWithNotify(_x, y, z, Block.chestPlanksOak.id);
				TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(_x, y, z);

				for (int i = 0; i < 3; i++) {
					ItemStack loot = generateLoot(rand);

					if (loot != null && tile != null) {
						tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), loot);
					}
				}
			}

			if (rand.nextInt(12) != 0) {
				world.setBlockAndMetadataWithNotify(_x, y + 1, z, Block.stairsPlanksOak.id, 2);
			} else {
				world.setBlockWithNotify(_x, y + 1, z, Block.chestPlanksOak.id);
				TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(_x, y, z);

				for (int i = 0; i < 3; i++) {
					ItemStack loot = generateLoot(rand);

					if (loot != null && tile != null) {
						tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), loot);
					}
				}
			}
		}
	}

	private void generateLongShelf(World world, Random rand, int x, int y, int z) {
		for (int _x = x - 4; _x < x; _x++) {
			world.setBlockWithNotify(_x, y + 2, z, Block.slabPlanksOak.id);

			if (rand.nextInt(12) != 0) {
				world.setBlockAndMetadataWithNotify(_x, y, z, Block.stairsPlanksOak.id, 2);
			} else {
				world.setBlockWithNotify(_x, y, z, Block.chestPlanksOak.id);
				TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(_x, y, z);

				for (int i = 0; i < 3; i++) {
					ItemStack loot = generateLoot(rand);

					if (loot != null && tile != null) {
						tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), loot);
					}
				}
			}

			if (rand.nextInt(12) != 0) {
				world.setBlockAndMetadataWithNotify(_x, y + 1, z, Block.stairsPlanksOak.id, 2);
			} else {
				world.setBlockWithNotify(_x, y + 1, z, Block.chestPlanksOak.id);
				TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(_x, y, z);

				for (int i = 0; i < 18; i++) {
					ItemStack loot = generateLoot(rand);

					if (loot != null && tile != null) {
						tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), loot);
					}
				}
			}
		}
	}

	private void generateRoom(World world, Random rand, int x, int y, int z) {
		for (int _x = x; _x < x + 5; _x++) {
			for (int _y = y; _y < y + 4; _y++) {
				for (int _z = z - 6; _z < z - 3; _z++) {
					world.setBlockWithNotify(x, _y, z - 3, Block.limestonePolished.id);

					world.setBlockWithNotify(_x, _y, _z, rand.nextInt(3) != 0 ? Block.brickLimestone.id : 0);
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
				world.setBlockWithNotify(_x, _y, z - 3, rand.nextInt(3) != 0 ? Block.brickLimestone.id : 0);
			}
		}

		world.setBlockAndMetadataWithNotify(x + 3, y, z - 3, Block.doorPlanksOakBottom.id, 3);
		world.setBlockAndMetadataWithNotify(x + 3, y + 1, z - 3, Block.doorPlanksOakTop.id, 3);
	}

	private void generateBuilding(World world, Random rand, int x, int y, int z) {
		// Walls
		for (int _x = x - 7; _x < x + 7; _x++) {
			for (int _y = y; _y < y + 4; _y++) {
				for (int _z = z - 7; _z < z + 7; _z++) {
					if (rand.nextInt(3) != 0) {
						world.setBlockWithNotify(_x, _y, _z, Block.brickLimestone.id);
						world.setBlockWithNotify(_x, y + 4, _z, Block.brickStone.id);
					} else {
						world.setBlockWithNotify(_x, _y, _z, 0);
					}
					world.setBlockWithNotify(_x, y + 5, _z, Block.slabBrickStone.id);

					world.setBlockWithNotify(_x, y - 1, _z, rand.nextInt(3) == 0 ? Block.cobbleStone.id : Block.cobbleStoneMossy.id);
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
				world.setBlockWithNotify(x - 7, _y, _z, Block.glass.id);
			}
		}

		world.setBlockAndMetadataWithNotify(x - 7, y, z - 1, Block.doorPlanksOakBottom.id, 0);
		world.setBlockAndMetadataWithNotify(x - 7, y + 1, z - 1, Block.doorPlanksOakTop.id, 0);
		world.setBlockAndMetadataWithNotify(x - 7, y, z, Block.doorPlanksOakBottom.id, 15);
		world.setBlockAndMetadataWithNotify(x - 7, y + 1, z, Block.doorPlanksOakTop.id, 15);

		generateRoom(world, rand, x, y, z);
	}

	private void generateCounterShelf(World world, Random rand, int x, int y, int z) {
		for (int i = 3; i > 0; i--) {
			world.setBlockAndMetadataWithNotify(x - i, y, z - 6, Block.slabPlanksOak.id, 2);
		}

		world.setBlockWithNotify(x, y, z - 6, Block.bookshelfPlanksOak.id);
		world.setBlockWithNotify(x, y + 1, z - 6, Block.bookshelfPlanksOak.id);
		world.setBlockWithNotify(x - 4, y, z - 6, Block.bookshelfPlanksOak.id);
		world.setBlockWithNotify(x - 4, y + 1, z - 6, Block.bookshelfPlanksOak.id);

		for (int i = 5; i > 0; i--) {
			world.setBlockWithNotify(x - i + 1, y + 2, z - 6, Block.slabPlanksOak.id);
		}
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (y < world.getHeightBlocks() && world.getBlock(x, y - 1, z) == Block.mudBaked && world.getBlock(x, y, z) == null) {
			generateBuilding(world, rand, x, y, z);

			// Counter
			world.setBlockAndMetadataWithNotify(x - 6 , y, z - 4, Block.slabStonePolished.id, 1);
			for (int xFE = 5; xFE > 2; xFE--) {
				world.setBlockAndMetadataWithNotify(x - xFE, y, z - 4, Block.slabStonePolished.id, 2);
			}
			world.setBlockAndMetadataWithNotify(x - 2, y, z - 4, Block.slabStonePolished.id, 1);

			generateCounterShelf(world, rand, x - 2, y, z);

			// Counter ladder
			for (int _y = y; _y < y + 4; _y++) {
				world.setBlockWithNotify(x - 1, _y, z - 7, Block.brickLimestone.id);
				world.setBlockAndMetadataWithNotify(x - 1, _y, z - 6, Block.ladderOak.id, 3);
				world.setBlockAndMetadataWithNotify(x - 1, y + 4, z - 6, Block.ladderOak.id, 3);
			}

			return true;
		}
		return false;
	}
}
