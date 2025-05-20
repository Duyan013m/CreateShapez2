package github.duyan013m.create_shapez.command.server;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import github.duyan013m.create_shapez.command.ShapezCommands;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.IntFunction;
import java.util.function.IntSupplier;

public class ShapezReloadCommandServer {

    public static void register(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builder.then(CommandManager.literal(ShapezCommands.RELOAD)
                .executes(context -> executeReload(context, -1))
                .then(CommandManager.literal("model")
                        .executes(context -> executeReloadModels(context,()->0,i->Text.literal(String.valueOf(i))))
                        .then(CommandManager.literal("all")
                                .executes(context -> executeReloadModels(context,()->0,i->Text.literal(String.valueOf(i)))))));
    }

    private static int executeReload(CommandContext<ServerCommandSource> context, int i) {
        throw new NotImplementedException();
    }

    private static int executeReloadAll(CommandContext<ServerCommandSource> context) {
        throw new NotImplementedException();
    }

    private static int executeReloadModels(CommandContext<ServerCommandSource> context, IntSupplier supplier, IntFunction<Text> function) {
        throw new NotImplementedException();
    }
}
