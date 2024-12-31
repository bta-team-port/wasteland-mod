package teamport.wasteland.core.world.biome;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.entity.animal.EntityFireflyCluster;
import net.minecraft.core.entity.animal.EntityWolf;
import net.minecraft.core.entity.monster.EntityArmoredZombie;
import net.minecraft.core.entity.monster.EntityCreeper;
import net.minecraft.core.entity.monster.EntitySnowman;
import net.minecraft.core.entity.monster.EntitySpider;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeShrub;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeTaigaBushy;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeTaigaTall;

import java.util.Random;

public class BiomeWastesTaiga extends Biome {
	public BiomeWastesTaiga() {
		super("wastes_taiga");
		this.setColor(16775936);
		this.setTopBlock(Block.dirt.id);
		this.setFillerBlock(Block.dirt.id);
		this.setSurfaceSnow();
		this.spawnableAmbientCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();

		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 2));
		this.spawnableAmbientCreatureList.add(new SpawnListEntry(EntityFireflyCluster.class, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 20));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityArmoredZombie.class, 5));
	}

	@Override
	public int getSkyColor(float temperature) {
		return 0x000000;
	}

	@Override
	public WorldFeature getRandomWorldGenForTrees(Random random) {
		if (random.nextInt(4) != 0) {
			return new WorldFeatureTreeShrub(Block.leavesShrub.id, Block.leavesOak.id);
		} else {
			return random.nextInt(8) == 0 ? new WorldFeatureTreeTaigaTall(Block.leavesPine.id, Block.logPine.id) : new WorldFeatureTreeTaigaBushy(Block.leavesPine.id, Block.logPine.id);
		}
	}
}
