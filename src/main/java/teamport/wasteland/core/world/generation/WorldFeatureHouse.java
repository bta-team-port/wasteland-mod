package teamport.wasteland.core.world.generation;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityChest;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class WorldFeatureHouse extends WorldFeature {
	private ItemStack generateBasementCraftingLoot(Random rand) {
		switch (rand.nextInt(13)) {
			case 1: return new ItemStack(Item.sulphur, rand.nextInt(4) + 1);
			case 3: return new ItemStack(Item.string, rand.nextInt(4) + 1);
			case 4: return new ItemStack(Item.toolBow, 1, rand.nextInt(383));
			case 5: return new ItemStack(Item.ammoArrow, rand.nextInt(8) + 1);
			case 6: return new ItemStack(Item.handcannonUnloaded, 1, rand.nextInt(100) + 33);
			case 7: return new ItemStack(Item.ammoChargeExplosive);
			case 8: return new ItemStack(Item.book, rand.nextInt(3) +  1);
			case 9: return new ItemStack(Item.armorHelmetLeather, 1, rand.nextInt(128) + 64);
			case 10: return new ItemStack(Item.armorChestplateLeather, 1, rand.nextInt(128) + 64);
			case 11: return new ItemStack(Item.armorLeggingsLeather, 1, rand.nextInt(128) + 64);
			case 12: return new ItemStack(Item.armorBootsLeather, 1, rand.nextInt(128) + 64);
			case 0: default: return null;
		}
	}

	private ItemStack generateBasementFurnaceLoot(Random rand) {
		switch (rand.nextInt(10)) {
			case 9: return rand.nextInt(3) == 0 ? new ItemStack(Item.bucketWater, 1) : new ItemStack(Item.bucket, 1);
			case 8: return new ItemStack(Block.logOak, rand.nextInt(3) + 1);
			case 7: return new ItemStack(Item.stick, rand.nextInt(8) + 2);
			case 6: return rand.nextInt(2) == 0 ? new ItemStack(Item.foodStewMushroom, 1) : new ItemStack(Item.bowl, 1);
			case 5: return new ItemStack(Item.foodBread, rand.nextInt(2) + 1);
			case 4: return new ItemStack(Item.foodPorkchopRaw, 1);
			case 3: return new ItemStack(Item.foodFishRaw, 1);
			case 2: return rand.nextInt(2) == 0 ? new ItemStack(Block.blockCoal, 1) : null;
			case 1: return new ItemStack(Item.coal, rand.nextInt(4) + 1);
			case 0: default: return null;
		}
	}

	private ItemStack generateSurfaceFurnaceLoot(Random rand) {
		switch (rand.nextInt(8)) {
			case 7: return new ItemStack(Item.bucket);
			case 6: return new ItemStack(Item.stick, rand.nextInt(8) + 2);
			case 5: return rand.nextInt(2) == 0 ? new ItemStack(Item.foodStewMushroom, 1) : new ItemStack(Item.bowl, 1);
			case 4: return new ItemStack(Item.foodBread, rand.nextInt(2) + 1);
			case 3: return new ItemStack(Item.foodPorkchopRaw, 1);
			case 2: return new ItemStack(Item.foodFishRaw, 1);
			case 1: return new ItemStack(Item.coal, rand.nextInt(2) + 1);
			case 0: default: return null;
		}
	}

	private void generateRoom(World world, Random rand, int x, int y, int z) {
		// Walls
		for (int _x = x - 3; _x < x + 4; _x++) {
			for (int _y = y + 1; _y < y + 4; _y++) {
				for (int _z = z - 3; _z < z + 4; _z++) {
					world.setBlockWithNotify(_x, y, _z, Block.cobbleStone.id);
					world.setBlockWithNotify(_x, _y, _z, rand.nextInt(3) != 0 ? Block.planksOak.id : 0);

					// Corners
					world.setBlockWithNotify(x + 3, _y, z + 3, Block.logOak.id);
					world.setBlockWithNotify(x + 3, _y, z - 3, Block.logOak.id);
					world.setBlockWithNotify(x - 3, _y, z + 3, Block.logOak.id);
					world.setBlockWithNotify(x - 3, _y, z - 3, Block.logOak.id);
				}
			}
		}

		// Empty
		for (int _x = x - 2; _x < x + 3; _x++) {
			for (int _y = y + 1; _y < y + 4; _y++) {
				for (int _z = z - 2; _z < z + 3; _z++) {
					world.setBlockWithNotify(_x, y, _z, 0);
					world.setBlockWithNotify(_x, _y, _z, 0);
				}
			}
		}
	}

	public void generateBasement(World world, Random rand, int x, int y, int z) {
		// Air
		for (int _x = x - 2; _x < x + 3; _x++) {
			for (int _y = y; _y < y + 4; _y++) {
				for (int _z = z - 2; _z < z + 3; _z++) {
					world.setBlockWithNotify(_x, _y, _z, 0);
					world.setBlockAndMetadataWithNotify(x - 2, _y, z - 2, Block.ladderOak.id, 5);
				}
			}
		}

		// DECORATION //
		// Bookshelves
		for (int _y = y; _y < y + 4; _y++) {
			world.setBlockWithNotify(x - 2, _y, z + 2, Block.bookshelfPlanksOak.id);
			world.setBlockWithNotify(x - 1, _y, z + 2, Block.bookshelfPlanksOak.id);
		}

		// Furnace
		world.setBlockAndMetadataWithNotify(x + 1, y, z - 3, Block.furnaceStoneIdle.id, 3);

		// Chests
		world.setBlockAndMetadataWithNotify(x + 1, y, z + 2, Block.chestPlanksOak.id, 0);
		TileEntityChest tile1 = (TileEntityChest) world.getBlockTileEntity(x + 1, y, z + 2);
		for (int i = 0; i < 5; ++i) {
			ItemStack stack = generateBasementCraftingLoot(rand);

			if (tile1 != null && stack != null) {
				tile1.setInventorySlotContents(rand.nextInt(tile1.getSizeInventory()), stack);
			}
		}

		world.setBlockAndMetadataWithNotify(x + 1, y - 1, z - 2, Block.chestPlanksOak.id, 2);
		TileEntityChest tile2 = (TileEntityChest) world.getBlockTileEntity(x + 1, y - 1, z - 2);
		for (int i = 0; i < 3; ++i) {
			ItemStack stack = generateBasementFurnaceLoot(rand);

			if (tile2 != null && stack != null) {
				tile2.setInventorySlotContents(rand.nextInt(tile2.getSizeInventory()), stack);
			}
		}

		// Workbench
		world.setBlockWithNotify(x + 2, y, z + 2, Block.workbench.id);

		// Lamp
		world.setBlockAndMetadataWithNotify(x + 3, y + 1, z, Block.lampIdle.id, 0);
		world.setBlockAndMetadataWithNotify(x + 2, y + 1, z, Block.leverCobbleStone.id, 2);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (y < world.getHeightBlocks() && world.getBlock(x, y - 1, z) == Block.mudBaked && world.getBlock(x, y, z) == null) {
			// Floor and Foundation
			for (int _x = x - 6; _x < x + 7; _x++) {
				for (int _y = y - 8; _y < y - 1; _y++) {
					for (int _z = z - 6; _z < z + 7; _z++) {
						world.setBlockWithNotify(_x, y - 1, _z, rand.nextInt(3) != 0 ? Block.cobbleStoneMossy.id : Block.cobbleStone.id);
						world.setBlockWithNotify(_x, _y, _z, Block.stone.id);
					}
				}
			}

			// BASE ROOMS //
			generateRoom(world, rand, x + 3, y, z + 3);
			generateRoom(world, rand, x - 3, y, z + 3);
			generateRoom(world, rand, x - 3, y, z - 3);

			// Remove the wall
			for (int _x = x - 5; _x < x; _x++) {
				for (int _y = y; _y < y + 5; _y++) {
					world.setBlockWithNotify(_x, _y, z, 0);
				}
			}

			if (rand.nextInt(3) == 0) {
				generateBasement(world, rand, x + 3, y - 5, z + 3);
			}

			// DOORS //
			world.setBlockAndMetadataWithNotify(x + 3, y, z, Block.doorPlanksOakBottom.id, 12);
			world.setBlockAndMetadataWithNotify(x + 3, y + 1, z, Block.doorPlanksOakTop.id, 12);
			world.setBlockWithNotify(x, y, z + 3, 0);
			world.setBlockWithNotify(x, y + 1, z + 3, 0);

			// 'BEDROOM' //
			for (int _y = y + 1; _y < y + 4; _y++) {
				for (int _z = z + 2; _z < z + 6; _z++) {
					world.setBlockWithNotify(x - 3, y, _z, Block.cobbleStone.id);
					world.setBlockWithNotify(x - 3, _y, _z, rand.nextInt(3) != 0 ? Block.planksOak.id : 0);
				}
			}

			// Beds
			if (rand.nextInt(100) == 0) {
				world.setBlockAndMetadataWithNotify(x - 4, y, z + 5, Block.bed.id, 8);
				world.setBlockAndMetadataWithNotify(x - 4, y, z + 4, Block.bed.id, 0);
			}
			if (rand.nextInt(100) == 0) {
				world.setBlockAndMetadataWithNotify(x - 5, y, z + 5, Block.bed.id, 8);
				world.setBlockAndMetadataWithNotify(x - 5, y, z + 4, Block.bed.id, 0);
			}

			// KITCHEN //
			if (rand.nextInt(2) == 0) {
				world.setBlockAndMetadataWithNotify(x - 2, y, z - 5, Block.furnaceStoneIdle.id, 3);
			}

			world.setBlockAndMetadataWithNotify(x - 3, y, z - 5, Block.chestPlanksOak.id, 2);
			TileEntityChest tile3 = (TileEntityChest) world.getBlockTileEntity(x - 3, y, z - 5);

			for (int i = 0; i < 3; i++) {
				ItemStack stack = generateSurfaceFurnaceLoot(rand);

				if (tile3 != null && stack != null) {
					tile3.setInventorySlotContents(rand.nextInt(tile3.getSizeInventory()), stack);
				}
			}

			if (rand.nextInt(2) == 0) {
				world.setBlockWithNotify(x - 4, y, z - 5, Block.workbench.id);
			}

			return true;
		}
		return false;
	}
}
