package cn.nulladev.vanillamagic.item;

import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

public class WorldInteractionWand extends Item {

    private static final String TAG_CORE = "core";

    public WorldInteractionWand(Properties props) {
        super(props.stacksTo(1));
    }

    public static void setCore(ItemStack stack, ItemStack core) {
        CompoundTag tag = new CompoundTag();
        core.save(tag);
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

    private static int getCD(ItemStack stack) {
        ItemStack core = getCore(stack);
        if (!core.isEmpty())
            return ConceptCore.getCD(core);
        else
            return 0;
    }

    private static void setCD(ItemStack stack, int cd) {
        ItemStack core = getCore(stack);
        if (!core.isEmpty())
            ConceptCore.setCD(core, cd);
    }

    private static int getMaxCD(ItemStack stack) {
        ItemStack core = getCore(stack);
        if (core.getItem() instanceof ConceptCore)
            return ((ConceptCore) core.getItem()).UsingCD;
        else
            return 0;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getCD(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if (getMaxCD(stack) == 0)
            return 0;
        return Math.round(13F - 13F * getCD(stack) / getMaxCD(stack));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (getMaxCD(stack) == 0)
            return 0;
        float f = Math.max(0.0F, (getMaxCD(stack) - getCD(stack)) / (float) getMaxCD(stack));
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
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
        if (getCD(ctx.getItemInHand()) > 0 && ctx.getPlayer() != null && !ctx.getPlayer().isCreative())
            return InteractionResult.PASS;

        if (getCore(ctx.getItemInHand()).getItem() instanceof ConceptCore item) {
            InteractionResult result = item.wandUseOn(ctx);
            if (result != InteractionResult.PASS)
                setCD(ctx.getItemInHand(), item.UsingCD);
            return result;
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (getCD(stack) > 0 && !player.isCreative())
            return InteractionResultHolder.pass(stack);

        if (getCore(stack).getItem() instanceof ConceptCore item) {
            InteractionResultHolder<ItemStack> result = item.wandUse(level, player, hand);
            if (result.getResult() != InteractionResult.PASS) {
                setCD(player.getItemInHand(hand), item.UsingCD);
                return InteractionResultHolder.success(stack);
            }
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flags) {
        Component cd_info;
        if (!hasCore(stack))
            cd_info = new TranslatableComponent("vanillamagic.misc.miss_core").withStyle(ChatFormatting.RED);
        else if (getCD(stack) > 0)
            cd_info = new TranslatableComponent("vanillamagic.misc.cd2", getCD(stack)).withStyle(ChatFormatting.RED);
        else
            cd_info = new TranslatableComponent("vanillamagic.misc.cd1").withStyle(ChatFormatting.GREEN);
        list.add(cd_info);
    }
}
