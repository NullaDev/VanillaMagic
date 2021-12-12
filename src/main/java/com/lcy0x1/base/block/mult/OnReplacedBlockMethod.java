package com.lcy0x1.base.block.mult;

import com.lcy0x1.base.block.type.MultipleBlockMethod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface OnReplacedBlockMethod extends MultipleBlockMethod {
    void onReplaced(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving);
}