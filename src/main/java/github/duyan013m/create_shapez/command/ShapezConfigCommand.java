package github.duyan013m.create_shapez.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import github.duyan013m.create_shapez.config.ShapezConfigs;
import net.minecraft.command.CommandException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.apache.commons.lang3.NotImplementedException;

public class ShapezConfigCommand {

    public static void register(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builder.then(CommandManager.literal(ShapezCommands.CONFIG)
//                .then(CommandManager.literal(ShapezConfigs.HEXAGONAL)
//                        .executes(ShapezConfigCommand::executeScenarioGet)
//                        .then(CommandManager.argument(ShapezConfigs.HEXAGONAL, BoolArgumentType.bool())
//                                .executes(ShapezConfigCommand::executeScenarioSet)))
                .then(CommandManager.literal(ShapezConfigs.MAX_LAYER)
                        .executes(ShapezConfigCommand::executeMaxLayerGet)
                        .then(CommandManager.argument(ShapezConfigs.MAX_LAYER, IntegerArgumentType.integer(1, 5))
                                .executes(ShapezConfigCommand::executeMaxLayerSet)))
                .then(CommandManager.literal(ShapezConfigs.ALLOW_EMPTY)
                        .executes(ShapezConfigCommand::executeAllowEmptyGet)
                        .then(CommandManager.argument(ShapezConfigs.ALLOW_EMPTY, BoolArgumentType.bool())
                                .executes(ShapezConfigCommand::executeAllowEmptySet))));
    }

//    private static int executeScenarioGet(CommandContext<ServerCommandSource> context) {
//        context.getSource().sendFeedback(() -> Text.literal(ShapezConfigs.getScenario()), false);
//        return 1;
//    }

//    private static int executeScenarioSet(CommandContext<ServerCommandSource> context) {
//        ShapezConfigs.setScenario(BoolArgumentType.getBool(context, ShapezConfigs.HEXAGONAL));
//        context.getSource().sendFeedback(() -> Text.literal(ShapezConfigs.getScenario()), true);
//        return 1;
//    }

    private static int executeMaxLayerGet(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(() -> Text.literal(ShapezConfigs.getMaxLayer().toString()), false);
        return ShapezConfigs.getMaxLayer();
    }

    private static int executeMaxLayerSet(CommandContext<ServerCommandSource> context) {
        ShapezConfigs.setMaxLayer(IntegerArgumentType.getInteger(context, ShapezConfigs.MAX_LAYER));
        context.getSource().sendFeedback(() -> Text.literal(ShapezConfigs.getMaxLayer().toString()), true);
        return 1;
    }

    private static int executeAllowEmptyGet(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(() -> Text.literal(ShapezConfigs.getAllowEmpty().toString()), false);
        return 1;
    }

    private static int executeAllowEmptySet(CommandContext<ServerCommandSource> context) {
        ShapezConfigs.setAllowEmpty(BoolArgumentType.getBool(context, ShapezConfigs.ALLOW_EMPTY));
        context.getSource().sendFeedback(() -> Text.literal(ShapezConfigs.getAllowEmpty().toString()), true);
        return 1;
    }
}
