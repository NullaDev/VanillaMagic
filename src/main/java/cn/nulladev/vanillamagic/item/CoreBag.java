package cn.nulladev.vanillamagic.item;

import cn.nulladev.vanillamagic.client.CoreBagMenu;
import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;

public class CoreBag extends Item {

    public static final int SIZE = 27;
    public static final String TAG_ITEMS = "Items";

    public CoreBag(Properties props) {
        super(props.stacksTo(1));
    }

    public static ListNBT getListTag(ItemStack stack) {
        if (stack.getOrCreateTag().contains(TAG_ITEMS)) {
            return stack.getOrCreateTag().getList(TAG_ITEMS, Tag.TAG_COMPOUND);
        } else {
            return new ListNBT();
        }
    }

    public static void setListTag(ItemStack stack, ListNBT list) {
        stack.getOrCreateTag().put(TAG_ITEMS, list);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            MenuProvider menu = new SimpleMenuProvider((windowId, playerInventory, playerEntity) -> new CoreBagMenu(windowId, playerInventory, stack), stack.getDisplayName());
            NetworkHooks.openGui((ServerPlayer) player, menu, buf -> buf.writeBoolean(hand == InteractionHand.MAIN_HAND));
            //player.openMenu(container);
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        ListTag list = getListTag(stack);
        boolean changed = false;
        for (int i = 0; i < Math.min(SIZE, list.size()); i++) {
            ItemStack core = ItemStack.of(list.getCompound(i));
            if (ConceptCore.getCD(core) > 0) {
                ConceptCore.setCD(core, ConceptCore.getCD(core) - 1);
                changed = true;
                list.set(i, core.save(new CompoundTag()));
            }
        }
        if (changed)
            setListTag(stack, list);
    }

}


