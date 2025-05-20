package github.duyan013m.create_shapez.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ShapezCommands {
    public static final String SHAPEZ = "shapez2u";
    public static final String CONFIG = "config";
    public static final String RELOAD = "reload";

    public static void register() {
        LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal(SHAPEZ);
        ShapezGiveCommand.register(builder);
        ShapezConfigCommand.register(builder);
        ShapezProcessingCommand.register(builder);
        // just for command tab complete, real implementations are in ShapezCommandClient
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(builder));
    }
}
