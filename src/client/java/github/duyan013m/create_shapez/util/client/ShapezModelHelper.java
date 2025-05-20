package github.duyan013m.create_shapez.util.client;

import github.duyan013m.create_shapez.CreateShapezClient;
import github.duyan013m.create_shapez.ShapezItems;
import github.duyan013m.create_shapez.config.client.ShapezConfigsClient;
import github.duyan013m.create_shapez.util.ShapezCodeHelper;
import net.minecraft.client.render.model.BakedModel;

import java.util.HashMap;
import java.util.Map;

public class ShapezModelHelper {
    private static final Map<String, BakedModel> models2 = new HashMap<>();
    private static final Map<String, BakedModel> models3 = new HashMap<>();

    public static int clearAllModel(){
        int i = models2.size()+models3.size();
        models2.clear();
        models3.clear();
        return i;
    }
    public static int clearAllMissingModel(){
        return 0;
    }
    public static int clearAllDisplayModel(){
        return 0;
    }
    public static int clearAllMissingDisplayModel(){
        return 0;
    }

    public static BakedModel get(String name) {
        Boolean display3d = ShapezConfigsClient.getDisplay3d();
        Map<String, BakedModel> models = display3d ? models3 : models2;
        if (!models.containsKey(name)) {
            if (!ShapezCodeHelper.valid(name)) {
                if (!models.containsKey(ShapezItems.Shapez2.getTranslationKey())) {
                    models.put(ShapezItems.Shapez2.getTranslationKey(), CreateShapezClient.bakerImpl.bake(ShapezJsonUnbakedModelHelper.DEFAULT2D));
                }
                models.put(name, models.get(ShapezItems.Shapez2.getTranslationKey()));
            } else {
                models.put(name, CreateShapezClient.bakerImpl.bake(ShapezJsonUnbakedModelHelper.create(name,display3d)));
            }
        }
        return models.get(name);
    }
}
