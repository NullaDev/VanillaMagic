package cn.nulladev.vanillamagic.core;

import cn.nulladev.vanillamagic.item.*;
import cn.nulladev.vanillamagic.item.conceptcore.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

@SuppressWarnings("unused")
public class VMItems {

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Item.class, VanillaMagic.MODID);

    public static RegistryObject<Item> SPACE_CRYSTAL_BASIC = register("space_crystal_basic", (p) -> new SpaceCrystal(p, 3));
    public static RegistryObject<Item> SPACE_CRYSTAL_ADVANCED = register("space_crystal_advanced", (p) -> new SpaceCrystal(p, 4));
    public static RegistryObject<Item> SPACE_CRYSTAL_ULTIMATE = register("space_crystal_ultimate", (p) -> new SpaceCrystal(p.rarity(Rarity.UNCOMMON), 5));

    public static RegistryObject<Item> CORE_AMPLIFIER = register("core_amplifier", GenericVMItem::new);
    public static RegistryObject<Item> CORE_BAG = register("core_bag", CoreBag::new);
    public static RegistryObject<Item> CORE_MODIFIER_DARK_BOX = register("core_modifier_dark_box", GenericVMItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_FALL = register("core_modifier_fall", GenericVMItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_PULSE = register("core_modifier_pulse", GenericVMItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_PULSE_CONTROLLABLE = register("core_modifier_pulse_controllable", GenericVMItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_SNOW_GOLEM = register("core_modifier_snow_golem", GenericVMItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_UPDATE_END = register("core_modifier_update_end", GenericVMItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_VILLAGER = register("core_modifier_villager", GenericVMItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_WITHER = register("core_modifier_wither", GenericVMItem::new);
    public static RegistryObject<Item> INFINITE_FUEL = register("infinite_fuel", InfiniteFuel::new);
    public static RegistryObject<Item> WORLD_INTERACTION_IO = register("world_interaction_io", GenericVMItem::new);

    public static RegistryObject<Item> WORLD_INTERACTION_WAND = register("world_interaction_wand", WorldInteractionWand::new);

    public static RegistryObject<Item> CONCEPT_CORE_ATTACHMENT_BLOCK = register("concept_core_attachment_block", ConceptCoreAttachmentBlock::new);
    public static RegistryObject<Item> CONCEPT_CORE_BONEMEAL = register("concept_core_bone_meal", ConceptCoreBoneMeal::new);
    public static RegistryObject<Item> CONCEPT_CORE_BONEMEAL_ADVANCED = register("concept_core_bone_meal_advanced", ConceptCoreBoneMealAdvanced::new);
    public static RegistryObject<Item> CONCEPT_CORE_CACTUS = register("concept_core_cactus", ConceptCoreCactus::new);
    public static RegistryObject<Item> CONCEPT_CORE_CHICKEN = register("concept_core_chicken", ConceptCoreChicken::new);
    public static RegistryObject<Item> CONCEPT_CORE_CHICKEN_ROAST = register("concept_core_chicken_roast", ConceptCoreChickenRoast::new);
    public static RegistryObject<Item> CONCEPT_CORE_COBBLESTONE = register("concept_core_cobblestone", ConceptCoreCobblestone::new);
    public static RegistryObject<Item> CONCEPT_CORE_COBBLESTONE_ADVANCED = register("concept_core_cobblestone_advanced", ConceptCoreCobblestoneAdvanced::new);
    public static RegistryObject<Item> CONCEPT_CORE_FALLING_BLOCK = register("concept_core_falling_block", ConceptCoreFallingBlock::new);
    public static RegistryObject<Item> CONCEPT_CORE_FARM = register("concept_core_farm", ConceptCoreFarm::new);
    public static RegistryObject<Item> CONCEPT_CORE_FRAME_DESTROY = register("concept_core_frame_destroy", ConceptCoreFrameDestory::new);
    public static RegistryObject<Item> CONCEPT_CORE_GOLD = register("concept_core_gold", ConceptCoreGold::new);
    public static RegistryObject<Item> CONCEPT_CORE_IRON = register("concept_core_iron", ConceptCoreIron::new);
    public static RegistryObject<Item> CONCEPT_CORE_LAVA = register("concept_core_lava", ConceptCoreLava::new);
    public static RegistryObject<Item> CONCEPT_CORE_NETHER_PORTAL = register("concept_core_nether_portal", ConceptCoreNetherPortal::new);
    public static RegistryObject<Item> CONCEPT_CORE_OBSIDIAN = register("concept_core_obsidian", ConceptCoreObsidian::new);
    public static RegistryObject<Item> CONCEPT_CORE_PORKCHOP = register("concept_core_porkchop", ConceptCorePorkchop::new);
    public static RegistryObject<Item> CONCEPT_CORE_SNOWBALL = register("concept_core_snowball", ConceptCoreSnowball::new);
    public static RegistryObject<Item> CONCEPT_CORE_STONE = register("concept_core_stone", ConceptCoreStone::new);
    public static RegistryObject<Item> CONCEPT_CORE_SUGAR_CANE = register("concept_core_sugar_cane", ConceptCoreSugarCane::new);
    public static RegistryObject<Item> CONCEPT_CORE_TNT = register("concept_core_tnt", ConceptCoreTNT::new);
    public static RegistryObject<Item> CONCEPT_CORE_VILLAGER_SPAWN = register("concept_core_villager_spawn", ConceptCoreVillagerSpawn::new);
    public static RegistryObject<Item> CONCEPT_CORE_WATER = register("concept_core_water", ConceptCoreWater::new);

    public static <V extends Item> RegistryObject<V> register(String name, Function<Item.Properties, V> sup) {
        return ITEM.register(name, () -> sup.apply(new Item.Properties().tab(VMCreativeTab.INSTANCE)));
    }

}
