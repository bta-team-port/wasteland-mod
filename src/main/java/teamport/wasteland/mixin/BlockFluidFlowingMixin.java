package teamport.wasteland.mixin;

import net.minecraft.core.block.BlockFluid;
import net.minecraft.core.block.BlockFluidFlowing;
import net.minecraft.core.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamport.wasteland.WastelandConfig;

@Mixin(value = BlockFluidFlowing.class, remap = false)
public abstract class BlockFluidFlowingMixin extends BlockFluid {
	public BlockFluidFlowingMixin(String key, int id, Material material) {
		super(key, id, material);
	}

	@Redirect(method = "updateTick", at = @At(value = "INVOKE", target = "net/minecraft/core/block/material/Material.isSolid ()Z"))
	private boolean wasteland_finiteWater(Material instance) {
        return !WastelandConfig.cfg.getBoolean("Config.FiniteWater");
    }
}
