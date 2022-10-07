package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreFrameDestroyer extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCoreFrameDestroyer(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 20;
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return IConceptCoreWand.removeBlock(ctx, Blocks.END_PORTAL_FRAME);
    }
}
