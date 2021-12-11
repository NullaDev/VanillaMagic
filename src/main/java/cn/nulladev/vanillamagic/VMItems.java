package cn.nulladev.vanillamagic;

import cn.nulladev.vanillamagic.item.ConceptCore;
import cn.nulladev.vanillamagic.item.WorldInteractionWand;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

@SuppressWarnings("unused")
public class VMItems {

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Item.class, VanillaMagic.MODID);

    public static RegistryObject<Item> WORLD_INTERACTION_WAND = register("world_interaction_wand", WorldInteractionWand::new);

    public static RegistryObject<Item> CONCEPT_CORE_WATER = register("water", ConceptCore::new);
    public static RegistryObject<Item> CONCEPT_CORE_COBBLESTONE = register("concept_core_cobblestone", ConceptCore::new);
    public static RegistryObject<Item> CONCEPT_CORE_STONE = register("concept_core_stone", ConceptCore::new);


    public static <V extends Item> RegistryObject<V> register(String name, Function<Item.Properties, V> sup) {
        return ITEM.register(name, () -> sup.apply(new Item.Properties().tab(VMCreativeTab.INSTANCE)));
    }

}
