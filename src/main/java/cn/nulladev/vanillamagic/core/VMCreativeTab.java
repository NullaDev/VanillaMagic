package cn.nulladev.vanillamagic.core;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class VMCreativeTab extends ItemGroup {

    public static final VMCreativeTab INSTANCE = new VMCreativeTab();

    public VMCreativeTab() {
        super(VanillaMagic.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(VMItems.WORLD_INTERACTION_WAND.get());
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
