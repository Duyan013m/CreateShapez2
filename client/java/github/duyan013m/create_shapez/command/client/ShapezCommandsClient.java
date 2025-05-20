package github.duyan013m.create_shapez.command.client;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import github.duyan013m.create_shapez.command.server.ShapezCommandsServer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class ShapezCommandsClient {
    public static void register() {
        LiteralArgumentBuilder<FabricClientCommandSource> builder = ClientCommandManager.literal(ShapezCommandsServer.SHAPEZ_CLIENT);
        ShapezConfigCommandClient.register(builder);
        ShapezReloadCommandClient.register(builder);
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(builder));
    }
}
