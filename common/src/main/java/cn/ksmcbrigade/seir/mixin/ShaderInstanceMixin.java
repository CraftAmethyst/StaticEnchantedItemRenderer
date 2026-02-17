package cn.ksmcbrigade.seir.mixin;

import cn.ksmcbrigade.seir.interfaces.IUniform;
import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.client.renderer.ShaderInstance;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShaderInstance.class)
public abstract class ShaderInstanceMixin {

    @Shadow public abstract String getName();

    @Shadow @Final public Uniform TEXTURE_MATRIX;

    @Inject(method = {"setDefaultUniforms"},at = @At(value = "TAIL"))
    private void update(CallbackInfo ci){
        if(!this.getName().toLowerCase().contains("glint") || TEXTURE_MATRIX==null) return;
        ((IUniform) TEXTURE_MATRIX).staticEnchantedItemRenderer$setLocked(true);
    }
}
