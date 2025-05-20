package github.duyan013m.create_shapez.config;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import github.duyan013m.create_shapez.CreateShapez;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class ShapezConfigs {
//    public static final String REGULAR = "Regular";
//    public static final String HEXAGONAL = "Hexagonal";
//    public static final String SCENARIO = "Scenario";
    public static final String MAX_LAYER = "maxLayer";
    public static final String ALLOW_EMPTY="allowEmpty";
    public static final String DISPLAY3D = "Display3d";
    public static final String PERSPECTIVE="perspective";
    //    private static final ForgeConfigSpec.ConfigValue<String> Scenario;
    private static final ForgeConfigSpec.IntValue MaxLayer;
    private static final ForgeConfigSpec.BooleanValue AllowEmpty;

//    public static final ImmutableBiMap<String, Boolean> SB = ImmutableBiMap.of(REGULAR, false, HEXAGONAL, true);
//    public static final ImmutableBiMap<Boolean, String> BS = SB.inverse();
//    public static final ImmutableBiMap<Integer, String> IS = ImmutableBiMap.of(4, REGULAR, 6, HEXAGONAL);
//    public static final ImmutableBiMap<String, Integer> SI = IS.inverse();

    public static Integer getMaxLayer() {
        return MaxLayer.get();
    }

    public static void setMaxLayer(Integer integer) {
        MaxLayer.set(integer);
    }
    public static Boolean getAllowEmpty() {
        return AllowEmpty.get();
    }

    public static void setAllowEmpty(Boolean bool) {
        AllowEmpty.set(bool);
    }

//    public static String getScenario() {
//        return Scenario.get();
//    }

//    public static void setScenario(Boolean hexagonal) {
//        Scenario.set(BS.get(hexagonal));
//    }

//    public static Integer getQuadrants() {
//        return SI.get(Scenario.get());
//    }

    public static void register() {
    }

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
//        Scenario = builder.defineInList(SCENARIO, REGULAR, List.of(REGULAR, HEXAGONAL));
        MaxLayer = builder.defineInRange(MAX_LAYER, 4, 1, 5);
        AllowEmpty = builder.define(ALLOW_EMPTY, false);
        ForgeConfigRegistry.INSTANCE.register(CreateShapez.ID, ModConfig.Type.SERVER, builder.build());
    }
}
