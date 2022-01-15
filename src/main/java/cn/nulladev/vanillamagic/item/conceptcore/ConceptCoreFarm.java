package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ConceptCoreFarm extends ConceptCoreWithContent {

    public ConceptCoreFarm(Properties props) {
        super(props);
        this.setUsingCD(1200);
    }

    @Override
    public boolean isContentValid(ItemStack stack) {
        if (stack.getItem() == Items.POTATO)
            return true;
        else if (stack.getItem() == Items.CARROT)
            return true;
        else if (stack.getItem() == Items.BEETROOT_SEEDS)
            return true;
        else if (stack.getItem() == Items.WHEAT_SEEDS)
            return true;
        else
            return false;

    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(false)) {
            ItemStack content = getContent(player.getItemInHand(hand));
            if (content.getItem() == Items.POTATO)
                player.getFoodData().eat(1, 0.6F);
            else if (content.getItem() == Items.CARROT)
                player.getFoodData().eat(3, 1.2F);
            else if (content.getItem() == Items.BEETROOT_SEEDS)
                player.getFoodData().eat(1, 1.2F);
            else if (content.getItem() == Items.WHEAT_SEEDS)
                if (new Random().nextInt(3) == 0)
                    player.getFoodData().eat(5, 1.2F);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

    @Override
    public ItemStack getMachineOutput(ItemStack coreStack) {
        ItemStack content = getContent(coreStack);
        if (content.getItem() == Items.POTATO)
            return new ItemStack(Items.POTATO);
        else if (content.getItem() == Items.CARROT)
            return new ItemStack(Items.CARROT);
        else if (content.getItem() == Items.BEETROOT_SEEDS)
            return new ItemStack(Items.BEETROOT);
        else if (content.getItem() == Items.WHEAT_SEEDS)
            return new Random().nextInt(3) == 0? new ItemStack(Items.BREAD) : ItemStack.EMPTY;
        else
            return ItemStack.EMPTY;
    }
}