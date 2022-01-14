package cn.nulladev.vanillamagic.client;

import cn.nulladev.vanillamagic.core.VMRegistry;
import cn.nulladev.vanillamagic.core.VanillaMagic;
import cn.nulladev.vanillamagic.item.CoreBag;
import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import com.lcy0x1.base.menu.BaseContainerMenu;
import com.lcy0x1.core.util.SpriteManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CoreBagMenu extends BaseContainerMenu<CoreBagMenu> {

    public static class CoreBagContainer extends BaseContainer<CoreBagMenu> {

        public CoreBagContainer(CoreBagMenu menu, ItemStack bag) {
            super(CoreBag.SIZE, menu);
            ListTag list = CoreBag.getListTag(bag);
            for (int i = 0; i < Math.min(CoreBag.SIZE, list.size()); i++) {
                this.setItem(i, ItemStack.of(list.getCompound(i)));
            }
        }

        @Override
        public boolean canPlaceItem(int index, ItemStack stack) {
            return stack.getItem() instanceof ConceptCore;
        }
    }

    public static final SpriteManager CORE_BAG = new SpriteManager(VanillaMagic.MODID, "core_bag");

    private final Player player;
    private final ItemStack bag;

    public static CoreBagMenu fromNetwork(int windowId, Inventory inv, FriendlyByteBuf buf) {
        InteractionHand hand = buf.readBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        return new CoreBagMenu(windowId, inv, inv.player.getItemInHand(hand));
    }

    public CoreBagMenu(int windowId, Inventory inventory, ItemStack bag) {
        super(VMRegistry.MT_CORE_BAG.get(), windowId, inventory, CORE_BAG, menu -> new CoreBagContainer(menu, bag), false);
        this.player = inventory.player;
        this.bag = bag;
        this.addSlot("grid", stack -> stack.getItem() instanceof ConceptCore);
        if (!this.player.level.isClientSide()) {
            ListTag tag = CoreBag.getListTag(bag);
            for (int i = 0; i < tag.size(); i++) {
                this.container.setItem(i, ItemStack.of((CompoundTag) tag.get(i)));
            }
        }
    }

    @Override
    protected boolean shouldLock(Inventory inv, int slot) {
        return slot == inv.selected && inv.getItem(slot).getItem() instanceof CoreBag;
    }

    @Override
    public void removed(Player player) {
        if (!player.level.isClientSide) {
            ListTag list = new ListTag();
            for (int i = 0; i < this.container.getContainerSize(); i++) {
                list.add(i, this.container.getItem(i).save(new CompoundTag()));
            }
            CoreBag.setListTag(this.bag, list);
        }
        super.removed(player);
    }
}
