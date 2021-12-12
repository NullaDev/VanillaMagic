package com.lcy0x1.base.block.mult;

import com.lcy0x1.base.block.type.MultipleBlockMethod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface NeighborUpdateBlockMethod extends MultipleBlockMethod {

    void neighborChanged(Block self, BlockState state, Level world, BlockPos pos, Block nei_block, BlockPos nei_pos, boolean moving);

}
