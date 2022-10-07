package cn.nulladev.sheathmagic.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SheathMagicCreativeTab extends CreativeModeTab {

    public static final SheathMagicCreativeTab INSTANCE = new SheathMagicCreativeTab();

    public SheathMagicCreativeTab() {
        super(SheathMagic.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return Items.APPLE.getDefaultInstance();
    }
}
