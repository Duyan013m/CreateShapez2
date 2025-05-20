package github.duyan013m.create_shapez.util;

import com.simibubi.create.content.kinetics.belt.behaviour.TransportedItemStackHandlerBehaviour;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.processing.recipe.ProcessingInventory;
import com.simibubi.create.foundation.item.SmartInventory;
import com.simibubi.create.foundation.utility.VecHelper;
import github.duyan013m.create_shapez.CreateShapez;
import github.duyan013m.create_shapez.processing.common.ShapezInciser;
import github.duyan013m.create_shapez.processing.common.ShapezSeparator;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandlerContainer;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ShapezRecipeHelper {
    public static boolean bcCheck(SmartInventory inputInventory, SmartInventory outputInventory) {
        return inputInventory.getStackInSlot(0).isOf(Items.CRAFTING_TABLE);
    }

    public static void bcCraft(SmartInventory inputInventory, SmartInventory outputInventory) {
        inputInventory.getStackInSlot(0).decrement(1);
        outputInventory.setStackInSlot(0, new ItemStack(Items.FURNACE));
    }

    public static boolean bmCheck(SmartInventory inputInventory, SmartInventory outputInventory) {
        return inputInventory.getStackInSlot(0).isOf(Items.CRAFTING_TABLE);
    }

    public static void bmCraft(SmartInventory inputInventory, SmartInventory outputInventory) {
        inputInventory.getStackInSlot(0).decrement(1);
        outputInventory.setStackInSlot(0, new ItemStack(Items.FURNACE));
    }

    public static boolean dCheck(ItemStack transported, ItemStack heldItem) {
        return transported.isOf(Items.CRAFTING_TABLE) && heldItem.isOf(Items.CRAFTING_TABLE);
    }

    public static void dCraft(TransportedItemStack transported, ItemStack heldItem, TransportedItemStackHandlerBehaviour handler) {
        List<TransportedItemStack> collect = new ArrayList<>();
        TransportedItemStack copy = transported.copy();
        copy.stack = new ItemStack(Items.FURNACE);
        collect.add(copy);
        TransportedItemStack left = transported.copy();
        left.stack.decrement(1);
        handler.handleProcessingOnItem(transported, TransportedItemStackHandlerBehaviour.TransportedResult.convertToAndLeaveHeld(collect, left));
        heldItem.decrement(1);
    }

    public static boolean cCheck(ProcessingInventory inventory) {
        return inventory.getStackInSlot(0).isOf(Items.CRAFTING_TABLE);
    }

    public static void cCraft(ProcessingInventory inventory) {
        inventory.setStackInSlot(0, new ItemStack(Items.FURNACE));
    }

    public static boolean pwCheck(ItemEntity itemEntity) {
        return itemEntity.getStack().isOf(Items.CRAFTING_TABLE);
    }

    public static void pwCraft(ItemEntity itemEntity) {
        ItemStack item = itemEntity.getStack();
        World world = itemEntity.getWorld();
        if (item.getCount() == 1) {
            itemEntity.setStack(new ItemStack(Items.FURNACE));
        } else {
            ItemEntity created = new ItemEntity(world, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), new ItemStack(Items.FURNACE));
            created.setToDefaultPickupDelay();
            created.setVelocity(VecHelper.offsetRandomly(Vec3d.ZERO, world.random, .05f));
            world.spawnEntity(created);
            item.decrement(1);
        }
    }

    public static boolean pbCheck(TransportedItemStack input) {
        return input.stack.isOf(Items.CRAFTING_TABLE);
    }

    public static void pbCraft(TransportedItemStack input, List<ItemStack> outputList) {
        outputList.add(new ItemStack(Items.FURNACE));
    }

    public static boolean mCheck(ItemStackHandlerContainer inputInv, ItemStackHandler outputInv) {
        return inputInv.getStackInSlot(0).isOf(Items.CRAFTING_TABLE);
    }

    public static void mCraft(ItemStackHandlerContainer inputInv, ItemStackHandler outputInv) {
        inputInv.getStackInSlot(0).decrement(1);
        outputInv.setStackInSlot(0, new ItemStack(Items.FURNACE));
    }

    public static boolean sCheck(ProcessingInventory inventory) {
        return inventory.getStackInSlot(0).isOf(Items.CRAFTING_TABLE);
    }

    public static void sCraft(ProcessingInventory inventory) {
        inventory.setStackInSlot(0, new ItemStack(Items.FURNACE));
    }
}
