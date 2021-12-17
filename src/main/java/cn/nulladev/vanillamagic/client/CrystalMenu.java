package cn.nulladev.vanillamagic.client;

import cn.nulladev.vanillamagic.VMItems;
import cn.nulladev.vanillamagic.VMRegistry;
import com.lcy0x1.base.PredSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CrystalMenu extends AbstractContainerMenu {

    private final Player player;
    private final ItemStack crystal;
    private final Container dummy_container;

    private int size;

    public static CrystalMenu fromNetwork(int windowId, Inventory inv, FriendlyByteBuf buf) {
        InteractionHand hand = buf.readBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        return new CrystalMenu(windowId, inv, inv.player.getItemInHand(hand));
    }


    public CrystalMenu(int windowID, Inventory inventory, ItemStack crystal) {
        super(VMRegistry.CRYSTAL.get(), windowID);
        this.player = inventory.player;
        this.crystal = crystal;
        this.dummy_container = new SimpleContainer() {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        };

        if (this.crystal.getItem() == VMItems.SPACE_CRYSTAL_LARGE.get())
            size = 5;
        else if (this.crystal.getItem() == VMItems.SPACE_CRYSTAL_MIDDLE.get())
            size = 4;
        else
            size = 3;

        this.addSlot(new PredSlot(this.dummy_container, 0, 124, 45, stack -> false));

        for (int i = 0; i < size * size; i++)
            this.addSlot(new Slot(this.dummy_container, i + 1, 8 + 18 * (i % size), 5 + 18 * (i / size)));

        for (int i = 0; i < 27; i++)
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 155));

        for (int i = 0; i < 9; i++)
            this.addSlot(new Slot(inventory, 9 + i, 8 + 18 * (i % 9), 97 + 18 * (i / 9)));
    }


    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.index != 0 && super.canTakeItemForPickAll(stack, slot);
    }

}
