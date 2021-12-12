package cn.nulladev.vanillamagic;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class VMRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = null; //DeferredRegister.create(RecipeSerializer.class, VanillaMagic.MODID);

    /*public static <V extends RecipeSerializer> RegistryObject<V> register(String name, Function<Item.Properties, V> sup) {
        return RECIPE.register(name, () -> sup.apply(new Item.Properties().tab(VMCreativeTab.INSTANCE)));
    }*/
}
