package github.duyan013m.create_shapez;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ShapezItems {
    public static final Item Shapez2 = Registry.register(Registries.ITEM, ShapezIdentifier.Shapez2, new Item(new FabricItemSettings().maxCount(1)));

    public static void register() {

    }
}
