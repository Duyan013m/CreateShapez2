package github.duyan013m.create_shapez.util;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.JsonHelper;


public class ShapezItemHelper {
    public static ItemStack setName(ItemStack itemStack, String name) {
        if(name.isEmpty()){
            return ItemStack.EMPTY;
        }
        if (!itemStack.hasNbt()) {
            itemStack.setNbt(new NbtCompound());
        }
        NbtCompound tag = itemStack.getNbt();
        if (tag == null) {
            tag = new NbtCompound();
            itemStack.setNbt(tag);
        }
        NbtCompound display = new NbtCompound();
        display.putString("Name", String.format("{\"text\":\"%s\",\"italic\":false}", name));
        tag.put("display", display);
        return itemStack;
    }

    public static String getName(ItemStack itemStack) {
        if (!itemStack.hasNbt()) {
            return "";
        }
        NbtCompound tag = itemStack.getNbt();
        if (tag == null) {
            return "";
        }
        NbtCompound display = tag.getCompound("display");
        if (!display.contains("Name")) {
            return "";
        }
        JsonObject name = JsonHelper.deserialize(display.getString("Name"));
        if (!name.has("italic")) {
            return "";
        }
        return name.get("text").getAsString();
    }
}
