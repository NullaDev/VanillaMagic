package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.item.ItemStack;

public interface IConceptCore {

    String TAG_COOLDOWN = "cooldown";

    static int getTagCooldown(ItemStack itemStack) {
        return itemStack.getOrCreateTag().getInt(TAG_COOLDOWN);
    }

    static void setTagCooldown(ItemStack itemStack, int cooldown) {
        itemStack.getOrCreateTag().putInt(TAG_COOLDOWN, cooldown);
    }
    
    default boolean canUse(ItemStack stack) {
        return true;
    }

    int getCooldown();
}
