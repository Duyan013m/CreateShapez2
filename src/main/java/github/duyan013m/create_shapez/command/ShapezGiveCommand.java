package github.duyan013m.create_shapez.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.duyan013m.create_shapez.ShapezItems;
import github.duyan013m.create_shapez.util.ShapezItemHelper;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;

public class ShapezGiveCommand {

    public static final String GIVE = "give";
    public static final String TARGETS = "targets";
    public static final String NAME = "name";
    public static final String COUNT = "count";

    public static void register(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builder.then(CommandManager.literal(GIVE)
                .requires((source) -> source.hasPermissionLevel(2))
                .then(CommandManager.argument(TARGETS, EntityArgumentType.players())
                        .then(CommandManager.argument(NAME, StringArgumentType.string())
                                .executes(context -> execute(context, false))
                                .then(CommandManager.argument(COUNT, IntegerArgumentType.integer(1))
                                        .executes(context -> execute(context, true))))));
    }

    private static int execute(CommandContext<ServerCommandSource> context, boolean hasCount) throws CommandSyntaxException {
        return execute(context.getSource(),
                StringArgumentType.getString(context, NAME),
                EntityArgumentType.getPlayers(context, TARGETS),
                hasCount ? IntegerArgumentType.getInteger(context, COUNT) : 1
        );
    }

    private static int execute(ServerCommandSource source, String name, Collection<ServerPlayerEntity> targets, int count) {
        Item item = ShapezItems.Shapez2;
        ItemStack itemStack = new ItemStack(item, count);
        ShapezItemHelper.setName(itemStack, name);
        targets.forEach(serverPlayerEntity -> serverPlayerEntity.getInventory().insertStack(itemStack));
        if (targets.size() == 1) {
            source.sendFeedback(() -> Text.translatable("commands.give.success.single", count, itemStack.toHoverableText(), (targets.iterator().next()).getDisplayName()), true);
        } else {
            source.sendFeedback(() -> Text.translatable("commands.give.success.single", count, itemStack.toHoverableText(), targets.size()), true);
        }
        return targets.size();
    }
}
