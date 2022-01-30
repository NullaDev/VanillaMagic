package cn.nulladev.vanillamagic.block;

import cn.nulladev.vanillamagic.core.VMRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class AutomaticCollector extends BaseEntityBlock {

    public AutomaticCollector(Properties props) {
        super(props);
    }

    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new CollectorBE(pos, blockState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide()) {
            return createTickerHelper(type, VMRegistry.BE_COLLECTOR.get(), CollectorBE.ticker);
        }
        return null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof CollectorBE) {
                MenuProvider menu = (MenuProvider)level.getBlockEntity(pos);
                NetworkHooks.openGui((ServerPlayer) player, menu, buf -> buf.writeBlockPos(pos));
                //player.openMenu((MenuProvider)level.getBlockEntity(pos));
            }
        }
        return InteractionResult.PASS;
    }
}
