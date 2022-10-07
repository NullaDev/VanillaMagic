package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public interface IConceptCoreWithContent extends IConceptCore {
    String TAG_CONTENT = "content";

    static void setContent(ItemStack stack, ItemStack content) {
        CompoundTag tag = new CompoundTag();
        content.save(tag);
        stack.getOrCreateTag().put(TAG_CONTENT, tag);
    }

    static ItemStack getContent(ItemStack stack) {
        if (stack.getOrCreateTag().contains(TAG_CONTENT)) {
            return ItemStack.of(stack.getOrCreateTag().getCompound(TAG_CONTENT));
        } else {
            return ItemStack.EMPTY;
        }
    }

    static boolean hasContent(ItemStack stack) {
        return !getContent(stack).isEmpty();
    }

    static Block getBlock(ItemStack stack) {
        if (getContent(stack).getItem() instanceof BlockItem) {
            return ((BlockItem) (getContent(stack).getItem())).getBlock();
        } else {
            return Blocks.AIR;
        }
    }

    boolean isContentValid(ItemStack stack);

    @Override
    default boolean canUse(ItemStack stack) {
        return hasContent(stack);
    }
}
