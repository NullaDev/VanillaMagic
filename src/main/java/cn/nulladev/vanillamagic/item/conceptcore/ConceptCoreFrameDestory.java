package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreFrameDestory extends ConceptCore {

    public ConceptCoreFrameDestory(Properties props) {
        super(props);
        this.setUsingCD(20);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos blockpos = ctx.getClickedPos();
        if (level.getBlockState(blockpos).getBlock() == Blocks.END_PORTAL_FRAME) {
            level.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}