package com.lcy0x1.base.block;

import com.lcy0x1.base.block.type.BlockMethod;
import com.lcy0x1.base.block.type.TileEntitySupplier;
import net.minecraft.world.level.block.Block;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class DelegateBlock extends Block {

    public static DelegateBlock newBaseBlock(DelegateBlockProperties p, BlockMethod... impl) {
        for (BlockMethod m : impl) {
            if (m instanceof TileEntitySupplier) {
                return new DelegateEntityBlockImpl(p, impl);
            }
        }
        return new DelegateBlockImpl(p, impl);
    }

    protected DelegateBlock(Properties props) {
        super(props);
    }

}