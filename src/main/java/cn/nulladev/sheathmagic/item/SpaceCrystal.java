package cn.nulladev.sheathmagic.item;

import cn.nulladev.sheathmagic.menu.BasicCrystalMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class SpaceCrystal extends Item {

    private final int SIZE;

    public SpaceCrystal(Properties properties, int size) {
        super(properties.stacksTo(1));
        this.SIZE = size;
    }

    public static int getSize(ItemStack stack) {
        if (stack.getItem() instanceof SpaceCrystal crystal) {
            return crystal.SIZE;
        }
        else {
            return 0;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            MenuProvider container = new SimpleMenuProvider((windowId, playerInventory, playerEntity) -> new BasicCrystalMenu(windowId, playerInventory), stack.getDisplayName());
            NetworkHooks.openScreen((ServerPlayer) player, container, buf -> buf.writeBoolean(hand == InteractionHand.MAIN_HAND));

        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
