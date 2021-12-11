package cn.nulladev.vanillamagic;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class VMCreativeTab extends CreativeModeTab {

    public static final VMCreativeTab INSTANCE = new VMCreativeTab();

    public VMCreativeTab() {
        super("vanillamagic");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.APPLE);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
