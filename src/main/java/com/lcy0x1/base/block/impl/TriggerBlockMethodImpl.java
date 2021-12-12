package com.lcy0x1.base.block.impl;

import com.lcy0x1.base.block.mult.CreateBlockStateBlockMethod;
import com.lcy0x1.base.block.mult.DefaultStateBlockMethod;
import com.lcy0x1.base.block.mult.NeighborUpdateBlockMethod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class TriggerBlockMethodImpl implements NeighborUpdateBlockMethod, CreateBlockStateBlockMethod, DefaultStateBlockMethod {

    private final int delay;

    public TriggerBlockMethodImpl(int delay) {
        this.delay = delay;
    }

    @Override
    public void neighborChanged(Block self, BlockState state, Level world, BlockPos pos, Block nei_block, BlockPos nei_pos, boolean moving) {
        boolean flag = world.hasNeighborSignal(pos) || world.hasNeighborSignal(pos.above());
        boolean flag1 = state.getValue(BlockStateProperties.TRIGGERED);
        if (flag && !flag1) {
            world.scheduleTick(pos, self, delay);
            world.setBlock(pos, state.setValue(BlockStateProperties.TRIGGERED, Boolean.TRUE), delay);
        } else if (!flag && flag1) {
            world.setBlock(pos, state.setValue(BlockStateProperties.TRIGGERED, Boolean.FALSE), delay);
        }
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.TRIGGERED);
    }

    @Override
    public BlockState getDefaultState(BlockState state) {
        return state.setValue(BlockStateProperties.TRIGGERED, false);
    }
}
