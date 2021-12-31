package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreCactus extends ConceptCore {

    public ConceptCoreCactus(Properties props) {
        super(props);
        this.setUsingCD(12000);
    }

    @Override
    public InteractionResult wandUse(UseOnContext ctx) {
        return GenericInteractions.place(new BlockPlaceContext(ctx), Blocks.CACTUS);
    }
}