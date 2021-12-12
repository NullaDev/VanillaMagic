package cn.nulladev.vanillamagic.block;

import cn.nulladev.vanillamagic.VMItems;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CrystalSlot extends Slot {
    public CrystalSlot(Container container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if (stack.getItem().equals(VMItems.SPACE_CRYSTAL_SMALL.get()))
            return true;
        if (stack.getItem().equals(VMItems.SPACE_CRYSTAL_MIDDLE.get()))
            return true;
        if (stack.getItem().equals(VMItems.SPACE_CRYSTAL_LARGE.get()))
            return true;
        return false;
    }
}
