package github.duyan013m.create_shapez;

import github.duyan013m.create_shapez.command.client.ShapezCommandsClient;
import github.duyan013m.create_shapez.config.client.ShapezConfigsClient;
import github.duyan013m.create_shapez.util.client.ShapezBakerImpl;
import net.fabricmc.api.ClientModInitializer;

public class CreateShapezClient implements ClientModInitializer {
    public static ShapezBakerImpl bakerImpl;

    @Override
    public void onInitializeClient() {
        ShapezConfigsClient.register();
        ShapezCommandsClient.register();
        CreateShapez.LOGGER.error("display3d4d_cache_reload transparent_texture client_T_command_lang stat belt_pause");
    }
}
