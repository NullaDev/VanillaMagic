package cn.nulladev.sheathmagic.item;

import cn.nulladev.sheathmagic.item.conceptcore.IConceptCore;
import cn.nulladev.sheathmagic.item.conceptcore.IConceptCoreWand;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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

import javax.annotation.Nullable;
import java.util.List;

public class WorldInteroperationWand extends Item {

    private static final String TAG_WAND_CORE = "wand_core";

    public WorldInteroperationWand(Properties properties) {
        super(properties.stacksTo(1));
    }

    public static void setCore(ItemStack wand, ItemStack core) {
        CompoundTag tag = new CompoundTag();
        core.save(tag);
        wand.getOrCreateTag().put(TAG_WAND_CORE, tag);
    }

    public static ItemStack getCore(ItemStack wand) {
        if (wand.getOrCreateTag().contains(TAG_WAND_CORE)) {
            return ItemStack.of(wand.getOrCreateTag().getCompound(TAG_WAND_CORE));
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static boolean hasCore(ItemStack wand) {
        return !getCore(wand).isEmpty();
    }


    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slot, boolean selected) {
        if (getCoreTagCooldown(itemStack) > 0) {
            setCoreTagCooldown(itemStack, getCoreTagCooldown(itemStack) - 1);
        }
    }

    private static int getCoreTagCooldown(ItemStack wand) {
        ItemStack core = getCore(wand);
        if (!core.isEmpty())
            return IConceptCore.getTagCooldown(core);
        else
            return 0;
    }

    private static void setCoreTagCooldown(ItemStack wand, int cd) {
        ItemStack core = getCore(wand);
        if (!core.isEmpty())
            IConceptCore.setTagCooldown(core, cd);
    }

    private static int getCoreCooldown(ItemStack wand) {
        ItemStack core = getCore(wand);
        if (core.getItem() instanceof IConceptCore)
            return ((IConceptCore) core.getItem()).getCooldown();
        else
            return 0;
    }


    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getCoreTagCooldown(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if (getCoreCooldown(stack) == 0)
            return 0;
        return Math.round(13F - 13F * getCoreTagCooldown(stack) / getCoreCooldown(stack));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (getCoreCooldown(stack) == 0)
            return 0;
        float f = Math.max(0.0F, (getCoreCooldown(stack) - getCoreTagCooldown(stack)) / (float) getCoreCooldown(stack));
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
        if (getCoreTagCooldown(ctx.getItemInHand()) > 0 && ctx.getPlayer() != null && !ctx.getPlayer().isCreative()) {
            return InteractionResult.PASS;
        }

        if (getCore(ctx.getItemInHand()).getItem() instanceof IConceptCoreWand wandCore) {
            InteractionResult result = wandCore.wandUseOn(ctx);

            if (result != InteractionResult.PASS)
                setCoreTagCooldown(ctx.getItemInHand(), wandCore.getCooldown());
            return result;
        }

        return InteractionResult.PASS;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var itemStackInHand = player.getItemInHand(hand);
        if (getCoreTagCooldown(itemStackInHand) > 0 && !player.isCreative())
            return InteractionResultHolder.pass(itemStackInHand);

        if (getCore(itemStackInHand).getItem() instanceof IConceptCoreWand wandCore) {
            InteractionResultHolder<ItemStack> result = wandCore.wandUse(level, player, hand);
            if (result.getResult() != InteractionResult.PASS) {
                setCoreTagCooldown(player.getItemInHand(hand), wandCore.getCooldown());
                return InteractionResultHolder.success(itemStackInHand);
            }
        }
        return InteractionResultHolder.pass(itemStackInHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        if (!hasCore(stack)) {
            list.add(Component.translatable("sheathmagic.misc.missing_core").withStyle(ChatFormatting.RED));
        } else if (getCoreTagCooldown(stack) > 0) {
            list.add(Component.translatable("sheathmagic.misc.in_cooldown", getCoreTagCooldown(stack)).withStyle(ChatFormatting.RED));
        } else {
            list.add(Component.translatable("sheathmagic.misc.available").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

}
