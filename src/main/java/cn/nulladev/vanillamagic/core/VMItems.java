package cn.nulladev.vanillamagic.core;

import cn.nulladev.vanillamagic.item.conceptcore.*;
import cn.nulladev.vanillamagic.item.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class VMItems {

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Item.class, VanillaMagic.MODID);

    public static RegistryObject<Item> SPACE_CRYSTAL_BASIC = register("space_crystal_basic", (p) -> new SpaceCrystal(p, 3));
    public static RegistryObject<Item> SPACE_CRYSTAL_ADVANCED = register("space_crystal_advanced", (p) -> new SpaceCrystal(p, 4));
    public static RegistryObject<Item> SPACE_CRYSTAL_ULTIMATE = register("space_crystal_ultimate", (p) -> new SpaceCrystal(p, 5));

    public static RegistryObject<Item> CORE_AMPLIFIER = register("core_amplifier", GenericVMItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_FALL = register("core_modifier_fall", GenericVMItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_PULSE = register("core_modifier_pulse", GenericVMItem::new);
    public static RegistryObject<Item> WORLD_INTERACTION_IO = register("world_interaction_io", GenericVMItem::new);

    public static RegistryObject<Item> WORLD_INTERACTION_WAND = register("world_interaction_wand", WorldInteractionWand::new);

    public static RegistryObject<Item> CONCEPT_CORE_BONEMEAL = register("concept_core_bone_meal", ConceptCoreBoneMeal::new);
    public static RegistryObject<Item> CONCEPT_CORE_BONEMEAL_ADVANCED = register("concept_core_bone_meal_advanced", ConceptCoreBoneMealAdvanced::new);
    public static RegistryObject<Item> CONCEPT_CORE_CACTUS = register("concept_core_cactus", ConceptCoreCactus::new);
    public static RegistryObject<Item> CONCEPT_CORE_COBBLESTONE = register("concept_core_cobblestone", ConceptCoreCobblestone::new);
    public static RegistryObject<Item> CONCEPT_CORE_LAVA = register("concept_core_lava", ConceptCoreLava::new);
    public static RegistryObject<Item> CONCEPT_CORE_OBSIDIAN = register("concept_core_obsidian", ConceptCoreObsidian::new);
    public static RegistryObject<Item> CONCEPT_CORE_STONE = register("concept_core_stone", ConceptCoreStone::new);
    public static RegistryObject<Item> CONCEPT_CORE_SUGAR_CANE = register("concept_core_sugar_cane", ConceptCoreSugarCane::new);
    public static RegistryObject<Item> CONCEPT_CORE_TNT = register("concept_core_tnt", ConceptCoreTNT::new);
    public static RegistryObject<Item> CONCEPT_CORE_WATER = register("concept_core_water", ConceptCoreWater::new);

    public static <V extends Item> RegistryObject<V> register(String name, Function<Item.Properties, V> sup) {
        return ITEM.register(name, () -> sup.apply(new Item.Properties().tab(VMCreativeTab.INSTANCE)));
    }

}
