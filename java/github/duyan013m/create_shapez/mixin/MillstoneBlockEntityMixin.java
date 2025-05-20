package github.duyan013m.create_shapez.mixin;


import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;
import github.duyan013m.create_shapez.util.ShapezRecipeHelper;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MillstoneBlockEntity.class)
public abstract class MillstoneBlockEntityMixin {
    @Shadow(remap = false)
    public ItemStackHandlerContainer inputInv;

    @Shadow(remap = false)
    public ItemStackHandler outputInv;

    @Inject(method = "process", at = @At("HEAD"), remap = false, cancellable = true)
    private void process(CallbackInfo ci) {
        if(ShapezRecipeHelper.mCheck(inputInv,outputInv)){
            ShapezRecipeHelper.mCraft(inputInv,outputInv);
            ci.cancel();
        }
//        ItemStack itemStack = inputInv.getStackInSlot(0);
//        if (!itemStack.isOf(Items.CRAFTING_TABLE)) {
//            return;
//        }
//        if (!outputInv.empty()) {
//            ci.cancel();
//            return;
//        }
//        itemStack.decrement(1);
//        outputInv.setStackInSlot(0, new ItemStack(Items.FURNACE));

//		try (Transaction t = TransferUtil.getTransaction()) {
//			ItemStackHandlerSlot slot = inputInv.getSlot(0);
//			slot.extract(slot.getResource(), 1, t);
//			ItemStack stack=new ItemStack(Items.FURNACE);
//			outputInv.insert(ItemVariant.of(stack), stack.getCount(), t);
//			t.commit();
//		}


//		award(AllAdvancements.MILLSTONE);
//		sendData();
//		markDirty();
//		ci.cancel();
    }
}
