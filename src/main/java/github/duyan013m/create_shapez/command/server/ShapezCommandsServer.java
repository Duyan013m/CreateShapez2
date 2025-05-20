package github.duyan013m.create_shapez.command.server;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ShapezCommandsServer {
    public static final String SHAPEZ_CLIENT = "shapez2u-client";

    public static void register() {
        LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal(SHAPEZ_CLIENT);
        ShapezConfigCommandServer.register(builder);
        ShapezReloadCommandServer.register(builder);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(builder));
    }
}
