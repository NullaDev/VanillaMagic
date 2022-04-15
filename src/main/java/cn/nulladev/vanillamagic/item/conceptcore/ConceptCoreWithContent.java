package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public abstract class ConceptCoreWithContent extends ConceptCore {
    private static final String TAG_CONTENT = "content";

    public ConceptCoreWithContent(Properties props) {
        super(props);
    }

    public static void setContent(ItemStack stack, ItemStack content) {
        CompoundNBT tag = new CompoundNBT();
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
    public ITextComponent getName(ItemStack stack) {
        if (hasContent(stack)) {
            IFormattableTextComponent component = super.getName(stack).plainCopy();
            component.append("(");
            component.append(getContent(stack).getDisplayName().plainCopy().withStyle(TextFormatting.YELLOW));
            component.append(")");
            return component;
        } else {
            return super.getName(stack);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flags) {
        IFormattableTextComponent total_cd = new TranslationTextComponent("vanillamagic.misc.total_cd", UsingCD);
        IFormattableTextComponent cd_info;
        if (!hasContent(stack))
            cd_info = new TranslationTextComponent("vanillamagic.misc.miss_content").withStyle(TextFormatting.RED);
        else if (getCD(stack) > 0)
            cd_info = new TranslationTextComponent("vanillamagic.misc.cd2", getCD(stack)).withStyle(TextFormatting.RED);
        else
            cd_info = new TranslationTextComponent("vanillamagic.misc.cd1").withStyle(TextFormatting.GREEN);
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
