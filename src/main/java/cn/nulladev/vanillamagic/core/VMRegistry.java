package cn.nulladev.vanillamagic.core;

import cn.nulladev.vanillamagic.client.CrystalMenu;
import cn.nulladev.vanillamagic.crafting.*;
import com.lcy0x1.base.BaseRecipe;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

public class VMRegistry {

    public static final VMRegistry INSTANCE = new VMRegistry();

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, VanillaMagic.MODID);
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.CONTAINERS, VanillaMagic.MODID);

    public static final RecipeType<AbstractCrystalRecipe<?>> RT_CRYSTAL = RecipeType.register("vanillamagic:crystal");

    public static final RegistryObject<BaseRecipe.RecType<DefaultCrystalRecipe, AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer>> RS_CRYSTAL_DEFAULT = RECIPE.register("crystal_default", () -> new BaseRecipe.RecType<>(DefaultCrystalRecipe.class, RT_CRYSTAL));
    public static final RegistryObject<RecipeSerializer<WandCoreRecipe>> RS_WAND_CORE = RECIPE.register("wand_core", () -> WandCoreRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<WandRemoveCoreRecipe>> RS_WAND_REMOVE_CORE = RECIPE.register("wand_remove_core", () -> WandRemoveCoreRecipe.SERIALIZER);

    public static final RegistryObject<MenuType<CrystalMenu>> MT_CRYSTAL = MENU.register("space_crystal", () -> IForgeMenuType.create(CrystalMenu::fromNetwork));

    public void registerEvents() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(INSTANCE);
        VMBlocks.BLOCK.register(modBus);
        VMItems.ITEM.register(modBus);
        RECIPE.register(modBus);
        MENU.register(modBus);
    }
}
