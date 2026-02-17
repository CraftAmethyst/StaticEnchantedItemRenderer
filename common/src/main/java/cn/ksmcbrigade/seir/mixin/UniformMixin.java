package cn.ksmcbrigade.seir.mixin;

import cn.ksmcbrigade.seir.interfaces.IUniform;
import com.mojang.blaze3d.shaders.Uniform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Uniform.class)
public abstract class UniformMixin implements IUniform {
    @Shadow public abstract String getName();

    @Unique
    private boolean staticEnchantedItemRenderer$locked = false;

    @Unique
    private boolean staticEnchantedItemRenderer$set = false;

    @Inject(method = "set(Lorg/joml/Matrix4f;)V",at = @At("HEAD"),cancellable = true)
    public void lock(CallbackInfo ci){
        if(!getName().equals("TextureMat")) return;
        if(staticEnchantedItemRenderer$locked && staticEnchantedItemRenderer$set) ci.cancel();
        staticEnchantedItemRenderer$set = true;
    }

    @Unique
    @Override
    public void staticEnchantedItemRenderer$setLocked(boolean value) {
        this.staticEnchantedItemRenderer$locked = value;
    }
}
