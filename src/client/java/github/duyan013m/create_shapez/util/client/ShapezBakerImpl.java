package github.duyan013m.create_shapez.util.client;

import net.minecraft.client.render.model.*;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ShapezBakerImpl implements Baker {
    private final Function<SpriteIdentifier, Sprite> textureGetter;
    private final ModelLoader modelLoader;
    private final Identifier modelId;

    public ShapezBakerImpl(BiFunction<Identifier, SpriteIdentifier, Sprite> spriteLoader, ModelLoader modelLoader, Identifier modelId) {
        this.textureGetter = (spriteId) -> (Sprite) spriteLoader.apply(modelId, spriteId);
        this.modelLoader = modelLoader;
        this.modelId = modelId;
    }

    public UnbakedModel getOrLoadModel(Identifier id) {
        return this.modelLoader.getOrLoadModel(id);
    }

    @Deprecated
    @Nullable
    @Override
    public BakedModel bake(Identifier id, ModelBakeSettings settings) {
        throw new RuntimeException();
    }

    public BakedModel bake(UnbakedModel unbakedModel) {
        unbakedModel.setParents(this.modelLoader::getOrLoadModel);
        return unbakedModel.bake(this,this.textureGetter, ModelRotation.X0_Y0, this.modelId);
    }
}
