package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseConceptCore extends Item implements IConceptCore {
    public BaseConceptCore(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (IConceptCore.getTagCooldown(stack) > 0) {
            IConceptCore.setTagCooldown(stack, IConceptCore.getTagCooldown(stack) - 1);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return IConceptCore.getTagCooldown(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13F - 13F * IConceptCore.getTagCooldown(stack) / getCooldown());
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float f = Math.max(0.0F, (getCooldown() - IConceptCore.getTagCooldown(stack)) / (float) getCooldown());
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("sheathmagic.misc.total_cd", getCooldown()));
        if (IConceptCore.getTagCooldown(stack) > 0) {
            list.add((Component.translatable("sheathmagic.misc.in_cooldown",
                    IConceptCore.getTagCooldown(stack)).withStyle(ChatFormatting.RED)));
        } else {
            list.add(Component.translatable("sheathmagic.misc.available").withStyle(ChatFormatting.GREEN));
        }

    }

}
