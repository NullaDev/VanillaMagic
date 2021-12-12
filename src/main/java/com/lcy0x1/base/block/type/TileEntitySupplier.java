package com.lcy0x1.base.block.type;

import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.function.Supplier;

public interface TileEntitySupplier extends BlockMethod, Supplier<BlockEntity> {

    @Override
    BlockEntity get();
}