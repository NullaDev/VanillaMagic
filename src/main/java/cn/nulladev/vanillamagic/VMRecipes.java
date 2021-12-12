package cn.nulladev.vanillamagic;

import cn.nulladev.vanillamagic.crafting.WandCoreRecipe;
import cn.nulladev.vanillamagic.crafting.WandRemoveCoreRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class VMRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, VanillaMagic.MODID);

    public static final RegistryObject<RecipeSerializer<WandCoreRecipe>> WAND_CORE = register("wand_core", ()-> WandCoreRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<WandRemoveCoreRecipe>> WAND_REMOVE_CORE = register("wand_remove_core", ()-> WandRemoveCoreRecipe.SERIALIZER);

    public static <V extends RecipeSerializer<?>> RegistryObject<V> register(String name, Supplier<V> sup) {
        return RECIPE.register(name, sup);
    }
}
