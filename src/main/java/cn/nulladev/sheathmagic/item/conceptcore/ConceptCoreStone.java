package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreStone extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCoreStone(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 30;
    }


    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return IConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), Blocks.STONE);
    }

}
