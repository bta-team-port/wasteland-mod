package teamport.wasteland.extra.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFluid;
import net.minecraft.core.block.BlockLogicFluidFlowing;
import net.minecraft.core.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamport.wasteland.WastelandConfig;

@Mixin(value = BlockLogicFluidFlowing.class, remap = false)
public abstract class BlockLogicFluidFlowingMixin extends BlockLogicFluid {
	public BlockLogicFluidFlowingMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Redirect(method = "updateTick", at = @At(value = "INVOKE", target = "net/minecraft/core/block/material/Material.isSolid ()Z"))
	private boolean wasteland_finiteWater(Material instance) {
        return !WastelandConfig.cfg.getBoolean("Config.finiteWater");
    }
}
