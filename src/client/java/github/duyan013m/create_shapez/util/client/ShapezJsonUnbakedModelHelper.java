package github.duyan013m.create_shapez.util.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import github.duyan013m.create_shapez.CreateShapez;
import github.duyan013m.create_shapez.config.client.ShapezConfigsClient;
import github.duyan013m.create_shapez.util.ShapezCodeHelper;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.JsonHelper;

import java.util.stream.IntStream;

public class ShapezJsonUnbakedModelHelper {
    public static final JsonObject DEFAULT = JsonHelper.deserialize(String.format("{\"parent\":\"%s:item/display\",\"elements\":[],\"textures\":{}}", CreateShapez.ID));
    public static final JsonObject ELEMENTS2D;
    public static final JsonObject ELEMENTS3D;
    ;
    public static final JsonUnbakedModel DEFAULT2D;
    public static final JsonUnbakedModel DEFAULT3D;

    public static final String BACK = CreateShapez.ID+":item/back";

    static {
        ELEMENTS2D = JsonHelper.deserialize("{\"-1\":{\"faces\":{\"north\":{\"texture\":\"#-1\",\"uv\":[16,0,0,16]}},\"from\":[-16,-16,8],\"to\":[32,32,8]},\"0\":{\"faces\":{\"south\":{\"texture\":\"#0\",\"uv\":[0,0,16,16]}},\"from\":[-16,-16,8],\"to\":[32,32,8]},\"11\":{\"faces\":{\"south\":{\"texture\":\"#11\",\"uv\":[8,0,16,8]}},\"from\":[8,8,8.01],\"to\":[32,32,8.01]},\"12\":{\"faces\":{\"south\":{\"texture\":\"#12\",\"uv\":[8,8,16,16]}},\"from\":[8,-16,8.01],\"to\":[32,8,8.01]},\"13\":{\"faces\":{\"south\":{\"texture\":\"#13\",\"uv\":[0,8,8,16]}},\"from\":[-16,-16,8.01],\"to\":[8,8,8.01]},\"14\":{\"faces\":{\"south\":{\"texture\":\"#14\",\"uv\":[0,0,8,8]}},\"from\":[-16,8,8.01],\"to\":[8,32,8.01]},\"21\":{\"faces\":{\"south\":{\"texture\":\"#21\",\"uv\":[8,0,16,8]}},\"from\":[8,8,8.03],\"to\":[28,28,8.03]},\"22\":{\"faces\":{\"south\":{\"texture\":\"#22\",\"uv\":[8,8,16,16]}},\"from\":[8,-12,8.03],\"to\":[28,8,8.03]},\"23\":{\"faces\":{\"south\":{\"texture\":\"#23\",\"uv\":[0,8,8,16]}},\"from\":[-12,-12,8.03],\"to\":[8,8,8.03]},\"24\":{\"faces\":{\"south\":{\"texture\":\"#24\",\"uv\":[0,0,8,8]}},\"from\":[-12,8,8.03],\"to\":[8,28,8.03]},\"31\":{\"faces\":{\"south\":{\"texture\":\"#31\",\"uv\":[8,0,16,8]}},\"from\":[8,8,8.05],\"to\":[24,24,8.05]},\"32\":{\"faces\":{\"south\":{\"texture\":\"#32\",\"uv\":[8,8,16,16]}},\"from\":[8,-8,8.05],\"to\":[24,8,8.05]},\"33\":{\"faces\":{\"south\":{\"texture\":\"#33\",\"uv\":[0,8,8,16]}},\"from\":[-8,-8,8.05],\"to\":[8,8,8.05]},\"34\":{\"faces\":{\"south\":{\"texture\":\"#34\",\"uv\":[0,0,8,8]}},\"from\":[-8,8,8.05],\"to\":[8,24,8.05]},\"41\":{\"faces\":{\"south\":{\"texture\":\"#41\",\"uv\":[8,0,16,8]}},\"from\":[8,8,8.07],\"to\":[21,21,8.07]},\"42\":{\"faces\":{\"south\":{\"texture\":\"#42\",\"uv\":[8,8,16,16]}},\"from\":[8,-5,8.07],\"to\":[21,8,8.07]},\"43\":{\"faces\":{\"south\":{\"texture\":\"#43\",\"uv\":[0,8,8,16]}},\"from\":[-5,-5,8.07],\"to\":[8,8,8.07]},\"44\":{\"faces\":{\"south\":{\"texture\":\"#44\",\"uv\":[0,0,8,8]}},\"from\":[-5,8,8.07],\"to\":[8,21,8.07]},\"51\":{\"faces\":{\"south\":{\"texture\":\"#51\",\"uv\":[8,0,16,8]}},\"from\":[8,8,8.09],\"to\":[18,18,8.09]},\"52\":{\"faces\":{\"south\":{\"texture\":\"#52\",\"uv\":[8,8,16,16]}},\"from\":[8,-2,8.09],\"to\":[18,8,8.09]},\"53\":{\"faces\":{\"south\":{\"texture\":\"#53\",\"uv\":[0,8,8,16]}},\"from\":[-2,-2,8.09],\"to\":[8,8,8.09]},\"54\":{\"faces\":{\"south\":{\"texture\":\"#54\",\"uv\":[0,0,8,8]}},\"from\":[-2,8,8.09],\"to\":[8,18,8.09]}}");
        ELEMENTS3D = JsonHelper.deserialize("{\"-1\":{\"faces\":{\"north\":{\"texture\":\"#-1\",\"uv\":[16,0,0,16]}},\"from\":[-16,-16,8],\"to\":[32,32,8]},\"0\":{\"faces\":{\"south\":{\"texture\":\"#0\",\"uv\":[0,0,16,16]}},\"from\":[-16,-16,8],\"to\":[32,32,8]},\"11\":{\"faces\":{\"south\":{\"texture\":\"#11\",\"uv\":[8,0,16,8]}},\"from\":[8,8,8.01],\"to\":[32,32,9.01]},\"12\":{\"faces\":{\"south\":{\"texture\":\"#12\",\"uv\":[8,8,16,16]}},\"from\":[8,-16,8.01],\"to\":[32,8,9.01]},\"13\":{\"faces\":{\"south\":{\"texture\":\"#13\",\"uv\":[0,8,8,16]}},\"from\":[-16,-16,8.01],\"to\":[8,8,9.01]},\"14\":{\"faces\":{\"south\":{\"texture\":\"#14\",\"uv\":[0,0,8,8]}},\"from\":[-16,8,8.01],\"to\":[8,32,9.01]},\"21\":{\"faces\":{\"south\":{\"texture\":\"#21\",\"uv\":[8,0,16,8]}},\"from\":[8,8,9.03],\"to\":[28,28,10.03]},\"22\":{\"faces\":{\"south\":{\"texture\":\"#22\",\"uv\":[8,8,16,16]}},\"from\":[8,-12,9.03],\"to\":[28,8,10.03]},\"23\":{\"faces\":{\"south\":{\"texture\":\"#23\",\"uv\":[0,8,8,16]}},\"from\":[-12,-12,9.03],\"to\":[8,8,10.03]},\"24\":{\"faces\":{\"south\":{\"texture\":\"#24\",\"uv\":[0,0,8,8]}},\"from\":[-12,8,9.03],\"to\":[8,28,10.03]},\"31\":{\"faces\":{\"south\":{\"texture\":\"#31\",\"uv\":[8,0,16,8]}},\"from\":[8,8,10.05],\"to\":[24,24,11.05]},\"32\":{\"faces\":{\"south\":{\"texture\":\"#32\",\"uv\":[8,8,16,16]}},\"from\":[8,-8,10.05],\"to\":[24,8,11.05]},\"33\":{\"faces\":{\"south\":{\"texture\":\"#33\",\"uv\":[0,8,8,16]}},\"from\":[-8,-8,10.05],\"to\":[8,8,11.05]},\"34\":{\"faces\":{\"south\":{\"texture\":\"#34\",\"uv\":[0,0,8,8]}},\"from\":[-8,8,10.05],\"to\":[8,24,11.05]},\"41\":{\"faces\":{\"south\":{\"texture\":\"#41\",\"uv\":[8,0,16,8]}},\"from\":[8,8,11.07],\"to\":[21,21,12.07]},\"42\":{\"faces\":{\"south\":{\"texture\":\"#42\",\"uv\":[8,8,16,16]}},\"from\":[8,-5,11.07],\"to\":[21,8,12.07]},\"43\":{\"faces\":{\"south\":{\"texture\":\"#43\",\"uv\":[0,8,8,16]}},\"from\":[-5,-5,11.07],\"to\":[8,8,12.07]},\"44\":{\"faces\":{\"south\":{\"texture\":\"#44\",\"uv\":[0,0,8,8]}},\"from\":[-5,8,11.07],\"to\":[8,21,12.07]},\"51\":{\"faces\":{\"south\":{\"texture\":\"#51\",\"uv\":[8,0,16,8]}},\"from\":[8,8,12.09],\"to\":[18,18,13.09]},\"52\":{\"faces\":{\"south\":{\"texture\":\"#52\",\"uv\":[8,8,16,16]}},\"from\":[8,-2,12.09],\"to\":[18,8,13.09]},\"53\":{\"faces\":{\"south\":{\"texture\":\"#53\",\"uv\":[0,8,8,16]}},\"from\":[-2,-2,12.09],\"to\":[8,8,13.09]},\"54\":{\"faces\":{\"south\":{\"texture\":\"#54\",\"uv\":[0,0,8,8]}},\"from\":[-2,8,12.09],\"to\":[8,18,13.09]}}");
        JsonObject jsonObject = DEFAULT.deepCopy();
        JsonObject textures = jsonObject.getAsJsonObject("textures");
        JsonArray elements = jsonObject.getAsJsonArray("elements");
        IntStream.range(-1, 1).forEach(i -> {
            String s = String.valueOf(i);
            textures.addProperty(s, BACK);
            elements.add(ELEMENTS2D.get(s));
        });
        DEFAULT2D = JsonHelper.deserialize(JsonUnbakedModel.GSON, JsonHelper.toSortedString(jsonObject), JsonUnbakedModel.class);
        DEFAULT3D = JsonHelper.deserialize(JsonUnbakedModel.GSON, JsonHelper.toSortedString(jsonObject), JsonUnbakedModel.class);
    }

