package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;

public class ConceptCoreBoneMealAdvanced extends ConceptCore {

    public ConceptCoreBoneMealAdvanced(Properties props) {
        super(props);
        this.setUsingCD(1);
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