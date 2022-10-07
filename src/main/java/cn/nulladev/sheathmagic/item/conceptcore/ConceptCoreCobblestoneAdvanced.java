package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreCobblestoneAdvanced extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCoreCobblestoneAdvanced(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 10;
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return IConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), Blocks.COBBLESTONE);
    }
}
