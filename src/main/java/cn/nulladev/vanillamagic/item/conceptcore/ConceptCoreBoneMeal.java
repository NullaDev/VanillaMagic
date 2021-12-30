package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;

public class ConceptCoreBoneMeal extends ConceptCore {

    public ConceptCoreBoneMeal(Properties props) {
        super(props);
        this.setUsingCD(24000);
    }

    @Override
    public InteractionResult wandUse(UseOnContext ctx) {
        return Items.BONE_MEAL.useOn(ctx);
    }
}