package cn.nulladev.vanillamagic.item;

import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class WorldInteractionWand extends Item {

    private static final String TAG_CORE = "core";
    private static final String TAG_CD = "cd";
    private static final String TAG_MAX_CD = "cdmax";

    public WorldInteractionWand(Properties props) {
        super(props.stacksTo(1));
    }

    public static void setCore(ItemStack stack, ItemStack core) {
        CompoundTag tag = new CompoundTag();
        if (core != null) {
            tag = core.save(tag);
        }
        stack.getOrCreateTag().put(TAG_CORE, tag);
    }

    public static ItemStack getCore(ItemStack stack) {
        if (stack.getOrCreateTag().contains(TAG_CORE)) {
            return ItemStack.of(stack.getOrCreateTag().getCompound(TAG_CORE));
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static boolean hasCore(ItemStack stack) {
        return !getCore(stack).isEmpty();
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
    public Component getName(ItemStack stack) {
        if (hasCore(stack)) {
            MutableComponent component = super.getName(stack).plainCopy();
            component.append("(");
            component.append(getCore(stack).getDisplayName().plainCopy().withStyle(ChatFormatting.GREEN));
            component.append(")");
            return component;
        } else {
            return super.getName(stack);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        if (getCD(ctx.getItemInHand()) > 0 && !ctx.getPlayer().isCreative())
            return InteractionResult.PASS;
        if (getCore(ctx.getItemInHand()).getItem() instanceof ConceptCore) {
            ConceptCore item = (ConceptCore)getCore(ctx.getItemInHand()).getItem();
            setCD(ctx.getItemInHand(), item.CD);
            setMaxCD(ctx.getItemInHand(), item.CD);
            return item.wandUse(ctx);
        }
        return InteractionResult.PASS;
    }



}
