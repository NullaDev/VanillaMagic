package cn.nulladev.vanillamagic.item.conceptcore;

import cn.nulladev.vanillamagic.item.WorldInteractionWand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;

public class ConceptCoreAttachmentBlock extends ConceptCoreWithContent {

    public ConceptCoreAttachmentBlock(Properties props) {
        super(props);
        this.setUsingCD(2);
    }

    @Override
    public boolean isContentValid(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem) {
            Block b = ((BlockItem)(stack.getItem())).getBlock();
            if (b instanceof WoolCarpetBlock)
                return true;
            else if (b instanceof BaseRailBlock)
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    public static Block getBlock(ItemStack stack) {
        if (getContent(stack).getItem() instanceof BlockItem) {
            return ((BlockItem)(getContent(stack).getItem())).getBlock();
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
