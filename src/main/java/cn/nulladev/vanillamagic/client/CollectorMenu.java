package cn.nulladev.vanillamagic.client;

import cn.nulladev.vanillamagic.core.VMRegistry;
import cn.nulladev.vanillamagic.core.VanillaMagic;
import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import com.lcy0x1.base.menu.BaseContainerMenu;
import com.lcy0x1.core.util.SpriteManager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class CollectorMenu extends BaseContainerMenu<CollectorMenu> {

    public static final int SIZE = 28;

    public static class CoreMachineContainer extends BaseContainer<CollectorMenu> {
        public CoreMachineContainer(CollectorMenu menu) {
            super(SIZE, menu);
        }

        @Override
        public boolean canPlaceItem(int index, ItemStack stack) {
            if (index == 0)
                return stack.getItem() instanceof ConceptCore;
            else
                return false;
        }
    }

    public static final SpriteManager CORE_MACHINE = new SpriteManager(VanillaMagic.MODID, "core_machine");

    public CollectorMenu(int windowId, Inventory inventory) {
        super(VMRegistry.MT_COLLECTOR.get(), windowId, inventory, CORE_MACHINE, CoreMachineContainer::new, false);
        this.addSlot("core", stack -> stack.getItem() instanceof ConceptCore);
        this.addSlot("grid", stack -> false);
    }

}
