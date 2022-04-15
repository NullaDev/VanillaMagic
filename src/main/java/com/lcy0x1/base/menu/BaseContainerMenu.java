package com.lcy0x1.base.menu;

import com.lcy0x1.core.util.SpriteManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@ParametersAreNonnullByDefault
public class BaseContainerMenu<T extends BaseContainerMenu<T>> extends ContainerScreen {

    public static class BaseContainer<T extends BaseContainerMenu<T>> extends Container {

        protected final T parent;
        private boolean updating = false;

        public BaseContainer(int size, T menu) {
            super(size);
            parent = menu;
        }

        @Override
        public void setChanged() {
            super.setChanged();
            if (!updating) {
                updating = true;
                parent.slotsChanged(this);
                updating = false;
            }
        }

    }

    public final PlayerInventory inventory;
    public final Container container;
    public final SpriteManager sprite;
    private int added = 0;
    private final boolean isVirtual;

    @SuppressWarnings("unchecked")
    protected BaseContainerMenu(ContainerType<?> type, int wid, PlayerInventory plInv, SpriteManager manager, Function<T, SimpleContainer> factory, boolean isVirtual) {
        super(type, wid);
        this.inventory = plInv;
        container = factory.apply((T) this);
        sprite = manager;
        int x = manager.getPlInvX();
        int y = manager.getPlInvY();
        this.bindPlayerInventory(plInv, x, y);
        this.isVirtual = isVirtual;
    }

    protected void bindPlayerInventory(PlayerInventory plInv, int x, int y) {
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(plInv, j + i * 9 + 9, x + j * 18, y + i * 18));
        for (int k = 0; k < 9; ++k)
            if (shouldLock(plInv, k))
                this.addSlot(new SlotLocked(plInv, k, x + k * 18, y + 58));
            else
                this.addSlot(new Slot(plInv, k, x + k * 18, y + 58));
    }

    protected boolean shouldLock(PlayerInventory inv, int slot) {
        return false;
    }

    protected void addSlot(String name, Predicate<ItemStack> pred) {
        sprite.getSlot(name, (x, y) -> new PredSlot(container, added++, x, y, pred), this::addSlot);
    }

    protected void addSlot(String name, Predicate<ItemStack> pred, Consumer<PredSlot> modifier) {
        sprite.getSlot(name, (x, y) -> {
            PredSlot s = new PredSlot(container, added++, x, y, pred);
            modifier.accept(s);
            return s;
        }, this::addSlot);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity pl, int id) {
        ItemStack stack = slots.get(id).getItem();
        int n = container.getContainerSize();
        if (id >= 36) {
            moveItemStackTo(stack, 0, 36, true);
        } else {
            moveItemStackTo(stack, 36, 36 + n, false);
        }
        container.setChanged();
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return player.isAlive();
    }

    @Override
    public void removed(PlayerEntity player) {
        if (isVirtual && !player.level.isClientSide())
            clearContainer(player, container);
        super.removed(player);
    }
}
