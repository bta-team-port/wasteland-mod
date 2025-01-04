package teamport.wasteland.core.world.generation;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntityChest;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldFeatureRuins extends WorldFeature {
	private ItemStack generateSurfaceLoot(Random rand) {
		switch (rand.nextInt(10)) {
			case 1: return new ItemStack(Items.INGOT_IRON, rand.nextInt(2) + 1);
			case 2: return new ItemStack(Items.FOOD_BREAD);
			case 3: return rand.nextInt(2) == 0 ? new ItemStack(Items.FOOD_STEW_MUSHROOM) : new ItemStack(Items.BOWL);
			case 4: return new ItemStack(Items.BUCKET);
			case 5: return new ItemStack(Items.STICK, rand.nextInt(4) + 1);
			case 6: return new ItemStack(Items.TOOL_AXE_WOOD, 1, rand.nextInt(63));
			case 7: return new ItemStack(Items.TOOL_PICKAXE_WOOD, 1, rand.nextInt(63));
			case 8: return new ItemStack(Items.TOOL_SWORD_WOOD, 1, rand.nextInt(63));
			case 9: return new ItemStack(Blocks.LOG_OAK);
			case 0: default: return null;
		}
	}

	private ItemStack generateBasementLoot(Random rand) {
		switch (rand.nextInt(11)) {
			case 1: return new ItemStack(Items.SULPHUR, rand.nextInt(4) + 1);
			case 2: return new ItemStack(Items.BONE, rand.nextInt(4) + 1);
			case 3: return new ItemStack(Items.STRING, rand.nextInt(4) + 1);
			case 4: return new ItemStack(Items.TOOL_BOW, 1, rand.nextInt(383));
			case 5: return new ItemStack(Items.AMMO_ARROW, rand.nextInt(8) + 1);
			case 6: return new ItemStack(Items.HANDCANNON_UNLOADED, 1, rand.nextInt(100));
			case 7: return new ItemStack(Items.AMMO_CHARGE_EXPLOSIVE);
			case 8: return rand.nextInt(3) == 0 ? new ItemStack(Items.BUCKET_WATER) : new ItemStack(Items.BUCKET);
			case 9: return rand.nextInt(100) == 0 ? new ItemStack(Items.FOOD_APPLE_GOLD) : null;
			case 10: return new ItemStack(Items.DUST_SUGAR, rand.nextInt(6) + 1);
			case 0: default: return null;
		}
	}

	private ItemStack generateFarmLoot(Random random) {
		switch (random.nextInt(17)) {
			case 1: return new ItemStack(Items.SEEDS_WHEAT, random.nextInt(3) + 1);
			case 2: return new ItemStack(Items.SEEDS_PUMPKIN, random.nextInt(3) + 1);
			case 3: return new ItemStack(Items.WHEAT, random.nextInt(3) + 1);
			case 4: return new ItemStack(Blocks.PUMPKIN);
			case 5: return new ItemStack(Items.FOOD_APPLE);
			case 6: return new ItemStack(Items.TOOL_SHEARS, 1, random.nextInt(255));
			case 7: return new ItemStack(Items.TOOL_HOE_WOOD, 1, random.nextInt(63));
			case 8: return new ItemStack(Items.TOOL_SHOVEL_WOOD, 1, random.nextInt(63));
			case 9: return new ItemStack(Blocks.MUSHROOM_BROWN, random.nextInt(2) + 1);
			case 10: return new ItemStack(Blocks.MUSHROOM_RED, random.nextInt(2) + 1);
			case 11: return new ItemStack(Items.BUCKET);
			case 12: return new ItemStack(Items.FOOD_CHERRY, random.nextInt(1));
			case 13: return new ItemStack(Items.SUGARCANE, random.nextInt(3) + 1);
			case 14: return random.nextInt(100) == 0 ? new ItemStack(Blocks.SAPLING_OAK) : null;
			case 15: return random.nextInt(200) == 0 ? new ItemStack(Blocks.SAPLING_CACAO) : null;
			case 16: return random.nextInt(200) == 0 ? new ItemStack(Blocks.SAPLING_CHERRY) : null;
			case 0: default: return null;
		}
	}

	private void generateRuins1(World world, Random random, int x, int y, int z) {
		// Foundation
		for (int _x = x - 3; _x < x + 4; _x++) {
			for (int _y = y - 6; _y < y - 1; _y++) {
				for (int _z = z - 3; _z < z + 4; _z++) {
					world.setBlockWithNotify(_x, _y, _z, Blocks.STONE.id());
				}
			}
		}

		// Floor
		for (int _x = x - 3; _x < x + 4; _x++) {
			for (int _z = z - 3; _z < z + 4; _z++) {
				world.setBlockWithNotify(_x, y - 1, _z, random.nextInt(2) != 0 ? Blocks.COBBLE_STONE_MOSSY.id() : Blocks.COBBLE_STONE.id());
			}
		}

		// Walls
		for (int _x = x - 3; _x < x + 4; _x++) {
			for (int _y = y; _y < y + 3; _y++) {
				for (int _z = z - 3; _z < z + 4; _z++) {
					if (random.nextInt(3) != 0) {
						switch (random.nextInt(4)) {
							case 1:
								world.setBlockWithNotify(_x, _y, _z, Blocks.COBBLE_STONE_MOSSY.id());
								break;
							case 2:
								world.setBlockWithNotify(_x, _y, _z, Blocks.PLANKS_OAK.id());
								break;
							case 3:
								world.setBlockWithNotify(_x, y + 1, _z, Blocks.GLASS.id());
								break;
							case 0:
							default:
								world.setBlockWithNotify(_x, _y, _z, Blocks.COBBLE_STONE.id());
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
		world.setBlockWithNotify(x - 2 + _x, y - 5, z - 2 + _z, Blocks.PRESSURE_PLATE_COBBLE_STONE.id());
		for (int tX = -1; tX < 1; tX++) {
			for (int tZ = -1; tZ < 1; tZ++) {
				world.setBlockWithNotify(x - 2 + _x + tX, y - 7, z - 2 + _z + tZ, Blocks.TNT.id());
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
					world.setBlockWithNotify(_x, _y, _z, Blocks.MUD_BAKED.id());
				}
			}
		}

		// 'Farm' Ground
		for (int _x = x - 2; _x < x + 3; _x++) {
			for (int _z = z + 4; _z < z + 8; _z++) {
				world.setBlockWithNotify(_x, y - 2, _z, Blocks.MUD.id());
			}
		}

		// Fence
		for (int _x = x - 3; _x < x + 4; _x++) {
			for (int _z = z + 4; _z < z + 9; _z++) {
				world.setBlockWithNotify(_x, y - 1, _z, Blocks.FENCE_PLANKS_OAK.id());
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
	public boolean place(World world, Random random, int x, int y, int z) {
		if (y < world.getHeightBlocks() && world.getBlock(x, y - 1, z) == Blocks.MUD_BAKED && world.getBlock(x, y, z) == null) {
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

					world.setBlockAndMetadataWithNotify(x, y - 5, z - 2, Blocks.CHEST_PLANKS_OAK.id(), 2);
					TileEntityChest tile = (TileEntityChest) world.getTileEntity(x, y - 5, z - 2);

					for (int i = 0; i < 4; ++i) {
						ItemStack stack = generateBasementLoot(random);
						if (stack != null && tile != null) {
							tile.setItem(random.nextInt(tile.getContainerSize()), stack);
						}
					}
				}

				// Farm
				if (random.nextInt(8) == 0) {
					this.generateRuinsFarm(world, random, x, y, z);

					world.setBlockAndMetadataWithNotify(x + 2, y - 1, z + 4, Blocks.CHEST_PLANKS_OAK.id(), 3);
					TileEntityChest tile = (TileEntityChest) world.getTileEntity(x + 2, y - 1, z + 4);

					for (int i = 0; i < 3; ++i) {
						ItemStack stack = this.generateFarmLoot(random);
						if (stack != null && tile != null) {
							tile.setItem(random.nextInt(tile.getContainerSize()), stack);
						}
					}
				}

				// House Chest
				world.setBlockWithNotify(x, y, z + 2, Blocks.CHEST_PLANKS_OAK.id());
				TileEntityChest tile = (TileEntityChest) world.getTileEntity(x, y, z + 2);

				for (int i = 0; i < 3; ++i) {
					ItemStack stack = this.generateSurfaceLoot(random);
					if (stack != null && tile != null) {
						tile.setItem(random.nextInt(tile.getContainerSize()), stack);
					}
				}

				return true;
			}
		return false;
	}
}
