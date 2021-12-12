package cn.nulladev.vanillamagic.block;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class AdjusterCraftSlot extends Slot {
    public AdjusterCraftSlot(Container container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
