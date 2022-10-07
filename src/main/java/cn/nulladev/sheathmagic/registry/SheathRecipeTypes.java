package cn.nulladev.sheathmagic.registry;

import cn.nulladev.sheathmagic.core.SheathMagic;
import cn.nulladev.sheathmagic.crafting.BasicSpaceCrystalRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SheathRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, SheathMagic.MODID);


    public static final RegistryObject<RecipeType<BasicSpaceCrystalRecipe>> SPACE_CRYSTAL_BASIC_RECIPE_TYPE = RECIPE_TYPE.register("space_crystal_basic", () -> registerRecipeType("space_crystal_basic"));
    public static final RegistryObject<RecipeType<BasicSpaceCrystalRecipe>> SPACE_CRYSTAL_ADVANCED_RECIPE_TYPE = RECIPE_TYPE.register("space_crystal_advance", () -> registerRecipeType("space_crystal_advance"));
    public static final RegistryObject<RecipeType<BasicSpaceCrystalRecipe>> SPACE_CRYSTAL_ULTIMATE_RECIPE_TYPE = RECIPE_TYPE.register("space_crystal_ultimate", () -> registerRecipeType("space_crystal_ultimate"));

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<>()
        {
            public String toString() {
                return SheathMagic.MODID + ":" + identifier;
            }
        };
    }
}
