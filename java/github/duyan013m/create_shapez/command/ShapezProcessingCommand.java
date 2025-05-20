package github.duyan013m.create_shapez.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.duyan013m.create_shapez.ShapezItems;
import github.duyan013m.create_shapez.processing.ShapezProcessings;
import github.duyan013m.create_shapez.processing.common.ShapezUnstacker;
import github.duyan013m.create_shapez.processing.shape.*;
import github.duyan013m.create_shapez.util.ShapezItemHelper;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Hand;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ShapezProcessingCommand {

    public static final String PROCESSING = "processing";
    public static final String TARGETS = "targets";

    public static void register(LiteralArgumentBuilder<ServerCommandSource> builder) {
        RequiredArgumentBuilder<ServerCommandSource, EntitySelector> targets = CommandManager.argument(TARGETS, EntityArgumentType.players());
        registers(targets);
        registerc(targets);
        builder.then(CommandManager.literal(PROCESSING).then(targets));
    }

    private static void registers(RequiredArgumentBuilder<ServerCommandSource, EntitySelector> builder) {
        builder.then(CommandManager.literal(ShapezProcessings.cutter.getCmd())
                        .executes(context -> executeString12(context, ShapezCutter::cut)))
                .then(CommandManager.literal(ShapezProcessings.halfDestroyer.getCmd())
                        .executes(context -> executeString11(context, ShapezHalfDestroyer::halfDestroy)))
                .then(CommandManager.literal(ShapezProcessings.pinPusher.getCmd())
                        .executes(context -> executeString11(context, ShapezPinPusher::pinPush)))
                .then(CommandManager.literal(ShapezProcessings.rotator.getCmd())
                        .then(CommandManager.literal(ShapezRotator.CLOCKWISE)
                                .executes(context -> executeString11(context, ShapezRotator::clockwise)))
                        .then(CommandManager.literal(ShapezRotator.CENTRAL_SYMMETRY)
                                .executes(context -> executeString11(context, ShapezRotator::centralSymmetry)))
                        .then(CommandManager.literal(ShapezRotator.COUNTERCLOCKWISE)
                                .executes(context -> executeString11(context, ShapezRotator::counterclockwise))))
                .then(CommandManager.literal(ShapezProcessings.stacker.getCmd())
                        .executes(context -> executeString21(context, ShapezStacker::stack)))
                .then(CommandManager.literal(ShapezProcessings.swapper.getCmd())
                        .executes(context -> executeString22(context, ShapezSwapper::swap)))
                .then(CommandManager.literal(ShapezProcessings.unstacker.getCmd())
                        .executes(context -> executeString12(context, ShapezUnstacker::unstack)));

    }

    private static int executeString11(CommandContext<ServerCommandSource> context, Function<String, String> function) throws CommandSyntaxException {
        return EntityArgumentType.getPlayers(context, TARGETS).stream().mapToInt(serverPlayerEntity -> {
            ItemStack mainHandStack = serverPlayerEntity.getMainHandStack();
            if (!mainHandStack.isOf(ShapezItems.Shapez2)) {
                return 0;
            }
            serverPlayerEntity.setStackInHand(Hand.MAIN_HAND,
                    ShapezItemHelper.setName(mainHandStack, function.apply(ShapezItemHelper.getName(mainHandStack))));
            return 1;
        }).sum();
    }

    private static int executeString12(CommandContext<ServerCommandSource> context, Function<String, String[]> function) throws CommandSyntaxException {
        return EntityArgumentType.getPlayers(context, TARGETS).stream().mapToInt(serverPlayerEntity -> {
            ItemStack mainHandStack = serverPlayerEntity.getMainHandStack();
            if (!mainHandStack.isOf(ShapezItems.Shapez2) || !serverPlayerEntity.getOffHandStack().isEmpty()) {
                return 0;
            }
            String[] strings = function.apply(ShapezItemHelper.getName(mainHandStack));
            serverPlayerEntity.setStackInHand(Hand.MAIN_HAND, ShapezItemHelper.setName(mainHandStack, strings[0]));
            serverPlayerEntity.setStackInHand(Hand.OFF_HAND, ShapezItemHelper.setName(new ItemStack(ShapezItems.Shapez2), strings[1]));
            return 1;
        }).sum();
    }

    private static int executeString21(CommandContext<ServerCommandSource> context, BiFunction<String, String, String> function) throws CommandSyntaxException {
        return EntityArgumentType.getPlayers(context, TARGETS).stream().mapToInt(serverPlayerEntity -> {
            ItemStack mainHandStack = serverPlayerEntity.getMainHandStack();
            ItemStack offHandStack = serverPlayerEntity.getOffHandStack();
            if (!mainHandStack.isOf(ShapezItems.Shapez2) || !offHandStack.isOf(ShapezItems.Shapez2)) {
                return 0;
            }
            serverPlayerEntity.setStackInHand(Hand.MAIN_HAND, ShapezItemHelper.setName(mainHandStack,
                    function.apply(ShapezItemHelper.getName(mainHandStack), ShapezItemHelper.getName(offHandStack))));
            serverPlayerEntity.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
            return 1;
        }).sum();
    }

    private static int executeString22(CommandContext<ServerCommandSource> context, BiFunction<String, String, String[]> function) throws CommandSyntaxException {
        return EntityArgumentType.getPlayers(context, TARGETS).stream().mapToInt(serverPlayerEntity -> {
            ItemStack mainHandStack = serverPlayerEntity.getMainHandStack();
            ItemStack offHandStack = serverPlayerEntity.getOffHandStack();
            if (!mainHandStack.isOf(ShapezItems.Shapez2) || !offHandStack.isOf(ShapezItems.Shapez2)) {
                return 0;
            }
            String[] strings = function.apply(ShapezItemHelper.getName(mainHandStack), ShapezItemHelper.getName(serverPlayerEntity.getOffHandStack()));
            serverPlayerEntity.setStackInHand(Hand.MAIN_HAND, ShapezItemHelper.setName(mainHandStack, strings[0]));
            serverPlayerEntity.setStackInHand(Hand.OFF_HAND, ShapezItemHelper.setName(offHandStack, strings[1]));
            return 1;
        }).sum();
    }

    private static void registerc(RequiredArgumentBuilder<ServerCommandSource, EntitySelector> builder) {
    }
}
