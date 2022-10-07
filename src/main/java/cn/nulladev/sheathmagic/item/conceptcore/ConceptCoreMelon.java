package cn.nulladev.sheathmagic.item.conceptcore;

import cn.nulladev.sheathmagic.item.WorldInteroperationWand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.Random;

public class ConceptCoreMelon extends BaseConceptCore implements IConceptCoreWand, IConceptCoreWithContent{
    public ConceptCoreMelon(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 12000;
    }

    @Override
    public boolean isContentValid(ItemStack stack) {
        if (stack.getItem() == Items.MELON_SEEDS) {
            return true;
        }
        else {
            return stack.getItem() == Items.PUMPKIN_SEEDS;
        }
    }


    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        ItemStack core = WorldInteroperationWand.getCore(player.getItemInHand(hand));
        ItemStack content = IConceptCoreWithContent.getContent(core);
        if (content.getItem() != Items.MELON_SEEDS || !player.canEat(false)) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
        int i = 3 + new Random().nextInt(5);
        while (i-- > 0) {
            if (player.canEat(false)) {
                player.getFoodData().eat(2, 0.6F);
            } else {
                break;
            }
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = WorldInteroperationWand.getCore(ctx.getItemInHand());
        ItemStack content = IConceptCoreWithContent.getContent(core);
        if (content.getItem() == Items.PUMPKIN_SEEDS) {
            return IConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), Blocks.PUMPKIN);
        } else {
            return InteractionResult.PASS;
        }
    }
}
