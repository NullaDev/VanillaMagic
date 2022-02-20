package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ConceptCoreShulkerShell extends ConceptCore {

    public ConceptCoreShulkerShell(Properties props) {
        super(props);
        this.setUsingCD(200);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        //Do nothing!
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    @Override
    public ItemStack getMachineOutput(ItemStack coreStack) {
        return new ItemStack(Items.SHULKER_SHELL);
    }

}