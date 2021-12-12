package com.lcy0x1.base.block.mult;

import com.lcy0x1.base.block.type.MultipleBlockMethod;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;

public interface PlacementBlockMethod extends MultipleBlockMethod {

    BlockState getStateForPlacement(BlockState def, BlockPlaceContext context);

}