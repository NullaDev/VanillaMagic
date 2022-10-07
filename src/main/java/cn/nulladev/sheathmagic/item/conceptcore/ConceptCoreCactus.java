package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreCactus extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCoreCactus(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 12000;
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return IConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), Blocks.CACTUS);
    }
}
