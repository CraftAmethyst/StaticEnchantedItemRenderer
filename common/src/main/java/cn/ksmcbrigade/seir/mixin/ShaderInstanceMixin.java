package cn.ksmcbrigade.seir.mixin;

import cn.ksmcbrigade.seir.interfaces.IUniform;
import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.client.renderer.ShaderInstance;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShaderInstance.class)
public abstract class ShaderInstanceMixin {

    @Shadow public abstract String getName();

    @Shadow @Final public Uniform TEXTURE_MATRIX;

    @Inject(method = {"getUniform"},at = @At(value = "RETURN"))
    private void update(String name, CallbackInfoReturnable<Uniform> cir){
        if(!this.getName().toLowerCase().contains("glint") || cir.getReturnValue()==null) return;
        if(TEXTURE_MATRIX!=null) ((IUniform) TEXTURE_MATRIX).staticEnchantedItemRenderer$setLocked(true);
    }
}
