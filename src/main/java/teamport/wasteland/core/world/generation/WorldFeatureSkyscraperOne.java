package teamport.wasteland.core.world.generation;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityChest;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldFeatureSkyscraperOne extends WorldFeature {

	private ItemStack generateCafeteriaLoot(Random rand) {
		switch (rand.nextInt(9)) {
			case 8: return new ItemStack(Item.foodPorkchopCooked, 1);
			case 7: return new ItemStack(Item.foodApple, rand.nextInt(2) + 1);
			case 6: return rand.nextInt(100) == 0 ? new ItemStack(Item.foodAppleGold) : null;
			case 5: return new ItemStack(Item.foodBread, rand.nextInt(3) + 1);
			case 4: return new ItemStack(Item.foodCherry, rand.nextInt(2) + 1);
			case 3: return new ItemStack(Item.foodCookie, rand.nextInt(4) + 1);
			case 2: return new ItemStack(Item.foodFishCooked, 1);
			case 1: return rand.nextInt(3) == 0 ? new ItemStack(Item.foodStewMushroom, 1) : new ItemStack(Item.bowl, 1);
			case 0: default: return null;
		}
	}

	private ItemStack generateLibraryLoot(Random rand) {
		switch (rand.nextInt(5)) {
			case 4: return new ItemStack(Item.dye, rand.nextInt(3) + 1, 0);
			case 3: return new ItemStack(Item.book, rand.nextInt(3) + 1);
			case 2: return new ItemStack(Item.paper, rand.nextInt(6) + 1);
			case 1: return new ItemStack(Item.featherChicken, rand.nextInt(2) + 1);
			case 0: default: return null;
		}
	}

	private ItemStack generateStoreLoot(Random rand) {
		switch (rand.nextInt(11)) {
			case 10: return rand.nextInt(4) == 0 ? new ItemStack(Item.foodPorkchopRaw, 1) : null;
			case 9: return rand.nextInt(4) == 0 ? new ItemStack(Item.foodFishRaw, 1) : null;
			case 8: return new ItemStack(Item.coal, rand.nextInt(4) + 1);
			case 7: return new ItemStack(Item.bone, rand.nextInt(8) + 1);
			case 6: return new ItemStack(Item.toolHoeWood, 1, rand.nextInt(63));
			case 5: return new ItemStack(Item.toolShovelWood, 1, rand.nextInt(63));
			case 4: return new ItemStack(Item.seedsWheat, rand.nextInt(2) + 1);
			case 3: return new ItemStack(Item.foodCherry, 1);
			case 2: return new ItemStack(Item.eggChicken, rand.nextInt(6) + 1);
			case 1: return new ItemStack(Item.foodBread, 1);
			case 0: default: return null;
		}
	}

	private void generateLevel(World world, Random rand, int x, int y, int z) {
		// Walls
		for (int _x = x - 6; _x < x + 7; _x++) {
			for (int _y = y; _y < y + 4; _y++) {
				for (int _z = z - 6; _z < z + 7; _z++) {
					world.setBlockWithNotify(_x, _y, _z, rand.nextInt(4) != 0 ? Block.brickStone.id : 0);
				}
			}
		}

		for (int _y = y; _y < y + 4; _y++) {
			for (int _z = z - 6; _z < z + 7; _z++) {
				if (_y != y + 3) {
					if (rand.nextInt(2) == 0) {
						world.setBlockWithNotify(x - 6, _y, _z, Block.glass.id);
						world.setBlockWithNotify(x + 6, _y, _z, Block.glass.id);
					} else {
						world.setBlockWithNotify(x - 6, _y, _z, 0);
						world.setBlockWithNotify(x + 6, _y, _z, 0);
					}
				}
			}
		}

		for (int _y = y; _y < y + 4; _y++) {
			// ZValsNeg XVals
			for (int xFE : new int[]{x - 6, x - 3, x, x + 3, x + 6}) {
				world.setBlockWithNotify(xFE, _y, z - 6, Block.logOak.id);
				world.setBlockWithNotify(xFE, _y, z + 6, Block.logOak.id);
			}

			// XValsNeg ZVals
			for (int zFE : new int[]{z - 6, z - 3, z, z + 3, z + 6}) {
				world.setBlockWithNotify(x - 6, _y, zFE, Block.logOak.id);
				world.setBlockWithNotify(x + 6, _y, zFE, Block.logOak.id);
			}
		}

		// Floors & Ceilings
		for (int _x = x - 5; _x < x + 6; _x++) {
			for (int _z = z - 5; _z < z + 6; _z++) {
				if (rand.nextInt(3) == 0) {
					world.setBlockWithNotify(_x, y - 1, _z, Block.cobbleStone.id);
					world.setBlockWithNotify(_x, y + 3, _z, Block.cobbleStone.id);
				} else {
					world.setBlockWithNotify(_x, y - 1, _z, Block.cobbleStoneMossy.id);
					world.setBlockWithNotify(_x, y + 3, _z, Block.cobbleStoneMossy.id);
				}
			}
		}

		// Interior Air
		for (int _x = x - 5; _x < x + 6; _x++) {
			for (int _y = y; _y < y + 3; _y++) {
				for (int _z = z - 5; _z < z + 6; _z++) {
					world.setBlockWithNotify(_x, _y, _z, 0);
				}
			}
		}
	}

	private void generateCafeteriaTable(World world, Random rand, int x, int y, int z) {
		for (int _x = x - 1; _x < x + 1; _x++) {
			for (int _z = z - 1; _z < z + 1; _z++) {
				if (rand.nextInt(2) == 0) {
					world.setBlockWithNotify(_x, y, _z, Block.fencePlanksOak.id);
					world.setBlockWithNotify(_x, y + 1, _z, Block.pressureplatePlanksOak.id);
				}
			}
		}
	}

	private void generateLibraryShelves(World world, Random rand, int x, int y, int z) {
		for (int _x = x - 3; _x < x; _x++) {
			if (rand.nextInt(2) == 0) {
				world.setBlockWithNotify(_x, y, z, Block.bookshelfPlanksOak.id);
				world.setBlockWithNotify(_x, y + 1, z, Block.bookshelfPlanksOak.id);
			}

			if (world.rand.nextInt(6) == 0) {
				world.setBlockWithNotify(_x, y, z, Block.chestPlanksOak.id);
				TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(_x, y, z);

				for (int i = 0; i < 6; i++) {
					ItemStack loot = generateLibraryLoot(rand);

					if (loot != null && tile != null) {
						tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), loot);
					}
				}
			}
		}
	}

	private void generateStoreShelvesOne(World world, Random rand, int x, int y, int z) {
		for (int _z = z - 1; _z < z + 1; _z++) {
			if (rand.nextInt(3) == 0) {
				world.setBlockAndMetadataWithNotify(x, y, _z, Block.stairsPlanksOak.id, 1);
				world.setBlockAndMetadataWithNotify(x, y + 1, _z, Block.stairsPlanksOak.id, 1);
				world.setBlockAndMetadataWithNotify(x, y + 2, _z, Block.slabPlanksOak.id, 0);
			}

			if (rand.nextInt(16) == 0) {
				world.setBlockWithNotify(x, y, _z, Block.planksOak.id);
				TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(x, y, _z);

				for (int i = 0; i < 4; i++) {
					ItemStack loot = generateStoreLoot(rand);

					if (loot != null && tile != null) {
						tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), loot);
					}
				}
			}
		}
	}

	private void generateStoreShelvesTwo(World world, Random rand, int x, int y, int z) {
		for (int _x = x - 1; _x < x + 1; _x++) {
			if (rand.nextInt(3) == 0) {
				world.setBlockAndMetadataWithNotify(_x, y, z, Block.stairsPlanksOak.id, 2);
				world.setBlockAndMetadataWithNotify(_x, y + 1, z, Block.stairsPlanksOak.id, 2);
				world.setBlockAndMetadataWithNotify(_x, y + 2, z, Block.slabPlanksOak.id, 0);
			}

			if (rand.nextInt(16) == 0) {
				world.setBlockWithNotify(_x, y, z, Block.chestPlanksOak.id);
				TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(_x, y, z);

				for (int i = 0; i < 4; i++) {
					ItemStack loot = generateStoreLoot(rand);

					if (loot != null && tile != null) {
						tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), loot);
					}
				}
			}
		}
	}

	private void generateRandomFloor(World world, Random rand, int x, int y, int z) {
		if (rand.nextInt(10) != 0) {
			if (rand.nextInt(2) == 0) {
				// Cafeteria
				for (int _z = z - 2; _z < z + 3; _z++) {
					world.setBlockWithNotify(x, y, _z, Block.planksOak.id);
					if (rand.nextInt(8) == 0) {
						world.setBlockAndMetadataWithNotify(x, y, _z, Block.chestPlanksOak.id, 3);

						TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(x, y, _z);
						for (int i = 0; i < 4; i++) {
							ItemStack loot = generateCafeteriaLoot(rand);

							if (loot != null && tile != null) {
								tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), loot);
							}
						}
					}
				}

				for (int zFE : new int[]{z + 5, z + 2, z - 1, z - 4}) {
					generateCafeteriaTable(world, rand, x + 5, y, zFE);
					generateCafeteriaTable(world, rand, x - 4, y, zFE);
				}
			} else {
				// Library
				for (int zFE : new int[]{z + 3, z, z - 3}) {
					generateLibraryShelves(world, rand, x + 6, y, zFE);
					generateLibraryShelves(world, rand, x - 2, y, zFE);
				}
			}
		} else {
			// Store
			for (int zFE : new int[]{z + 5, z + 2, z - 1, z - 4}) {
				generateStoreShelvesOne(world, rand, x - 5, y, zFE);
				generateStoreShelvesOne(world, rand, x - 3, y, zFE);
			}

			for (int xFE : new int[]{x + 5, x + 2}) {
				generateStoreShelvesTwo(world, rand, xFE, y, z + 5);
				generateStoreShelvesTwo(world, rand, xFE, y, z + 3);
				generateStoreShelvesTwo(world, rand, xFE, y, z + 1);
				generateStoreShelvesTwo(world, rand, xFE, y, z - 1);
			}
		}
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		if (y < world.getHeightBlocks() && world.getBlock(x, y - 1, z) == Block.mudBaked && world.getBlock(x, y, z) == null) {
			// Foundation
			for (int _x = x - 8; _x < x + 9; _x++) {
				for (int _y = y - 6; _y < y; _y++) {
					for (int _z = z - 8; _z < z + 9; _z++) {
						world.setBlockWithNotify(_x, _y, _z, Block.stone.id);
						world.setBlockWithNotify(_x, y - 1, _z, Block.cobbleStone.id);
					}
				}
			}

			// Sidewalk
			for (int _x = x - 8; _x < x + 9; _x++) {
				for (int _z = z - 8; _z < z + 9; _z++) {
					world.setBlockWithNotify(_x, y - 1, _z, Block.stonePolished.id);
				}
			}

			// Ground level floor
			for (int height = 0; height < 5; height++) {
				generateLevel(world, random, x, y + (height * 4), z);
				generateRandomFloor(world, random, x, y + (height * 4), z);
			}

			// Doors
			world.setBlockAndMetadataWithNotify(x - 2, y, z - 6, Block.doorPlanksOakBottom.id, 4);
			world.setBlockAndMetadataWithNotify(x - 2, y + 1, z - 6, Block.doorPlanksOakTop.id, 4);
			world.setBlockAndMetadataWithNotify(x - 1, y, z - 6, Block.doorPlanksOakBottom.id, 1);
			world.setBlockAndMetadataWithNotify(x - 1, y + 1, z - 6, Block.doorPlanksOakTop.id, 1);

			world.setBlockAndMetadataWithNotify(x + 2, y, z - 6, Block.doorPlanksOakBottom.id, 1);
			world.setBlockAndMetadataWithNotify(x + 2, y + 1, z - 6, Block.doorPlanksOakTop.id, 1);
			world.setBlockAndMetadataWithNotify(x + 1, y, z - 6, Block.doorPlanksOakBottom.id, 4);
			world.setBlockAndMetadataWithNotify(x + 1, y + 1, z - 6, Block.doorPlanksOakTop.id, 4);

			// Ladder
			for (int _y = y; _y < y + 20; _y++) {
				world.setBlockAndMetadataWithNotify(x, _y, z + 5, Block.ladderOak.id, 2);
			}
			return true;
		}

		return false;
	}
}
