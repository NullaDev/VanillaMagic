package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;

public class ConceptCoreBoneMealAdvanced extends ConceptCore {

    public ConceptCoreBoneMealAdvanced(Properties props) {
        super(props);
        this.setUsingCD(2);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return GenericInteractions.useBoneMeal(ctx);
    }

    @Override
    public ItemStack getMachineOutput(ItemStack coreStack) {
        return new ItemStack(Items.BONE_MEAL);
    }
}