package teamport.wasteland.extra.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicDeadBush;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockLogicDeadBush.class, remap = false)
public abstract class BlockLogicDeadBushMixin extends BlockLogic {
	public BlockLogicDeadBushMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Inject(method = "mayPlaceOn", at = @At("TAIL"), cancellable = true)
	private void wasteland_canGrowOnDirt(int i, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(i == Blocks.SAND.id() || i == Blocks.DIRT_SCORCHED.id() || i == Blocks.MUD_BAKED.id());
	}
}
