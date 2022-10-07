package cn.nulladev.sheathmagic.menu;

import cn.nulladev.sheathmagic.container.BasicCrystalContainer;
import cn.nulladev.sheathmagic.crafting.BasicSpaceCrystalRecipe;
import cn.nulladev.sheathmagic.item.SpaceCrystal;
import cn.nulladev.sheathmagic.registry.SheathItems;
import cn.nulladev.sheathmagic.registry.SheathMenus;
import cn.nulladev.sheathmagic.registry.SheathRecipeTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;
import java.util.Optional;

public class BasicCrystalMenu extends AbstractContainerMenu {
    public static final int SIZE = 3;

    private final Player player;
    private final Container container;

    public BasicCrystalMenu(int windowID, Inventory inventory) {
        super(SheathMenus.BASIC_CRYSTAL_MENU.get(), windowID);

        this.player = inventory.player;
        this.bindPlayerInventory(inventory);
        container = new BasicCrystalContainer(SIZE * SIZE + 1, this);
    }

    public static BasicCrystalMenu fromNetwork(int windowId, Inventory inv, FriendlyByteBuf buf) {
        InteractionHand hand = buf.readBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        return new BasicCrystalMenu(windowId, inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = slots.get(index).getItem();
        int n = container.getContainerSize();
        if (index >= 36) {
            moveItemStackTo(stack, 0, 36, true);
        } else {
            moveItemStackTo(stack, 36, 36 + n, false);
        }
        container.setChanged();
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        if (player.getMainHandItem().getItem() == SheathItems.SPACE_CRYSTAL_BASIC.get()) {
            return true;
        } else {
            return player.getOffhandItem().getItem() == SheathItems.SPACE_CRYSTAL_BASIC.get();
        }
    }

    protected void bindPlayerInventory(Inventory plInv) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(plInv, j + i * 9 + 9, 8 + j * 18, 78 + i * 18));
            }
        }

        // action bar
        for (int k = 0; k < 9; ++k) {
            if (k == plInv.selected && plInv.getItem(k).getItem() instanceof SpaceCrystal) {
                this.addSlot(new Slot(plInv, k, 8 + k * 18, 78 + 58) {
                    @Override
                    public boolean mayPickup(Player player) {
                        return false;
                    }

                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return false;
                    }
                });
            } else {
                this.addSlot(new Slot(plInv, k, 8 + k * 18, 78 + 58));
            }
        }
    }

    @Override
    public void slotsChanged(Container container) {

        if (!this.player.level.isClientSide) {
            Optional<BasicSpaceCrystalRecipe> optional = Objects.requireNonNull(player.getServer()).getRecipeManager()
                    .getRecipeFor(SheathRecipeTypes.SPACE_CRYSTAL_BASIC_RECIPE_TYPE.get(),
                            (BasicCrystalContainer) this.container,
                            this.player.level);

            if (optional.isPresent()) {
                BasicSpaceCrystalRecipe recipe = optional.get();
                ItemStack itemStack = recipe.assemble((BasicCrystalContainer) this.container);
                this.container.setItem(SIZE * SIZE, itemStack);
            }
        }

        super.slotsChanged(container);
    }

    @Override
    protected void clearContainer(Player player, Container container) {

        // Optional<BasicSpaceCrystalRecipe> optional = Objects.requireNonNull(player.getServer()).getRecipeManager()
        //         .getRecipeFor(SheathRecipeTypes.SPACE_CRYSTAL_BASIC_RECIPE_TYPE.get(),
        //                 (BasicCrystalContainer) this.container,
        //                 this.player.level);
        // if (optional.isPresent()) {
        //     for (int i = 0; i < SIZE * SIZE; i++) {
        //         ItemStack stack = container.getItem(i);
        //         if (!stack.isEmpty())
        //             stack.shrink(1);
        //     }
        //     crystal.shrink(1);
        // }
        // 
        super.clearContainer(player, container);
    }

    @Override
    public void removed(Player player) {
        // if (!player.level.isClientSide()) {
        //     clearContainer(player, container);
        // }

        super.removed(player);
    }
}
