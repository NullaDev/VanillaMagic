package cn.nulladev.vanillamagic.item.conceptcore;

import cn.nulladev.vanillamagic.item.WorldInteractionWand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;

public class ConceptCoreFallingBlock extends ConceptCoreWithContent {

    public ConceptCoreFallingBlock(Properties props) {
        super(props);
        this.setUsingCD(2);
    }

    @Override
    public boolean isContentValid(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem) {
            return ((BlockItem) (stack.getItem())).getBlock() instanceof FallingBlock;
        } else {
            return false;
        }
    }

    public static Block getBlock(ItemStack stack) {
        if (getContent(stack).getItem() instanceof BlockItem) {
            return ((BlockItem) (getContent(stack).getItem())).getBlock();
        } else {
            return Blocks.AIR;
        }
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = WorldInteractionWand.getCore(ctx.getItemInHand());
        if (hasContent(core)) {
            Block b = getBlock(core);
            return GenericInteractions.place(new BlockPlaceContext(ctx), b);
        } else {
            return InteractionResult.PASS;
        }
    }

    @Override
    public ItemStack getMachineOutput(ItemStack coreStack) {
        return getContent(coreStack).copy();
    }
}