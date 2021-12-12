package com.lcy0x1.base.block;

import com.lcy0x1.base.block.one.TitleEntityBlockMethod;
import com.lcy0x1.base.block.type.BlockMethod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DelegateEntityBlockImpl extends DelegateBlockImpl implements EntityBlock {

    protected DelegateEntityBlockImpl(DelegateBlockProperties p, BlockMethod... impl) {
        super(p, impl);
    }


    @Override
    public final BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return impl.one(TitleEntityBlockMethod.class).map(e -> e.createTileEntity(pos, state)).orElse(null);
    }

}
