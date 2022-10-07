package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class ConceptCoreBoneMealAdvanced extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCoreBoneMealAdvanced(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 2;
    }


    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return IConceptCoreWand.fertilize(ctx);
    }
}
