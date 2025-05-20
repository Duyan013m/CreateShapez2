package github.duyan013m.create_shapez.mixin;

import com.simibubi.create.content.kinetics.crusher.CrushingWheelControllerBlockEntity;
import com.simibubi.create.content.processing.recipe.ProcessingInventory;
import github.duyan013m.create_shapez.util.ShapezRecipeHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrushingWheelControllerBlockEntity.class)
public class CrushingWheelControllerBlockEntityMixin {
    @Shadow(remap = false)
    public ProcessingInventory inventory;

    @Inject(method = "applyRecipe", at = @At("HEAD"), remap = false, cancellable = true)
    private void applyRecipe(CallbackInfo ci) {
        if(ShapezRecipeHelper.cCheck(inventory)){
            ShapezRecipeHelper.cCraft(inventory);
            ci.cancel();
        }
//        ItemStack stack = inventory.getStackInSlot(0);
//        if (stack.isOf(Items.CRAFTING_TABLE)) {
//            inventory.setStackInSlot(0, new ItemStack(Items.FURNACE, stack.getCount()));
//            ci.cancel();
//        }
    }
}
