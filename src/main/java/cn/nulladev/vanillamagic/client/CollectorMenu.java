package cn.nulladev.vanillamagic.client;

import cn.nulladev.vanillamagic.core.VMRegistry;
import cn.nulladev.vanillamagic.core.VanillaMagic;
import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import com.lcy0x1.base.menu.BaseContainerMenu;
import com.lcy0x1.core.util.SpriteManager;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;

import java.util.function.Function;

public class CollectorMenu extends BaseContainerMenu<CollectorMenu> {

	public static final int SIZE = 28;

	public static final SpriteManager CORE_MACHINE = new SpriteManager(VanillaMagic.MODID, "core_machine");

	public CollectorMenu(int windowId, Inventory inventory) {
		this(windowId, inventory, menu -> new BaseContainer<>(SIZE, menu));
	}

	public CollectorMenu(int windowId, Inventory inventory, SimpleContainer container) {
		this(windowId, inventory, e -> container);
	}

	private CollectorMenu(int windowId, Inventory inventory, Function<CollectorMenu, SimpleContainer> func) {
		super(VMRegistry.MT_COLLECTOR.get(), windowId, inventory, CORE_MACHINE, func, false);
		this.addSlot("core", stack -> stack.getItem() instanceof ConceptCore && ((ConceptCore) stack.getItem()).isUsable(stack));
		this.addSlot("grid", stack -> false);
	}

}
