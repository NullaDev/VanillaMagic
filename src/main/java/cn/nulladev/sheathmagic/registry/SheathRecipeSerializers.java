package cn.nulladev.sheathmagic.registry;

import cn.nulladev.sheathmagic.core.SheathMagic;
import cn.nulladev.sheathmagic.crafting.BasicSpaceCrystalRecipe;
import cn.nulladev.sheathmagic.crafting.BasicSpaceCrystalRecipeSerializer;
import cn.nulladev.sheathmagic.crafting.WandAddCoreRecipe;
import cn.nulladev.sheathmagic.crafting.WandRemoveCoreRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SheathRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SheathMagic.MODID);

    public static final RegistryObject<RecipeSerializer<WandAddCoreRecipe>> RECIPE_WAND_ADD_CORE = RECIPE_SERIALIZER.register("wand_add_core", () -> WandAddCoreRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<WandRemoveCoreRecipe>> RECIPE_WAND_REMOVE_CORE = RECIPE_SERIALIZER.register("wand_remove_core", () -> WandRemoveCoreRecipe.SERIALIZER);

    public static final RegistryObject<RecipeSerializer<BasicSpaceCrystalRecipe>> BASIC_SPACE_CRYSTAL_RECIPE_SERIALIZER = RECIPE_SERIALIZER.register("crystal", () -> BasicSpaceCrystalRecipeSerializer.INSTANCE);

}
