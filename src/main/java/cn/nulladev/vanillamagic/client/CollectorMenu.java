package cn.nulladev.vanillamagic.client;

import cn.nulladev.vanillamagic.core.VMRegistry;
import cn.nulladev.vanillamagic.core.VanillaMagic;
import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import com.lcy0x1.base.menu.BaseContainerMenu;
import com.lcy0x1.core.util.SpriteManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;

public class CollectorMenu extends BaseContainerMenu<CollectorMenu> {

    public static final int SIZE = 28;

    public static final SpriteManager CORE_MACHINE = new SpriteManager(VanillaMagic.MODID, "core_machine");

    public CollectorMenu(int windowId, Inventory inventory, BlockPos pos, Level level) {
        super(VMRegistry.MT_COLLECTOR.get(), windowId, inventory, CORE_MACHINE, menu -> level.getBlockEntity(pos, VMRegistry.BE_COLLECTOR.get()).get().getContainer(), false);
        this.addSlot("core", stack -> stack.getItem() instanceof ConceptCore && ((ConceptCore)stack.getItem()).isUsable(stack));
        this.addSlot("grid", stack -> false);
    }

}
