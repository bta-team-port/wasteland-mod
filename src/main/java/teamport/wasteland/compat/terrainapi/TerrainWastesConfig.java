package teamport.wasteland.compat.terrainapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import useless.terrainapi.config.OreConfig;
import useless.terrainapi.util.Utilities;

import javax.annotation.Nullable;
import java.util.HashMap;

public class TerrainWastesConfig extends OreConfig {
	@Expose
	@SerializedName("Biome Random Grass Block")
	public HashMap<String, String> biomeRandomGrassBlock = new HashMap();
	@Expose
	@SerializedName("Grass Density")
	public HashMap<String, Integer> grassDensityMap = new HashMap();
	@Expose
	@SerializedName("Flower Density")
	public HashMap<String, Integer> flowerDensityMap = new HashMap();
	@Expose
	@SerializedName("Yellow Flower Density")
	public HashMap<String, Integer> yellowFlowerDensityMap = new HashMap();
	@Expose
	@SerializedName("Tree Density")
	public HashMap<String, Integer> treeDensityMap = new HashMap();
	@Expose
	@SerializedName("Lake Density")
	public HashMap<String, Integer> lakeDensityMap = new HashMap();
	public int defaultLakeDensity = 4;

	public void addRandomGrassBlock(Biome biome, Block block) {
		if (!this.getConfigOverride() || this.getRandomGrassBlock(biome) == null) {
			this.biomeRandomGrassBlock.put(Registries.BIOMES.getKey(biome), block.getKey());
		}
	}

	@Nullable
	public Block getRandomGrassBlock(Biome biome) {
		return Utilities.getBlock(this.biomeRandomGrassBlock.get(Registries.BIOMES.getKey(biome)));
	}

	@NotNull
	public Block getRandomGrassBlock(Biome biome, Block defaultValue) {
		Block returnBlock = Utilities.getBlock(this.biomeRandomGrassBlock.get(Registries.BIOMES.getKey(biome)));
		if (returnBlock == null) {
			returnBlock = defaultValue;
		}

		return returnBlock;
	}

	public void addGrassDensity(Biome biome, int density) {
		if (!this.getConfigOverride() || this.getGrassDensity(biome) == null) {
			this.grassDensityMap.put(Registries.BIOMES.getKey(biome), density);
		}
	}

	@Nullable
	public Integer getGrassDensity(Biome biome) {
		return this.grassDensityMap.get(Registries.BIOMES.getKey(biome));
	}

	@NotNull
	public Integer getGrassDensity(Biome biome, int defaultValue) {
		return this.grassDensityMap.getOrDefault(Registries.BIOMES.getKey(biome), defaultValue);
	}

	public void addFlowerDensity(Biome biome, int density) {
		if (!this.getConfigOverride() || this.getFlowerDensity(biome) == null) {
			this.flowerDensityMap.put(Registries.BIOMES.getKey(biome), density);
		}
	}

	@Nullable
	public Integer getFlowerDensity(Biome biome) {
		return this.flowerDensityMap.get(Registries.BIOMES.getKey(biome));
	}

	@NotNull
	public Integer getFlowerDensity(Biome biome, int defaultValue) {
		return this.flowerDensityMap.getOrDefault(Registries.BIOMES.getKey(biome), defaultValue);
	}

	public void addYellowFlowerDensity(Biome biome, int density) {
		if (!this.getConfigOverride() || this.getYellowFlowerDensity(biome) == null) {
			this.yellowFlowerDensityMap.put(Registries.BIOMES.getKey(biome), density);
		}
	}

	@Nullable
	public Integer getYellowFlowerDensity(Biome biome) {
		return this.yellowFlowerDensityMap.get(Registries.BIOMES.getKey(biome));
	}

	@NotNull
	public Integer getYellowFlowerDensity(Biome biome, int defaultValue) {
		return this.yellowFlowerDensityMap.getOrDefault(Registries.BIOMES.getKey(biome), defaultValue);
	}

	public void addTreeDensity(Biome biome, int density) {
		if (!this.getConfigOverride() || this.getTreeDensity(biome) == null) {
			this.treeDensityMap.put(Registries.BIOMES.getKey(biome), density);
		}
	}

	@Nullable
	public Integer getTreeDensity(Biome biome) {
		return this.treeDensityMap.get(Registries.BIOMES.getKey(biome));
	}

	@NotNull
	public Integer getTreeDensity(Biome biome, int defaultValue) {
		return this.treeDensityMap.getOrDefault(Registries.BIOMES.getKey(biome), defaultValue);
	}

	public void addLakeDensity(Biome biome, int density) {
		if (!this.getConfigOverride() || this.getTreeDensity(biome) == null) {
			this.lakeDensityMap.put(Registries.BIOMES.getKey(biome), density);
		}
	}

	@Nullable
	public Integer getLakeDensity(Biome biome) {
		return this.lakeDensityMap.get(Registries.BIOMES.getKey(biome));
	}

	@NotNull
	public Integer getLakeDensity(Biome biome, int defaultValue) {
		return this.lakeDensityMap.getOrDefault(Registries.BIOMES.getKey(biome), defaultValue);
	}
}
