package github.duyan013m.create_shapez.mixin.client;

import github.duyan013m.create_shapez.ShapezItems;
import github.duyan013m.create_shapez.util.client.ShapezModelHelper;
import github.duyan013m.create_shapez.util.ShapezItemHelper;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemModels.class)
public class ItemModelsMixin {
    @Inject(method = "getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;", at = @At("RETURN"), remap = false, cancellable = true)
    public void getModel(ItemStack stack, CallbackInfoReturnable<BakedModel> cir) {
        if (stack.isOf(ShapezItems.Shapez2)) {
            cir.setReturnValue(ShapezModelHelper.get(ShapezItemHelper.getName(stack)));
            cir.cancel();
        }
    }
}
