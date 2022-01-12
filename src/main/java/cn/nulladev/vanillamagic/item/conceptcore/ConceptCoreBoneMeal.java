package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;

public class ConceptCoreBoneMeal extends ConceptCore {

    public ConceptCoreBoneMeal(Properties props) {
        super(props);
        this.setUsingCD(12000);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return Items.BONE_MEAL.useOn(ctx);
    }

    @Override
    public ItemStack getMachineOutput(ItemStack coreStack) {
        return new ItemStack(Items.BONE_MEAL);
    }
}