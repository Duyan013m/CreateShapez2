package github.duyan013m.create_shapez.mixin.client;

import github.duyan013m.create_shapez.CreateShapezClient;
import github.duyan013m.create_shapez.util.client.ShapezBakerImpl;
import github.duyan013m.create_shapez.ShapezIdentifier;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiFunction;

@Mixin(ModelLoader.class)
public class ModelLoaderMixin {
    @Inject(method = "bake", at = @At("HEAD"), remap = false)
    public void bake(BiFunction<Identifier, SpriteIdentifier, Sprite> spriteLoader, CallbackInfo ci) {
        CreateShapezClient.bakerImpl = new ShapezBakerImpl(spriteLoader, (ModelLoader) (Object) this, new ModelIdentifier(ShapezIdentifier.Shapez2, "inventory"));
    }
}
