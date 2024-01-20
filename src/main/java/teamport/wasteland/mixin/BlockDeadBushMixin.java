package teamport.wasteland.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockDeadBush;
import net.minecraft.core.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockDeadBush.class, remap = false)
public abstract class BlockDeadBushMixin extends Block {
	public BlockDeadBushMixin(String key, int id, Material material) {
		super(key, id, material);
	}

	@Inject(method = "canThisPlantGrowOnThisBlockID", at = @At("TAIL"), cancellable = true)
	private void wasteland_canGrowOnDirt(int i, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(i == Block.dirt.id);
	}
}
