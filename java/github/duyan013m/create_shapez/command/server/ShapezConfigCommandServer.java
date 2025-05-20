package github.duyan013m.create_shapez.command.server;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import github.duyan013m.create_shapez.command.ShapezCommands;
import github.duyan013m.create_shapez.command.ShapezConfigCommand;
import github.duyan013m.create_shapez.config.ShapezConfigs;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.commons.lang3.NotImplementedException;

public class ShapezConfigCommandServer {
    public static void register(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builder.then(CommandManager.literal(ShapezCommands.CONFIG)
                .then(CommandManager.literal(ShapezConfigs.DISPLAY3D)
                        .executes(ShapezConfigCommandServer::executeDisplay3dGet)
                        .then(CommandManager.argument(ShapezConfigs.DISPLAY3D, BoolArgumentType.bool())
                                .executes(ShapezConfigCommandServer::executeDisplay3dSet)))
                .then(CommandManager.literal(ShapezConfigs.PERSPECTIVE)
                        .executes(ShapezConfigCommandServer::executePerspectiveGet)
                        .then(CommandManager.argument(ShapezConfigs.PERSPECTIVE, BoolArgumentType.bool())
                                .executes(ShapezConfigCommandServer::executePerspectiveSet))));
    }
    private static int executeDisplay3dGet(CommandContext<ServerCommandSource> context) {
        throw new NotImplementedException();
    }
    private static int executeDisplay3dSet(CommandContext<ServerCommandSource> context) {
        throw new NotImplementedException();
    }
    private static int executePerspectiveGet(CommandContext<ServerCommandSource> context) {
        throw new NotImplementedException();
    }
    private static int executePerspectiveSet(CommandContext<ServerCommandSource> context) {
        throw new NotImplementedException();
    }
}
