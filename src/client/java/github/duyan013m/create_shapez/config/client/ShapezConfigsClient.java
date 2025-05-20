package github.duyan013m.create_shapez.config.client;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import github.duyan013m.create_shapez.CreateShapez;
import github.duyan013m.create_shapez.config.ShapezConfigs;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class ShapezConfigsClient {
    private static final ForgeConfigSpec.BooleanValue Display3d;
    private static final ForgeConfigSpec.BooleanValue Perspective;

    public static Boolean getDisplay3d() {
        return Display3d.get();
    }

    public static void setDisplay3d(Boolean bool) {
        Display3d.set(bool);
    }


    public static Boolean getPerspective() {
        return Perspective.get();
    }

    public static void setPerspective(Boolean bool) {
        Perspective.set(bool);
    }
    public static void register() {
    }

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        Display3d = builder.define(ShapezConfigs.DISPLAY3D, true);
        Perspective = builder.define(ShapezConfigs.PERSPECTIVE, true);
        ForgeConfigRegistry.INSTANCE.register(CreateShapez.ID, ModConfig.Type.CLIENT, builder.build());
    }
}
