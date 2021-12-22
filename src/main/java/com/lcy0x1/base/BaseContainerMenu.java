package com.lcy0x1.base;

import com.lcy0x1.core.util.SpriteManager;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BaseContainerMenu<T extends BaseContainerMenu<T>> extends AbstractContainerMenu {

    public static class BaseContainer<T extends BaseContainerMenu<T>> extends SimpleContainer {

        protected final T parent;

        public BaseContainer(int size, T menu) {
            super(size);
            parent = menu;
        }

        @Override
        public void setChanged() {
            super.setChanged();
            parent.slotsChanged(this);
        }

    }

    public final Inventory inventory;
    public final SimpleContainer container;
    public final SpriteManager sprite;
    private int added = 0;

    @SuppressWarnings("unchecked")
    protected BaseContainerMenu(MenuType<?> type, int wid, Inventory plInv, SpriteManager manager, Function<T, BaseContainer<T>> factory) {
        super(type, wid);
        this.inventory = plInv;
        container = factory.apply((T) this);
        sprite = manager;
        int x = manager.getPlInvX();
        int y = manager.getPlInvY();
        this.bindPlayerInventory(plInv, x, y);
    }

    protected void bindPlayerInventory(Inventory plInv, int x, int y) {
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(plInv, j + i * 9 + 9, x + j * 18, y + i * 18));
        for (int k = 0; k < 9; ++k)
            this.addSlot(new Slot(plInv, k, x + k * 18, y + 58));
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
    public ItemStack quickMoveStack(Player pl, int id) {
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
    public boolean stillValid(Player player) {
        return player.isAlive();
    }

    @Override
    public void removed(Player player) {
        if (!player.level.isClientSide())
            clearContainer(player, container);
        super.removed(player);
    }
}
