package cn.nulladev.sheathmagic.container;

import cn.nulladev.sheathmagic.menu.BasicCrystalMenu;
import net.minecraft.world.SimpleContainer;

public class BasicCrystalContainer extends SimpleContainer {

    private final BasicCrystalMenu menu;
    private boolean updating = false;

    public BasicCrystalContainer(int slotCount, BasicCrystalMenu menu) {
        super(slotCount);
        this.menu = menu;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (!updating) {
            updating = true;
            menu.slotsChanged(this);
            updating = false;
        }
    }
}
