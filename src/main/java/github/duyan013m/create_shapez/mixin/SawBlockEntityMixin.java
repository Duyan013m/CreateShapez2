package github.duyan013m.create_shapez.mixin;

import com.simibubi.create.content.kinetics.saw.SawBlockEntity;
import com.simibubi.create.content.processing.recipe.ProcessingInventory;
import github.duyan013m.create_shapez.util.ShapezRecipeHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SawBlockEntity.class)
public class SawBlockEntityMixin {
    @Shadow(remap = false)
    public ProcessingInventory inventory;

    @Inject(method = "applyRecipe", at = @At("HEAD"), remap = false, cancellable = true)
    private void applyRecipe(CallbackInfo ci) {
        if(ShapezRecipeHelper.sCheck(inventory)) {
            ShapezRecipeHelper.sCraft(inventory);
            ci.cancel();
        }
//        if (inventory.getStackInSlot(0).isOf(Items.CRAFTING_TABLE)) {
//            inventory.setStackInSlot(0, new ItemStack(Items.FURNACE));
//			ItemStack itemStack = inventory.getStackInSlot(0);
//			String s = NBTModifier.getName(itemStack);
//			NBTModifier.setName(itemStack,s);

//			ci.cancel();
//        }
    }
}
