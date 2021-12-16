package cn.nulladev.vanillamagic;

import cn.nulladev.vanillamagic.item.conceptcore.*;
import cn.nulladev.vanillamagic.item.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class VMItems {

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Item.class, VanillaMagic.MODID);

    public static RegistryObject<Item> SPACE_CRYSTAL_SMALL = register("space_crystal_small", (p) -> new SpaceCrystal(p, 3));
    public static RegistryObject<Item> SPACE_CRYSTAL_MIDDLE = register("space_crystal_middle", (p) -> new SpaceCrystal(p, 4));
    public static RegistryObject<Item> SPACE_CRYSTAL_LARGE = register("space_crystal_large", (p) -> new SpaceCrystal(p, 5));

    public static RegistryObject<Item> WORLD_INTERACTION_IO = register("world_interaction_io", GenericVMItem::new);
    public static RegistryObject<Item> CORE_AMPLIFIER = register("core_amplifier", GenericVMItem::new);

    public static RegistryObject<Item> WORLD_INTERACTION_WAND = register("world_interaction_wand", WorldInteractionWand::new);

    public static RegistryObject<Item> CONCEPT_CORE_WATER = register("concept_core_water", ConceptCoreWater::new);
    public static RegistryObject<Item> CONCEPT_CORE_COBBLESTONE = register("concept_core_cobblestone", ConceptCoreCobblestone::new);
    public static RegistryObject<Item> CONCEPT_CORE_STONE = register("concept_core_stone", ConceptCoreStone::new);

    public static <V extends Item> RegistryObject<V> register(String name, Function<Item.Properties, V> sup) {
        return ITEM.register(name, () -> sup.apply(new Item.Properties().tab(VMCreativeTab.INSTANCE)));
    }

}
