package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreFrameDestory extends ConceptCore {

    public ConceptCoreFrameDestory(Properties props) {
        super(props);
        this.setUsingCD(20);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return GenericInteractions.remove(ctx, Blocks.END_PORTAL_FRAME);
    }
}