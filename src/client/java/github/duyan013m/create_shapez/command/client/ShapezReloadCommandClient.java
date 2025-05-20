package github.duyan013m.create_shapez.command.client;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import github.duyan013m.create_shapez.command.ShapezCommands;
import github.duyan013m.create_shapez.util.client.ShapezModelHelper;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import java.util.function.IntFunction;
import java.util.function.IntSupplier;

public class ShapezReloadCommandClient {
    public static void register(LiteralArgumentBuilder<FabricClientCommandSource> builder) {
        builder.then(ClientCommandManager.literal(ShapezCommands.RELOAD)
                .executes(context -> executeReload(context, -1))
                .then(ClientCommandManager.literal("model")
                        .executes(context -> executeReloadModels(context, ShapezModelHelper::clearAllModel, i -> Text.literal(String.valueOf(i))))
                        .then(ClientCommandManager.literal("all")
                                .executes(context -> executeReloadModels(context, ShapezModelHelper::clearAllModel, i -> Text.literal(String.valueOf(i)))))));
    }

    private static int executeReload(CommandContext<FabricClientCommandSource> context, int i) {
        return switch (i) {
            default -> executeReloadAll(context);
        };
    }

    private static int executeReloadAll(CommandContext<FabricClientCommandSource> context) {
        return executeReloadModels(context, ShapezModelHelper::clearAllModel, i -> Text.literal(String.valueOf(i)));
    }

    private static int executeReloadModels(CommandContext<FabricClientCommandSource> context, IntSupplier supplier, IntFunction<Text> function) {
        int i = supplier.getAsInt();
        context.getSource().sendFeedback(function.apply(i));
        return i;
    }
}
