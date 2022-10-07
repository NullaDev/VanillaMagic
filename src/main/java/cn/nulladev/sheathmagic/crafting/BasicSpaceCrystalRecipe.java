package cn.nulladev.sheathmagic.crafting;

import cn.nulladev.sheathmagic.registry.SheathRecipeSerializers;
import cn.nulladev.sheathmagic.registry.SheathRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.HashMap;

public class BasicSpaceCrystalRecipe implements Recipe<SimpleContainer> {
    private static final int SIZE = 3;
    private final ResourceLocation id;
    public final String[] pattern;
    public final HashMap<String, Ingredient> keys;
    public final ItemStack result;

    public BasicSpaceCrystalRecipe(ResourceLocation id, String[] pattern, HashMap<String, Ingredient> keys, ItemStack result) {
        this.id = id;
        this.pattern = pattern;
        this.keys = keys;
        this.result = result;
    }

    @Override
    public boolean matches(SimpleContainer inventory, Level level) {
        if (pattern.length != SIZE) {
            return false;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Ingredient ingredient = keys.get("" + pattern[i].charAt(j));
                if (ingredient == null) {
                    ingredient = Ingredient.EMPTY;
                }
                if (!ingredient.test(inventory.getItem(i * SIZE + j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width == SIZE && height == SIZE;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SheathRecipeSerializers.BASIC_SPACE_CRYSTAL_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return SheathRecipeTypes.SPACE_CRYSTAL_BASIC_RECIPE_TYPE.get();
    }
}
