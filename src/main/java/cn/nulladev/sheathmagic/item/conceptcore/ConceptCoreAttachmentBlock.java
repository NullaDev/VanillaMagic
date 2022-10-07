package cn.nulladev.sheathmagic.item.conceptcore;

import cn.nulladev.sheathmagic.item.WorldInteroperationWand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WoolCarpetBlock;

public class ConceptCoreAttachmentBlock extends BaseConceptCore implements IConceptCoreWithContent, IConceptCoreWand{
    public ConceptCoreAttachmentBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 2;
    }

    @Override
    public boolean isContentValid(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem) {
            Block b = ((BlockItem) (stack.getItem())).getBlock();
            if (b instanceof WoolCarpetBlock) {
                return true;
            }
            else {
                return (b instanceof BaseRailBlock);
            }
        } else {
            return false;
        }
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = WorldInteroperationWand.getCore(ctx.getItemInHand());
        if (IConceptCoreWithContent.hasContent(core)) {
            return IConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), IConceptCoreWithContent.getBlock(core));
        } else {
            return InteractionResult.PASS;
        }
    }

}
