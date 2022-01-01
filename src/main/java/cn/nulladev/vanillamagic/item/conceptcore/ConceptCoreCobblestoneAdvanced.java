package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreCobblestoneAdvanced extends ConceptCore {

    public ConceptCoreCobblestoneAdvanced(Properties props) {
        super(props);
        this.setUsingCD(1);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return GenericInteractions.place(new BlockPlaceContext(ctx), Blocks.COBBLESTONE);
    }

    @Override
    public ItemStack getMachineOutput() {
        return new ItemStack(Blocks.COBBLESTONE);
    }
}