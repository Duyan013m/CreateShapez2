package github.duyan013m.create_shapez.command.client;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import github.duyan013m.create_shapez.command.ShapezCommands;
import github.duyan013m.create_shapez.config.ShapezConfigs;
import github.duyan013m.create_shapez.config.client.ShapezConfigsClient;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public class ShapezConfigCommandClient {
    public static void register(LiteralArgumentBuilder<FabricClientCommandSource> builder) {
        builder.then(ClientCommandManager.literal(ShapezCommands.CONFIG)
                .then(ClientCommandManager.literal(ShapezConfigs.DISPLAY3D)
                        .executes(ShapezConfigCommandClient::executeDisplay3dGet)
                        .then(ClientCommandManager.argument(ShapezConfigs.DISPLAY3D, BoolArgumentType.bool())
                                .executes(ShapezConfigCommandClient::executeDisplay3dSet)))
                .then(ClientCommandManager.literal(ShapezConfigs.PERSPECTIVE)
                        .executes(ShapezConfigCommandClient::executePerspectiveGet)
                        .then(ClientCommandManager.argument(ShapezConfigs.PERSPECTIVE, BoolArgumentType.bool())
                                .executes(ShapezConfigCommandClient::executePerspectiveSet))));
    }
    private static int executeDisplay3dGet(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Text.literal(ShapezConfigsClient.getDisplay3d().toString()));
        return 1;
    }

    private static int executeDisplay3dSet(CommandContext<FabricClientCommandSource> context) {
        ShapezConfigsClient.setDisplay3d(BoolArgumentType.getBool(context, ShapezConfigs.DISPLAY3D));
        context.getSource().sendFeedback(Text.literal(ShapezConfigsClient.getDisplay3d().toString()));
        return 1;
    }

    private static int executePerspectiveGet(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Text.literal(ShapezConfigsClient.getPerspective().toString()));
        return 1;
    }

    private static int executePerspectiveSet(CommandContext<FabricClientCommandSource> context) {
        ShapezConfigsClient.setPerspective(BoolArgumentType.getBool(context, ShapezConfigs.PERSPECTIVE));
        context.getSource().sendFeedback(Text.literal(ShapezConfigsClient.getPerspective().toString()));
        return 1;
    }
}
