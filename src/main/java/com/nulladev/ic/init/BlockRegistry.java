package com.nulladev.ic.init;

import com.nulladev.ic.ICMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(Block.class, ICMod.MODID);

    public static <V extends Block> RegistryObject<V> register(String name, Supplier<V> sup) {
        RegistryObject<V> ans = BLOCK.register(name, sup);
        ItemRegistry.register(name, (p) -> new BlockItem(sup.get(), p));
        return ans;
    }

}
