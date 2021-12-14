package cn.nulladev.vanillamagic.block;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class AdjusterContainer implements Container {

    public ItemStack[] craft_items = new ItemStack[25];
    public ItemStack crystal = ItemStack.EMPTY;
    public ItemStack result = ItemStack.EMPTY;
    public final AbstractContainerMenu menu;

    public AdjusterContainer(AbstractContainerMenu menu) {
        this.menu = menu;
        for (int i = 0; i < craft_items.length; i++) {
            craft_items[i] = ItemStack.EMPTY;
        }
    }

    @Override
    public int getContainerSize() {
        return craft_items.length + 2;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.craft_items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return crystal.isEmpty();
    }

    @Override
    public ItemStack getItem(int index) {
        if (index > getContainerSize())
            return ItemStack.EMPTY;
        else if (index == craft_items.length + 1)
            return result;
        else if (index == craft_items.length)
            return crystal;
        else
            return craft_items[index];
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        if (count <= 0)
            return ItemStack.EMPTY;
        ItemStack stack = ItemStack.EMPTY;
        if (index == craft_items.length) {
            stack = crystal.copy();
            crystal = ItemStack.EMPTY;
        } else if (index < craft_items.length) {
            stack = craft_items[index].copy();
            craft_items[index] = ItemStack.EMPTY;
        }
        if (!stack.isEmpty())
            this.menu.slotsChanged(this);
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = ItemStack.EMPTY;
        if (index == craft_items.length) {
            stack = crystal.copy();
            crystal = ItemStack.EMPTY;
        } else if (index < craft_items.length) {
            stack = craft_items[index].copy();
            craft_items[index] = ItemStack.EMPTY;
        }
        return stack;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        if (index == craft_items.length)
            crystal = stack;
        else
            craft_items[index] = stack;
        this.menu.slotsChanged(this);
    }

    @Override
    public void setChanged() {

    }

    @Override
    public boolean stillValid(Player p_18946_) {
        return true;
    }

    @Override
    public void clearContent() {
        crystal = ItemStack.EMPTY;
        result = ItemStack.EMPTY;
        for (int i = 0; i < craft_items.length; i++) {
            craft_items[i] = ItemStack.EMPTY;
        }
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    public void clearInvalidItems() {

    }
}
