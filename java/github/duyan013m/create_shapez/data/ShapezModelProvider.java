package github.duyan013m.create_shapez.data;

import github.duyan013m.create_shapez.CreateShapez;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.util.JsonHelper;

public class ShapezModelProvider extends FabricModelProvider {
    public static final String DISPLAY="{\"display\":{\"firstperson_lefthand\":{\"rotation\":[0,0,0],\"scale\":[0.2268,0.2268,0.2268],\"translation\":[-2.87,8.2,1.13]},\"firstperson_righthand\":{\"rotation\":[0,0,0],\"scale\":[0.2268,0.2268,0.2268],\"translation\":[-2.87,8.2,1.13]},\"fixed\":{\"rotation\":[0,180,0],\"scale\":[0.24,0.24,0.24],\"translation\":[0,0,0]},\"ground\":{\"rotation\":[0,0,0],\"scale\":[0.12,0.12,0.12],\"translation\":[0,3,0]},\"gui\":{\"rotation\":[0,0,0],\"scale\":[0.3,0.3,0.3],\"translation\":[0,0,0]},\"thirdperson_lefthand\":{\"rotation\":[0,0,0],\"scale\":[0.285,0.285,0.285],\"translation\":[0,6.4,0.5]},\"thirdperson_righthand\":{\"rotation\":[0,0,0],\"scale\":[0.285,0.285,0.285],\"translation\":[0,6.4,0.5]}},\"gui_light\":\"front\"}";
    public ShapezModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.writer.accept(CreateShapez.id("item/display"),()-> JsonHelper.deserialize(DISPLAY));
    }
}
