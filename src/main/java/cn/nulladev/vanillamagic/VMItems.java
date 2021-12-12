package cn.nulladev.vanillamagic;

import cn.nulladev.vanillamagic.item.ConceptCore;
import cn.nulladev.vanillamagic.item.WorldInteractionWand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Function;

public class VMItems {

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Item.class, VanillaMagic.MODID);

    public static final HashMap<String, Integer> CORE_CD = new HashMap<>();

    public static RegistryObject<Item> WORLD_INTERACTION_WAND = register("world_interaction_wand", p -> new WorldInteractionWand(p.stacksTo(1)));

    public static RegistryObject<Item> CONCEPT_CORE_WATER = register("concept_core_water", ConceptCore::new);
    public static RegistryObject<Item> CONCEPT_CORE_COBBLESTONE = register("concept_core_cobblestone", ConceptCore::new);
    public static RegistryObject<Item> CONCEPT_CORE_STONE = register("concept_core_stone", ConceptCore::new);

    public static <V extends Item> RegistryObject<V> register(String name, Function<Item.Properties, V> sup) {
        return ITEM.register(name, () -> sup.apply(new Item.Properties().tab(VMCreativeTab.INSTANCE)));
    }

    public static void registerCoreCD(String core, int cd) {
        CORE_CD.put(core, cd);
    }

    public static int getCoreCD(String core) {
        if (CORE_CD.containsKey(core))
            return CORE_CD.get(core);
        return 20;
    }

    public static String getCoreType(ItemStack stack) {
        if (stack.getItem() instanceof ConceptCore) {
            String name = stack.getItem().getRegistryName().getPath();
            return name.substring(13);
        }
        return "";
    }

    public static Item getCoreFromName(String name) {
        switch (name) {
            case "water":
                return CONCEPT_CORE_WATER.get();
            case "stone":
                return CONCEPT_CORE_STONE.get();
            case "cobblestone":
                return CONCEPT_CORE_COBBLESTONE.get();
            default:
                return Items.AIR;
        }
    }

}
