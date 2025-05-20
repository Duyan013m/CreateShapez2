package github.duyan013m.create_shapez.mixin;

import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.content.kinetics.press.PressingBehaviour;
import com.simibubi.create.content.processing.basin.BasinOperatingBlockEntity;
import com.simibubi.create.foundation.utility.VecHelper;
import github.duyan013m.create_shapez.util.ShapezRecipeHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(MechanicalPressBlockEntity.class)
public abstract class MechanicalPressBlockEntityMixin extends BasinOperatingBlockEntity implements PressingBehaviour.PressingBehaviourSpecifics {
    @Shadow(remap = false)
    public PressingBehaviour pressingBehaviour;

    public MechanicalPressBlockEntityMixin(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Inject(method = "tryProcessInWorld", at = @At("HEAD"), remap = false, cancellable = true)
    public void tryProcessInWorld(ItemEntity itemEntity, boolean simulate, CallbackInfoReturnable<Boolean> cir) {
        if (ShapezRecipeHelper.pwCheck(itemEntity)) {
            if(!simulate){
                pressingBehaviour.particleItems.add(itemEntity.getStack());
                ShapezRecipeHelper.pwCraft(itemEntity);
            }
            cir.setReturnValue(true);
            cir.cancel();
        }
//        ItemStack item = itemEntity.getStack();
//        if (simulate || !item.isOf(Items.CRAFTING_TABLE)) return;
////        pressingBehaviour.particleItems.add(item);
//        if (item.getCount() == 1) {
//            itemEntity.setStack(new ItemStack(Items.FURNACE));
//        } else {
//            ItemEntity created = new ItemEntity(world, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), new ItemStack(Items.FURNACE));
//            created.setToDefaultPickupDelay();
//            created.setVelocity(VecHelper.offsetRandomly(Vec3d.ZERO, world.random, .05f));
//            world.spawnEntity(created);
//            item.decrement(1);
//        }
//        cir.setReturnValue(true);
//        cir.cancel();
    }

    @Inject(method = "tryProcessOnBelt", at = @At("HEAD"), remap = false, cancellable = true)
    public void tryProcessOnBelt(TransportedItemStack input, List<ItemStack> outputList, boolean simulate, CallbackInfoReturnable<Boolean> cir) {
        if(ShapezRecipeHelper.pbCheck(input)){
            if(!simulate) {
                pressingBehaviour.particleItems.add(input.stack);
                ShapezRecipeHelper.pbCraft(input, outputList);
            }
            cir.setReturnValue(true);
            cir.cancel();
        }
//        if (simulate || !input.stack.isOf(Items.CRAFTING_TABLE)) return;
////        pressingBehaviour.particleItems.add(input.stack);
//        outputList.add(new ItemStack(Items.FURNACE));
//        cir.setReturnValue(true);
//        cir.cancel();
    }
}