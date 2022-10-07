package cn.nulladev.sheathmagic.item.conceptcore;

import cn.nulladev.sheathmagic.item.WorldInteroperationWand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class ConceptCoreFallingBlock extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCoreFallingBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 2;
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = WorldInteroperationWand.getCore(ctx.getItemInHand());
        if (IConceptCoreWithContent.hasContent(core)) {
            Block b = IConceptCoreWithContent.getBlock(core);
            return IConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), b);
        } else {
            return InteractionResult.PASS;
        }
    }
}
