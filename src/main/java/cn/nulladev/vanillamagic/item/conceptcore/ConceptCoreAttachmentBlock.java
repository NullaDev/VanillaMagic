package cn.nulladev.vanillamagic.item.conceptcore;

import cn.nulladev.vanillamagic.item.WorldInteractionWand;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.RailBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class ConceptCoreAttachmentBlock extends ConceptCoreWithContent {

    public ConceptCoreAttachmentBlock(Properties props) {
        super(props);
        this.setUsingCD(2);
    }

    @Override
    public boolean isContentValid(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem) {
            Block b = ((BlockItem) (stack.getItem())).getBlock();
            if (b instanceof CarpetBlock)
                return true;
            else return b instanceof RailBlock;
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
    public ActionResultType wandUseOn(ItemUseContext ctx) {
        ItemStack core = WorldInteractionWand.getCore(ctx.getItemInHand());
        if (hasContent(core)) {
            Block b = getBlock(core);
            return GenericInteractions.place(new BlockItemUseContext(ctx), b);
        } else {
            return ActionResultType.PASS;
        }
    }

    @Override
    public ItemStack getMachineOutput(ItemStack coreStack) {
        return getContent(coreStack).copy();
    }
}
