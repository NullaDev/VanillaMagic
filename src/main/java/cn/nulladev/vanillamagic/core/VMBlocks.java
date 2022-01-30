package cn.nulladev.vanillamagic.core;

import cn.nulladev.vanillamagic.block.AutomaticCollector;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class VMBlocks {

    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(Block.class, VanillaMagic.MODID);

    public static final BlockBehaviour.Properties p = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.GOLD).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.METAL);
    public static final RegistryObject<Block> AUTOMATIC_COLLECTOR = register("automatic_collector", () -> new AutomaticCollector(p));

    public static <V extends Block> RegistryObject<V> register(String name, Supplier<V> sup) {
        RegistryObject<V> ans = BLOCK.register(name, sup);
        VMItems.register(name, (p) -> new BlockItem(ans.get(), p));
        return ans;
    }

}
