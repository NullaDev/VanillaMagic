package cn.nulladev.vanillamagic.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class WorldInteractionWand extends Item {

    private static final String TAG_CORE_TYPE = "type";
    private static final String TAG_CD = "cd";
    private static final String TAG_MAX_CD = "cdmax";

    public WorldInteractionWand(Properties props) {
        super(props);
    }

    public static void setCore(ItemStack stack, String type) {
        stack.getOrCreateTag().putString(TAG_CORE_TYPE, type);
    }

    public static String getCore(ItemStack stack) {
        return stack.getOrCreateTag().getString(TAG_CORE_TYPE);
    }

    public static boolean hasCore(ItemStack stack) {
        return !getCore(stack).equals("");
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (getCD(stack) > 0) {
            setCD(stack, getCD(stack) - 1);
        }
    }

    private int getCD(ItemStack stack) {
        return stack.getOrCreateTag().getInt(TAG_CD);
    }

    private void setCD(ItemStack stack, int cd) {
        stack.getOrCreateTag().putInt(TAG_CD, cd);
    }

    private int getMaxCD(ItemStack stack) {
        return stack.getOrCreateTag().getInt(TAG_MAX_CD);
    }

    private void setMaxCD(ItemStack stack, int cdmax) {
        stack.getOrCreateTag().putInt(TAG_MAX_CD, cdmax);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getCD(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if (getMaxCD(stack) == 0)
            return 0;
        return Math.round(13F * getCD(stack) / getMaxCD(stack));
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        switch (getCore(ctx.getItemInHand())) {
            case "water":
                return place(new BlockPlaceContext(ctx), Blocks.WATER);
            case "stone":
                return place(new BlockPlaceContext(ctx), Blocks.STONE);
            case "cobblestone":
                return place(new BlockPlaceContext(ctx), Blocks.COBBLESTONE);
            default:
                return InteractionResult.PASS;
        }
    }

    public InteractionResult place(BlockPlaceContext ctx, Block b) {
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

}
