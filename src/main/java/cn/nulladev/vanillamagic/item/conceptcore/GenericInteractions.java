package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GenericInteractions {

    public static ActionResultType place(BlockItemUseContext ctx, Block b) {
        if (!ctx.canPlace()) {
            return ActionResultType.FAIL;
        } else {
            BlockState blockstate = b.getStateForPlacement(ctx);
            BlockPos blockpos = ctx.getClickedPos();
            World world = ctx.getLevel();
            PlayerEntity player = ctx.getPlayer();
            ItemStack itemstack = ctx.getItemInHand();
            if (blockstate == null) {
                return ActionResultType.FAIL;
            } else if (!blockstate.canSurvive(world, blockpos)) {
                return ActionResultType.FAIL;
            } else if (!world.setBlock(blockpos, blockstate, 11)) {
                return ActionResultType.FAIL;
            } else {
                BlockState blockstate1 = world.getBlockState(blockpos);
                if (blockstate1.is(b)) {
                    blockstate1.getBlock().setPlacedBy(world, blockpos, blockstate1, player, itemstack);
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        CriteriaTriggers.PLACED_BLOCK.trigger(serverPlayer, blockpos, itemstack);
                    }
                }

                //world.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
                SoundType soundtype = blockstate1.getSoundType(world, blockpos, ctx.getPlayer());
                world.playSound(player, blockpos, blockstate1.getSoundType(world, blockpos, ctx.getPlayer()).getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                return ActionResultType.sidedSuccess(world.isClientSide);
            }
        }
    }

    public static ActionResultType remove(ItemUseContext ctx, Block b, Block b1) {
        World world = ctx.getLevel();
        BlockPos blockpos = ctx.getClickedPos();
        if (world.getBlockState(blockpos).getBlock() == b) {
            world.setBlockAndUpdate(blockpos, b1.defaultBlockState());
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    public static ActionResultType remove(ItemUseContext ctx, Block b) {
        return remove(ctx, b, Blocks.AIR);
    }

    public static ActionResultType useBoneMeal(ItemUseContext ctx) {
        World world = ctx.getLevel();
        BlockPos blockpos = ctx.getClickedPos();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getBlock() instanceof IGrowable growable) {
            if (growable.isValidBonemealTarget(world, blockpos, blockstate, world.isClientSide)) {
                if (world instanceof ServerWorld serverWorld) {
                    if (growable.isBonemealSuccess(world, world.random, blockpos, blockstate)) {
                        growable.performBonemeal(serverWorld, world.random, blockpos, blockstate);
                    }
                    world.levelEvent(1505, blockpos, 0);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

}
