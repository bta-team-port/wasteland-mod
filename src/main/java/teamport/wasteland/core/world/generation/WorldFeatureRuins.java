package teamport.wasteland.core.world.generation;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityChest;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldFeatureRuins extends WorldFeature {
	private ItemStack generateSurfaceLoot(Random random) {
		switch (random.nextInt(10)) {
			case 1: return new ItemStack(Item.ingotIron, random.nextInt(4) + 1);
			case 2: return new ItemStack(Item.foodBread);
			case 3: return random.nextInt(2) == 0 ? new ItemStack(Item.foodStewMushroom) : new ItemStack(Item.bowl);
			case 4: return new ItemStack(Item.bucket);
			case 5: return new ItemStack(Item.stick, random.nextInt(4) + 1);
			case 6: return new ItemStack(Item.toolAxeWood, 1, random.nextInt(63));
			case 7: return new ItemStack(Item.toolPickaxeWood, 1, random.nextInt(63));
			case 8: return new ItemStack(Item.toolSwordWood, 1, random.nextInt(63));
			case 9: return new ItemStack(Block.logOak);
			case 0: default: return null;
		}
	}

	private ItemStack generateBasementLoot(Random random) {
		switch (random.nextInt(11)) {
			case 1: return new ItemStack(Item.sulphur, random.nextInt(4) + 1);
			case 2: return new ItemStack(Item.bone, random.nextInt(4) + 1);
			case 3: return new ItemStack(Item.string, random.nextInt(4) + 1);
			case 4: return new ItemStack(Item.toolBow, 1, random.nextInt(383));
			case 5: return new ItemStack(Item.ammoArrow, random.nextInt(8) + 1);
			case 6: return new ItemStack(Item.handcannonUnloaded, 1, random.nextInt(100));
			case 7: return new ItemStack(Item.ammoChargeExplosive);
			case 8: return new ItemStack(Item.bucketWater);
			case 9: return random.nextInt(100) == 0 ? new ItemStack(Item.foodAppleGold) : null;
			case 10: return new ItemStack(Item.dustSugar, random.nextInt(6) + 1);
			case 0: default: return null;
		}
	}

	private ItemStack generateFarmLoot(Random random) {
		switch (random.nextInt(17)) {
			case 1: return new ItemStack(Item.seedsWheat, random.nextInt(3) + 1);
			case 2: return new ItemStack(Item.seedsPumpkin, random.nextInt(3) + 1);
			case 3: return new ItemStack(Item.wheat, random.nextInt(3) + 1);
			case 4: return new ItemStack(Block.pumpkin);
			case 5: return new ItemStack(Item.foodApple);
			case 6: return new ItemStack(Item.toolShears, 1, random.nextInt(255));
			case 7: return new ItemStack(Item.toolHoeWood, 1, random.nextInt(63));
			case 8: return new ItemStack(Item.toolShovelWood, 1, random.nextInt(63));
			case 9: return new ItemStack(Block.mushroomBrown, random.nextInt(2) + 1);
			case 10: return new ItemStack(Block.mushroomRed, random.nextInt(2) + 1);
			case 11: return new ItemStack(Item.bucket);
			case 12: return new ItemStack(Item.foodCherry, random.nextInt(1));
			case 13: return new ItemStack(Item.sugarcane, random.nextInt(3) + 1);
			case 14: return random.nextInt(100) == 0 ? new ItemStack(Block.saplingOak) : null;
			case 15: return random.nextInt(200) == 0 ? new ItemStack(Block.saplingCacao) : null;
			case 16: return random.nextInt(200) == 0 ? new ItemStack(Block.saplingCherry) : null;
			case 0: default: return null;
		}
	}

	private void generateRuins1(World world, Random random, int x, int y, int z) {
		// Foundation
		for (int _x = x - 3; _x < x + 4; _x++) {
			for (int _y = y - 6; _y < y - 1; _y++) {
				for (int _z = z - 3; _z < z + 4; _z++) {
					world.setBlockWithNotify(_x, _y, _z, Block.stone.id);
				}
			}
		}

		// Floor
		for (int _x = x - 3; _x < x + 4; _x++) {
			for (int _z = z - 3; _z < z + 4; _z++) {
				if (random.nextInt(2) != 0) {
					world.setBlockWithNotify(_x, y - 1, _z, Block.cobbleStoneMossy.id);
				} else world.setBlockWithNotify(_x, y - 1, _z, Block.cobbleStone.id);
			}
		}

		// Walls
		for (int _x = x - 3; _x < x + 4; _x++) {
			for (int _y = y; _y < y + 3; _y++) {
				for (int _z = z - 3; _z < z + 4; _z++) {
					if (random.nextInt(3) != 0) {
						switch (random.nextInt(4)) {
							case 1:
								world.setBlockWithNotify(_x, _y, _z, Block.cobbleStoneMossy.id);
								break;
							case 2:
								world.setBlockWithNotify(_x, _y, _z, Block.planksOak.id);
								break;
							case 3:
								world.setBlockWithNotify(_x, y + 1, _z, Block.glass.id);
								break;
							case 0:
							default:
								world.setBlockWithNotify(_x, _y, _z, Block.cobbleStone.id);
								break;
						}
					} else world.setBlockWithNotify(_x, _y, _z, 0);
				}
			}
		}
		for (int _x = x - 2; _x < x + 3; _x++) {
			for (int _y = y; _y < y + 3; _y++) {
				for (int _z = z - 2; _z < z + 3; _z++) {
					world.setBlockWithNotify(_x, _y, _z, 0);
				}
			}
		}
	}

	private void generateBasementTrap(World world, Random random, int x, int y, int z) {
		int _x = random.nextInt(5);
		int _z = random.nextInt(5);
		world.setBlockWithNotify(x - 2 + _x, y - 5, z - 2 + _z, Block.pressureplateCobbleStone.id);
		for (int tX = -1; tX < 1; tX++) {
			for (int tZ = -1; tZ < 1; tZ++) {
				world.setBlockWithNotify(x - 2 + _x + tX, y - 7, z - 2 + _z + tZ, Block.tnt.id);
			}
		}
	}

	private void generateRuinsBasement(World world, Random random, int x, int y, int z) {
		// Empty Space
		for (int _x = x - 2; _x < x + 3; _x++) {
			for (int _y = y - 5; _y < y - 1; _y++) {
				for (int _z = z - 2; _z < z + 3; _z++) {
					world.setBlockWithNotify(_x, _y, _z, 0);
				}
			}
		}

		this.generateBasementTrap(world, random, x, y, z);
	}

	private void generateRuinsFarm(World world, Random random, int x, int y, int z) {
		// Open Air
		for (int _x = x - 3; _x < x + 5; _x++) {
			for (int _y = y - 1; _y < y + 5; _y++) {
				for (int _z = z + 4; _z < z + 9; _z++) {
					world.setBlockWithNotify(_x, _y, _z, 0);
				}
			}
		}

		// Dirt Foundation
		for (int _x = x - 3; _x < x + 5; _x++) {
			for (int _y = y - 6; _y < y - 1; _y++) {
				for (int _z = z + 4; _z < z + 9; _z++) {
					world.setBlockWithNotify(_x, _y, _z, Block.mudBaked.id);
				}
			}
		}

		// 'Farm' Ground
		for (int _x = x - 2; _x < x + 3; _x++) {
			for (int _z = z + 4; _z < z + 8; _z++) {
				world.setBlockWithNotify(_x, y - 2, _z, Block.mud.id);
			}
		}

		// Fence
		for (int _x = x - 3; _x < x + 4; _x++) {
			for (int _z = z + 4; _z < z + 9; _z++) {
				world.setBlockWithNotify(_x, y - 1, _z, Block.fencePlanksOak.id);
			}
		}
		for (int _x = x - 2; _x < x + 3; _x++) {
			for (int _y = y - 1; _y < y; _y++) {
				for (int _z = z + 4; _z < z + 8; _z++) {
					world.setBlockWithNotify(_x, _y, _z, 0);
				}
			}
		}
	}

	// Keep chests OUT of the check! This is because they will spit out over an inventory of items.
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		if (y < world.getHeightBlocks() && world.getBlock(x, y - 1, z) == Block.mudBaked && world.getBlock(x, y, z) == null) {
				for (int _x = x - 3; _x < x + 4; _x++) {
					for (int _y = y; _y < y + 2; _y++) {
						for (int _z = z - 3; _z < z + 4; _z++) {
							if (world.getBlockId(_x, _y, _z) == 0) {
								this.generateRuins1(world, random, x, y, z);
							}
						}
					}
				}

				// Basement
				if (random.nextInt(3) == 0) {
					this.generateRuinsBasement(world, random, x, y, z);

					world.setBlockAndMetadataWithNotify(x, y - 5, z - 2, Block.chestPlanksOak.id, 2);
					TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(x, y - 5, z - 2);

					for (int i = 0; i < 4; ++i) {
						ItemStack stack = this.generateBasementLoot(random);
						if (stack != null && tile != null) {
							tile.setInventorySlotContents(random.nextInt(tile.getSizeInventory()), stack);
						}
					}
				}

				// Farm
				if (random.nextInt(8) == 0) {
					this.generateRuinsFarm(world, random, x, y, z);

					world.setBlockAndMetadataWithNotify(x + 2, y - 1, z + 4, Block.chestPlanksOak.id, 3);
					TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(x + 2, y - 1, z + 4);

					for (int i = 0; i < 3; ++i) {
						ItemStack stack = this.generateFarmLoot(random);
						if (stack != null && tile != null) {
							tile.setInventorySlotContents(random.nextInt(tile.getSizeInventory()), stack);
						}
					}
				}

				// House Chest
				world.setBlockWithNotify(x, y, z + 2, Block.chestPlanksOak.id);
				TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(x, y, z + 2);

				for (int i = 0; i < 3; ++i) {
					ItemStack stack = this.generateSurfaceLoot(random);
					if (stack != null && tile != null) {
						tile.setInventorySlotContents(random.nextInt(tile.getSizeInventory()), stack);
					}
				}

				return true;
			}
		return false;
	}
}
