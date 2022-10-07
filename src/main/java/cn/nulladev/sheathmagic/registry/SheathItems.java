package cn.nulladev.sheathmagic.registry;

import cn.nulladev.sheathmagic.core.SheathMagic;
import cn.nulladev.sheathmagic.core.SheathMagicCreativeTab;
import cn.nulladev.sheathmagic.item.MagicMaterialItem;
import cn.nulladev.sheathmagic.item.SpaceCrystal;
import cn.nulladev.sheathmagic.item.WorldInteroperationWand;
import cn.nulladev.sheathmagic.item.conceptcore.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class SheathItems {

    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, SheathMagic.MODID);

    // Item registry

    public static RegistryObject<Item> CORE_AMPLIFIER = registerItem("core_amplifier", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_DARK_BOX = registerItem("core_modifier_dark_box", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_ENDERMITE = registerItem("core_modifier_endermite", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_FALL = registerItem("core_modifier_fall", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_PISTON_WARM = registerItem("core_modifier_piston_worm", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_PULSE = registerItem("core_modifier_pulse", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_PULSE_CONTROLLABLE = registerItem("core_modifier_pulse_controllable", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_SHULKER = registerItem("core_modifier_shulker", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_SNOW_GOLEM = registerItem("core_modifier_snow_golem", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_UPDATE_END = registerItem("core_modifier_update_end", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_VILLAGER = registerItem("core_modifier_villager", MagicMaterialItem::new);
    public static RegistryObject<Item> CORE_MODIFIER_WITHER = registerItem("core_modifier_wither", MagicMaterialItem::new);
    public static RegistryObject<Item> WORLD_INTERACTION_IO = registerItem("world_interaction_io", MagicMaterialItem::new);

    public static RegistryObject<Item> SPACE_CRYSTAL_BASIC = registerItem("space_crystal_basic", (p) -> new SpaceCrystal(p, 3));
    public static RegistryObject<Item> SPACE_CRYSTAL_ADVANCED = registerItem("space_crystal_advanced", (p) -> new SpaceCrystal(p, 4));
    public static RegistryObject<Item> SPACE_CRYSTAL_ULTIMATE = registerItem("space_crystal_ultimate", (p) -> new SpaceCrystal(p.rarity(Rarity.UNCOMMON), 5));

    public static RegistryObject<Item> WORLD_INTEROPERATION_WAND = registerItem("world_interoperator_wand", WorldInteroperationWand::new);
    public static RegistryObject<Item> CONCEPT_CORE_ATTACHMENT_BLOCK = registerItem("concept_core_attachment_block", ConceptCoreAttachmentBlock::new);
    public static RegistryObject<Item> CONCEPT_CORE_BONE_MEAL = registerItem("concept_core_bone_meal", ConceptCoreBoneMeal::new);
    public static RegistryObject<Item> CONCEPT_CORE_BONE_MEAL_ADVANCED = registerItem("concept_core_bone_meal_advanced", ConceptCoreBoneMealAdvanced::new);
    public static RegistryObject<Item> CONCEPT_CORE_CACTUS = registerItem("concept_core_cactus", ConceptCoreCactus::new);
    public static RegistryObject<Item> CONCEPT_CORE_CHICKEN = registerItem("concept_core_chicken", ConceptCoreChicken::new);
    public static RegistryObject<Item> CONCEPT_CORE_CHICKEN_ROAST = registerItem("concept_core_chicken_roast", ConceptCoreChickenRoast::new);
    public static RegistryObject<Item> CONCEPT_CORE_COBBLESTONE = registerItem("concept_core_cobblestone", ConceptCoreCobblestone::new);
    public static RegistryObject<Item> CONCEPT_CORE_COBBLESTONE_ADVANCED = registerItem("concept_core_cobblestone_advanced", ConceptCoreCobblestoneAdvanced::new);
    public static RegistryObject<Item> CONCEPT_CORE_DRAIN = registerItem("concept_core_drain", ConceptCoreDrain::new);
    public static RegistryObject<Item> CONCEPT_CORE_ENDER_PEARL = registerItem("concept_core_ender_pearl", ConceptCoreEnderPearl::new);
    public static RegistryObject<Item> CONCEPT_CORE_FALLING_BLOCK = registerItem("concept_core_falling_block", ConceptCoreFallingBlock::new);
    public static RegistryObject<Item> CONCEPT_CORE_FARM = registerItem("concept_core_farm", ConceptCoreFarm::new);
    public static RegistryObject<Item> CONCEPT_CORE_FRAME_DESTROY = registerItem("concept_core_frame_destroyer", ConceptCoreFrameDestroyer::new);
    public static RegistryObject<Item> CONCEPT_CORE_GHAST = registerItem("concept_core_ghast", ConceptCoreGhast::new);
    public static RegistryObject<Item> CONCEPT_CORE_GLOW_BERRIES = registerItem("concept_core_glow_berries", ConceptCoreGlowBerries::new);
    public static RegistryObject<Item> CONCEPT_CORE_GOLD = registerItem("concept_core_gold", ConceptCoreGold::new);
    public static RegistryObject<Item> CONCEPT_CORE_IRON = registerItem("concept_core_iron", ConceptCoreIron::new);
    public static RegistryObject<Item> CONCEPT_CORE_LAVA = registerItem("concept_core_lava", ConceptCoreLava::new);
    public static RegistryObject<Item> CONCEPT_CORE_MELON = registerItem("concept_core_melon", ConceptCoreMelon::new);
    public static RegistryObject<Item> CONCEPT_CORE_MOB_GRINDER = registerItem("concept_core_mob_grinder", ConceptCoreMobGrinder::new);
    public static RegistryObject<Item> CONCEPT_CORE_OBSIDIAN = registerItem("concept_core_obsidian", ConceptCoreObsidian::new);
    public static RegistryObject<Item> CONCEPT_CORE_OBSIDIAN_ADVANCED = registerItem("concept_core_obsidian_advanced", ConceptCoreObsidianAdvanced::new);
    public static RegistryObject<Item> CONCEPT_CORE_PORKCHOP = registerItem("concept_core_porkchop", ConceptCorePorkchop::new);
    public static RegistryObject<Item> CONCEPT_CORE_SHULKER_SHELL = registerItem("concept_core_shulker_shell", ConceptCoreShulkerShell::new);
    public static RegistryObject<Item> CONCEPT_CORE_SNOWBALL = registerItem("concept_core_snowball", ConceptCoreSnowball::new);
    public static RegistryObject<Item> CONCEPT_CORE_STONE = registerItem("concept_core_stone", ConceptCoreStone::new);
    public static RegistryObject<Item> CONCEPT_CORE_SUGAR_CANE = registerItem("concept_core_sugar_cane", ConceptCoreSugarCane::new);
    public static RegistryObject<Item> CONCEPT_CORE_TNT = registerItem("concept_core_tnt", ConceptCoreTNT::new);
    public static RegistryObject<Item> CONCEPT_CORE_VILLAGER_SPAWN = registerItem("concept_core_villager", ConceptCoreVillager::new);
    public static RegistryObject<Item> CONCEPT_CORE_WATER = registerItem("concept_core_water", ConceptCoreWater::new);

    public static <V extends Item> RegistryObject<V> registerItem(String name, Function<Item.Properties, V> sup) {
        return ITEM_REGISTER.register(name, () -> sup.apply(new Item.Properties().tab(SheathMagicCreativeTab.INSTANCE)));
    }
}
