package teamport.wasteland.core.world.biome;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.entity.animal.MobFireflyCluster;
import net.minecraft.core.entity.animal.MobWolf;
import net.minecraft.core.entity.monster.MobCreeper;
import net.minecraft.core.entity.monster.MobZombieArmored;
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
		this.setTopBlock(Blocks.DIRT.id());
		this.setFillerBlock(Blocks.DIRT.id());
		this.setSurfaceSnow();
		this.spawnableAmbientCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();

		this.spawnableCreatureList.add(new SpawnListEntry(MobWolf.class, 2));
		this.spawnableAmbientCreatureList.add(new SpawnListEntry(MobFireflyCluster.class, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(MobCreeper.class, 20));
		this.spawnableMonsterList.add(new SpawnListEntry(MobZombieArmored.class, 5));
	}

	@Override
	public int getSkyColor(float temperature) {
		return 0x000000;
	}

	@Override
	public WorldFeature getRandomWorldGenForTrees(Random random) {
		if (random.nextInt(4) != 0) {
			return new WorldFeatureTreeShrub(Blocks.LEAVES_SHRUB.id(), Blocks.LOG_OAK.id());
		} else {
			return random.nextInt(8) == 0 ? new WorldFeatureTreeTaigaTall(Blocks.LEAVES_PINE.id(), Blocks.LOG_PINE.id()) : new WorldFeatureTreeTaigaBushy(Blocks.LEAVES_PINE.id(), Blocks.LOG_PINE.id());
		}
	}
}
