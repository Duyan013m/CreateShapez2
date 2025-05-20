package github.duyan013m.create_shapez.mixin;

import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.basin.BasinOperatingBlockEntity;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.item.SmartInventory;
import github.duyan013m.create_shapez.ShapezIdentifier;
import github.duyan013m.create_shapez.util.ShapezRecipeHelper;
import net.minecraft.recipe.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(BasinOperatingBlockEntity.class)
public abstract class BasinOperatingBlockEntityMixin {
    @Unique
    private static final Recipe<?> temp = new ProcessingRecipeBuilder<>(CompactingRecipe::new, ShapezIdentifier.Shapez2).build();

    //    private static final Recipe<?> temp=new ProcessingRecipeBuilder<>(MixingRecipe::new, ShapezIdentifier.Shapez2).build();
    @Shadow(remap = false)
    protected abstract Optional<BasinBlockEntity> getBasin();

    @Shadow(remap = false)
    protected abstract List<Recipe<?>> getMatchingRecipes();

    @Redirect(method = "updateBasin", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/processing/basin/BasinOperatingBlockEntity;getMatchingRecipes()Ljava/util/List;"))
    protected List<Recipe<?>> getMatchingRecipes(BasinOperatingBlockEntity blockEntity) {
        Optional<BasinBlockEntity> optionalBasin = getBasin();
        if (optionalBasin.isPresent()) {
            BasinBlockEntity basin = optionalBasin.get();
            SmartInventory inputInventory = basin.getInputInventory();
            SmartInventory outputInventory = basin.getOutputInventory();
            switch (this.getClass().getName()) {
                case "com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity":
                    if (ShapezRecipeHelper.bcCheck(inputInventory, outputInventory)) {
                        return List.of(temp);
                    }
                    break;
                case "com.simibubi.create.content.kinetics.press.MechanicalMixerBlockEntity":
                    if (ShapezRecipeHelper.bmCheck(inputInventory, outputInventory)) {
                        return List.of(temp);
                    }
                    break;
            }
        }
        return getMatchingRecipes();
    }

    @Inject(method = "applyBasinRecipe", at = @At("HEAD"), remap = false, cancellable = true)
    protected void applyBasinRecipe(CallbackInfo ci) {
        Optional<BasinBlockEntity> optionalBasin = getBasin();
        if (optionalBasin.isEmpty()) {
            ci.cancel();
            return;
        }
        BasinBlockEntity basin = optionalBasin.get();
        SmartInventory inputInventory = basin.getInputInventory();
        SmartInventory outputInventory = basin.getOutputInventory();
        switch (this.getClass().getName()) {
            case "com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity":
                if (ShapezRecipeHelper.bcCheck(inputInventory, outputInventory)) {
                    ShapezRecipeHelper.bcCraft(inputInventory, outputInventory);
                    ci.cancel();
                }
                break;
            case "com.simibubi.create.content.kinetics.press.MechanicalMixerBlockEntity":
                if (ShapezRecipeHelper.bmCheck(inputInventory, outputInventory)) {
                    ShapezRecipeHelper.bmCraft(inputInventory, outputInventory);
                    ci.cancel();
                }
                break;
        }
//		basin.notifyChangeOfContents();
//        ci.cancel();
    }
}
