package com.lcy0x1.base.block.mult;

import com.lcy0x1.base.block.type.MultipleBlockMethod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public interface CreateBlockStateBlockMethod extends MultipleBlockMethod {

    void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder);

}