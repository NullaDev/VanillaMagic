package com.nulladev.ic.init;

import com.nulladev.ic.ICMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ItemRegistry extends CreativeModeTab {

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Item.class, ICMod.MODID);

    public static final CreativeModeTab TAB = new ItemRegistry();

    public ItemRegistry() {
        super(ICMod.MODID);
    }

    public static <V extends Item> RegistryObject<V> register(String name, Function<Item.Properties, V> sup) {
        return ITEM.register(name, () -> sup.apply(new Item.Properties().tab(TAB)));
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return Items.QUARTZ.getDefaultInstance();
    }
}
