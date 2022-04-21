package cn.nulladev.vanillamagic.core;

import cn.nulladev.vanillamagic.block.CollectorBE;
import cn.nulladev.vanillamagic.client.CoreBagMenu;
import cn.nulladev.vanillamagic.client.CollectorMenu;
import cn.nulladev.vanillamagic.client.CrystalMenu;
import cn.nulladev.vanillamagic.crafting.*;
import com.lcy0x1.base.BaseRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class VMRegistry {

    public static final VMRegistry INSTANCE = new VMRegistry();

    public static final DeferredRegister<BlockEntityType<?>> BE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, VanillaMagic.MODID);
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.CONTAINERS, VanillaMagic.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, VanillaMagic.MODID);

    public static final RegistryObject<BlockEntityType<CollectorBE>> BE_COLLECTOR = BE.register("collector", () -> BlockEntityType.Builder.of(CollectorBE::new, VMBlocks.AUTOMATIC_COLLECTOR.get()).build(null));

    public static final RegistryObject<MenuType<CrystalMenu>> MT_CRYSTAL = MENU.register("space_crystal", () -> IForgeMenuType.create(CrystalMenu::fromNetwork));
    public static final RegistryObject<MenuType<CoreBagMenu>> MT_CORE_BAG = MENU.register("core_bag", () -> IForgeMenuType.create(CoreBagMenu::fromNetwork));
    public static final RegistryObject<MenuType<CollectorMenu>> MT_COLLECTOR = MENU.register("collector", () -> IForgeMenuType.create((windowId, inv, data) -> new CollectorMenu(windowId, inv, data.readBlockPos(), Minecraft.getInstance().level)));

    public static RecipeType<AbstractCrystalRecipe<?>> RT_CRYSTAL;
    @SubscribeEvent
    public static void registerRecipeType(RegistryEvent.Register<Block> event) {
        // Forge does not include a registry for RecipeTypes, and starting from 1.18.2,
        // registering in a vanilla registry must be done in any registry event.
        RT_CRYSTAL = RecipeType.register("vanillamagic:crystal");
    }
    public static final RegistryObject<BaseRecipe.RecType<DefaultCrystalRecipe, AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer>> RS_CRYSTAL_DEFAULT = RECIPE.register("crystal_default", () -> new BaseRecipe.RecType<>(DefaultCrystalRecipe.class, RT_CRYSTAL));
    public static final RegistryObject<RecipeSerializer<WandCoreRecipe>> RS_WAND_CORE = RECIPE.register("wand_core", () -> WandCoreRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<WandRemoveCoreRecipe>> RS_WAND_REMOVE_CORE = RECIPE.register("wand_remove_core", () -> WandRemoveCoreRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<CoreContentRecipe>> RS_CORE_CONTENT = RECIPE.register("core_content", () -> CoreContentRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<CoreRemoveContentRecipe>> RS_CORE_REMOVE_CONTENT = RECIPE.register("core_remove_content", () -> CoreRemoveContentRecipe.SERIALIZER);

    public void registerEvents() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(INSTANCE);
        VMBlocks.BLOCK.register(modBus);
        VMItems.ITEM.register(modBus);
        BE.register(modBus);
        RECIPE.register(modBus);
        MENU.register(modBus);
    }

}
