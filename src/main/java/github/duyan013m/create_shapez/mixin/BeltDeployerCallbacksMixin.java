package github.duyan013m.create_shapez.mixin;

import com.simibubi.create.content.kinetics.belt.behaviour.TransportedItemStackHandlerBehaviour;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.kinetics.deployer.BeltDeployerCallbacks;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import github.duyan013m.create_shapez.ShapezIdentifier;
import github.duyan013m.create_shapez.util.ShapezRecipeHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeltDeployerCallbacks.class)
public class BeltDeployerCallbacksMixin {
    @Unique
    private static final Recipe<?> temp = new ProcessingRecipeBuilder<>(DeployerApplicationRecipe::new, ShapezIdentifier.Shapez2).build();


    @Redirect(method = "onItemReceived", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/deployer/DeployerBlockEntity;getRecipe(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/recipe/Recipe;"))
    private static Recipe<? extends Inventory> getRecipe1(DeployerBlockEntity blockEntity, ItemStack stack) {
        if (ShapezRecipeHelper.dCheck(stack, blockEntity.getPlayer().getMainHandStack())) {
            return temp;
        } else {
            return blockEntity.getRecipe(stack);
        }
    }

    @Redirect(method = "whenItemHeld", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/deployer/DeployerBlockEntity;getRecipe(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/recipe/Recipe;"))
    private static Recipe<? extends Inventory> getRecipe2(DeployerBlockEntity blockEntity, ItemStack stack) {
        if (ShapezRecipeHelper.dCheck(stack, blockEntity.getPlayer().getMainHandStack())) {
            return temp;
        } else {
            return blockEntity.getRecipe(stack);
        }
    }

    @Inject(method = "activate", at = @At("HEAD"), cancellable = true)
    private static void activate(TransportedItemStack transported, TransportedItemStackHandlerBehaviour handler, DeployerBlockEntity blockEntity, Recipe<?> recipe, CallbackInfo ci) {
        ItemStack heldItem = blockEntity.getPlayer().getMainHandStack();
        if (ShapezRecipeHelper.dCheck(transported.stack, heldItem)) {
            ShapezRecipeHelper.dCraft(transported, heldItem, handler);
        }
//        ItemStack heldItem = blockEntity.getPlayer().getMainHandStack();
//        if (transported.stack.isOf(Items.CRAFTING_TABLE) && heldItem.isOf(Items.CRAFTING_TABLE)) {
//            List<TransportedItemStack> collect = new ArrayList<>();
//            TransportedItemStack copy = transported.copy();
//            copy.stack = new ItemStack(Items.FURNACE);
//            collect.add(copy);
//            TransportedItemStack left = transported.copy();
////			blockEntity.getPlayer().spawnedItemEffects = transported.stack.copy();
//            left.stack.decrement(1);
//            handler.handleProcessingOnItem(transported, TransportedItemStackHandlerBehaviour.TransportedResult.convertToAndLeaveHeld(collect, left));
//            heldItem.decrement(1);
////			ci.cancel();
//        }
    }
}
