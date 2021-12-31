package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreCarpet extends ConceptCore {

    public ConceptCoreCarpet(Properties props) {
        super(props);
        this.setUsingCD(2);
    }

    @Override
    public InteractionResult wandUse(UseOnContext ctx) {
        return GenericInteractions.place(new BlockPlaceContext(ctx), Blocks.WHITE_CARPET);
    }
}