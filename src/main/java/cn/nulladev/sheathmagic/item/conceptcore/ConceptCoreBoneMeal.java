package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class ConceptCoreBoneMeal extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCoreBoneMeal(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 12000;
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return IConceptCoreWand.fertilize(ctx);
    }
}
