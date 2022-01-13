package cn.nulladev.vanillamagic.item;

import cn.nulladev.vanillamagic.client.CoreBagMenu;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class CoreBag extends Item {

    public static final int SIZE = 27;
    public static final String TAG_ITEMS = "Items";

    public CoreBag(Properties props) {
        super(props.stacksTo(1));
    }

    public static ListTag getListTag(ItemStack stack) {
        if (stack.getOrCreateTag().contains(TAG_ITEMS)) {
            return stack.getOrCreateTag().getList(TAG_ITEMS, Tag.TAG_COMPOUND);
        } else {
            return new ListTag();
        }
    }

    public static void setListTag(ItemStack stack, ListTag list) {
        stack.getOrCreateTag().put(TAG_ITEMS, list);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            MenuProvider menu = new SimpleMenuProvider((windowId, playerInventory, playerEntity) -> new CoreBagMenu(windowId, playerInventory, stack), stack.getDisplayName());
            NetworkHooks.openGui((ServerPlayer) player, menu, buf -> {
                buf.writeBoolean(hand == InteractionHand.MAIN_HAND);
            });
            //player.openMenu(container);
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

}


