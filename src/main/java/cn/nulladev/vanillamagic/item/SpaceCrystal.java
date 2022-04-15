package cn.nulladev.vanillamagic.item;

import cn.nulladev.vanillamagic.client.CrystalMenu;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SpaceCrystal extends Item {

    public final int size;

    public SpaceCrystal(Properties props, int size) {
        super(props);
        this.size = size;
    }

    public static int getSize(ItemStack stack) {
        if (stack.getItem() instanceof SpaceCrystal)
            return ((SpaceCrystal) stack.getItem()).size;
        else
            return 0;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClientSide) {
            ItemStack stack = player.getItemInHand(hand);
            INamedContainerProvider container = new SimpleNamedContainerProvider((windowId, playerInventory, playerEntity) -> new CrystalMenu(windowId, playerInventory, stack), stack.getDisplayName());
            NetworkHooks.openGui((ServerPlayerEntity) player, container, buf -> buf.writeBoolean(hand == Hand.MAIN_HAND));
            //player.openMenu(container);
        }
        return ActionResult.success(player.getItemInHand(hand));
    }

}
