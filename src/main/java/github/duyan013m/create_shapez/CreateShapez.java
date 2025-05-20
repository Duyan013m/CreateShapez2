package github.duyan013m.create_shapez;

import github.duyan013m.create_shapez.command.ShapezCommands;
import github.duyan013m.create_shapez.config.ShapezConfigs;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class CreateShapez implements ModInitializer {
    public static final String ID = "create_shapez";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    @Override
    public void onInitialize() {
        ShapezItems.register();
        ShapezConfigs.register();
        ShapezCommands.register();
    }

    public static void test(int times, Runnable runnable) {
        long t = System.nanoTime();
        IntStream.range(0, times).forEach(i -> runnable.run());
        LOGGER.error(String.valueOf(System.nanoTime() - t));
    }

    public static Identifier id(String path) {
        return Identifier.of(ID, path);
    }
}