    public static JsonUnbakedModel create(String name,boolean display3d) {
        return display3d ? create3d(name) : create2d(name);
    }

    public static JsonUnbakedModel create2d(String name) {
        JsonObject jsonObject = DEFAULT.deepCopy();
        JsonObject textures = jsonObject.getAsJsonObject("textures");
        JsonArray elements = jsonObject.getAsJsonArray("elements");
//        textures.addProperty("0", BACK);
//        elements.add(ELEMENTS2D.get("0"));
        textures.addProperty("-1", BACK);
        elements.add(ELEMENTS2D.get("-1"));
        String code = ShapezCodeHelper.replace8s(name);
        IntStream.range(0, code.length() / 8).forEach(l -> IntStream.range(0, 4).forEach(p -> {
            String lp = String.valueOf(l + 1) + (p + 1);
            textures.addProperty(lp, ShapezCodeHelper.sc2name(code.substring(8 * l + 2 * p, 8 * l + 2 * p + 2)));
            elements.add(ELEMENTS2D.get(lp));
        }));
        return JsonHelper.deserialize(JsonUnbakedModel.GSON, JsonHelper.toSortedString(jsonObject), JsonUnbakedModel.class);
    }

    public static JsonUnbakedModel create3d(String name) {
        JsonObject jsonObject = DEFAULT.deepCopy();
        JsonObject textures = jsonObject.getAsJsonObject("textures");
        JsonArray elements = jsonObject.getAsJsonArray("elements");
//        textures.addProperty("0", BACK);
//        elements.add(ELEMENTS3D.get("0"));
        textures.addProperty("-1", BACK);
        elements.add(ELEMENTS3D.get("-1"));
        String code = ShapezCodeHelper.replace8s(name);
        IntStream.range(0, code.length() / 8).forEach(l -> IntStream.range(0, 4).forEach(p -> {
            String lp = String.valueOf(l + 1) + (p + 1);
            textures.addProperty(lp, ShapezCodeHelper.sc2name(code.substring(8 * l + 2 * p, 8 * l + 2 * p + 2)));
            elements.add(ELEMENTS3D.get(lp));
        }));
        return JsonHelper.deserialize(JsonUnbakedModel.GSON, JsonHelper.toSortedString(jsonObject), JsonUnbakedModel.class);
    }
}
