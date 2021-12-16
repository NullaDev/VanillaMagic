package cn.nulladev.vanillamagic;

import cn.nulladev.vanillamagic.client.CrystalMenu;
import cn.nulladev.vanillamagic.crafting.WandCoreRecipe;
import cn.nulladev.vanillamagic.crafting.WandRemoveCoreRecipe;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VMRegistry {

    public static final VMRegistry INSTANCE = new VMRegistry();
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, VanillaMagic.MODID);
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.CONTAINERS, VanillaMagic.MODID);

    public static final RegistryObject<RecipeSerializer<WandCoreRecipe>> WAND_CORE = RECIPE.register("wand_core", ()-> WandCoreRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<WandRemoveCoreRecipe>> WAND_REMOVE_CORE = RECIPE.register("wand_remove_core", ()-> WandRemoveCoreRecipe.SERIALIZER);

    public static final RegistryObject<MenuType<CrystalMenu>> CRYSTAL = MENU.register("space_crystal", () -> IForgeMenuType.create(CrystalMenu::fromNetwork));

    public void registerEvents() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(INSTANCE);
        VMBlocks.BLOCK.register(modBus);
        VMItems.ITEM.register(modBus);
        RECIPE.register(modBus);
        MENU.register(modBus);
    }
}
