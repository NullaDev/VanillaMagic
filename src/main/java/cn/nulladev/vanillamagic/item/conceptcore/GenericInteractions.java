package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class GenericInteractions {

    public static InteractionResult place(BlockPlaceContext ctx, Block b) {
        if (!ctx.canPlace()) {
            return InteractionResult.FAIL;
        } else {
            BlockState blockstate = b.getStateForPlacement(ctx);
            BlockPos blockpos = ctx.getClickedPos();
            Level level = ctx.getLevel();
            Player player = ctx.getPlayer();
            ItemStack itemstack = ctx.getItemInHand();
            if (blockstate == null) {
                return InteractionResult.FAIL;
            } else if (!blockstate.canSurvive(level, blockpos)) {
                return InteractionResult.FAIL;
            } else if (!level.setBlock(blockpos, blockstate, 11)) {
                return InteractionResult.FAIL;
            } else {
                BlockState blockstate1 = level.getBlockState(blockpos);
                if (blockstate1.is(b)) {
                    blockstate1.getBlock().setPlacedBy(level, blockpos, blockstate1, player, itemstack);
                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
                    }
                }

                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
                SoundType soundtype = blockstate1.getSoundType(level, blockpos, ctx.getPlayer());
                level.playSound(player, blockpos, blockstate1.getSoundType(level, blockpos, ctx.getPlayer()).getPlaceSound(), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
    }

    public static InteractionResult useBoneMeal(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos blockpos = ctx.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (blockstate.getBlock() instanceof BonemealableBlock bonemealableblock) {
            if (bonemealableblock.isValidBonemealTarget(level, blockpos, blockstate, level.isClientSide)) {
                if (level instanceof ServerLevel) {
                    if (bonemealableblock.isBonemealSuccess(level, level.random, blockpos, blockstate)) {
                        bonemealableblock.performBonemeal((ServerLevel) level, level.random, blockpos, blockstate);
                    }
                    level.levelEvent(1505, blockpos, 0);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

}
