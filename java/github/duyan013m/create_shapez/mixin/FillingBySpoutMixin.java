package github.duyan013m.create_shapez.mixin;

import com.simibubi.create.content.fluids.spout.FillingBySpout;
import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FillingBySpout.class)
public class FillingBySpoutMixin {
    @Inject(method = "fillItem", at = @At("HEAD"), remap = false, cancellable = true)
    private static void fillItem(World world, long requiredAmount, ItemStack stack, FluidStack availableFluid, CallbackInfoReturnable<ItemStack> cir) {
//        cir.setReturnValue(cir.getReturnValue());
    }
}
