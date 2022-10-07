package cn.nulladev.sheathmagic.item.conceptcore;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;

import java.util.Queue;

public class ConceptCoreDrain extends BaseConceptCore implements IConceptCoreWand {
    public ConceptCoreDrain(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        BlockPlaceContext bpc = new BlockPlaceContext(ctx);
        Level level = bpc.getLevel();
        BlockPos blockpos = bpc.getClickedPos();
        if (removeWaterBreadthFirstSearch(level, blockpos)) {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private static boolean removeWaterBreadthFirstSearch(Level level, BlockPos pos) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(pos, 0));
        int i = 0;

        while (!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos blockpos = tuple.getA();
            int j = tuple.getB();

            for (Direction direction : Direction.values()) {
                BlockPos relativePos = blockpos.relative(direction);
                BlockState relativeBlockState = level.getBlockState(relativePos);
                FluidState relativeFluidState = level.getFluidState(relativePos);
                Material material = relativeBlockState.getMaterial();
                if (relativeFluidState.is(FluidTags.WATER)) {
                    if (relativeBlockState.getBlock() instanceof BucketPickup && !((BucketPickup) relativeBlockState.getBlock()).pickupBlock(level, relativePos, relativeBlockState).isEmpty()) {
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(relativePos, j + 1));
                        }
                    } else if (relativeBlockState.getBlock() instanceof LiquidBlock) {
                        level.setBlock(relativePos, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(relativePos, j + 1));
                        }
                    } else if (material == Material.WATER_PLANT || material == Material.REPLACEABLE_WATER_PLANT) {
                        BlockEntity blockentity = relativeBlockState.hasBlockEntity() ? level.getBlockEntity(relativePos) : null;
                        Block.dropResources(relativeBlockState, level, relativePos, blockentity);
                        level.setBlock(relativePos, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(relativePos, j + 1));
                        }
                    }
                }
            }

            if (i > 64) {
                break;
            }
        }

        return i > 0;
    }
}
