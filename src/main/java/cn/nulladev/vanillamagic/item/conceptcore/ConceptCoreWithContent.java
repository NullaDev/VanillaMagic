package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ConceptCoreWithContent extends ConceptCore {
    private static final String TAG_CONTENT = "content";

    public ConceptCoreWithContent(Properties props) {
        super(props);
    }

    public static void setContent(ItemStack stack, ItemStack content) {
        CompoundTag tag = new CompoundTag();
        content.save(tag);
        stack.getOrCreateTag().put(TAG_CONTENT, tag);
    }

    public static ItemStack getContent(ItemStack stack) {
        if (stack.getOrCreateTag().contains(TAG_CONTENT)) {
            return ItemStack.of(stack.getOrCreateTag().getCompound(TAG_CONTENT));
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static boolean hasContent(ItemStack stack) {
        return !getContent(stack).isEmpty();
    }

    public abstract boolean isContentValid(ItemStack stack);

    @Override
    public Component getName(ItemStack stack) {
        if (hasContent(stack)) {
            MutableComponent component = super.getName(stack).plainCopy();
            component.append("(");
            component.append(getContent(stack).getDisplayName().plainCopy().withStyle(ChatFormatting.YELLOW));
            component.append(")");
            return component;
        } else {
            return super.getName(stack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        Component total_cd = new TranslatableComponent("vanillamagic.misc.total_cd", UsingCD);
        Component cd_info;
        if (!hasContent(stack))
            cd_info = new TranslatableComponent("vanillamagic.misc.miss_content").withStyle(ChatFormatting.RED);
        else if (getCD(stack) > 0)
            cd_info = new TranslatableComponent("vanillamagic.misc.cd2", getCD(stack)).withStyle(ChatFormatting.RED);
        else
            cd_info = new TranslatableComponent("vanillamagic.misc.cd1").withStyle(ChatFormatting.GREEN);
        list.add(total_cd);
        list.add(cd_info);
    }

    @Override
    public boolean isUsable(ItemStack stack) {
        return !getContent(stack).isEmpty();
    }

    public boolean canProvideItem(ItemStack stack) {
        return !getContent(stack).isEmpty();
    }
}
