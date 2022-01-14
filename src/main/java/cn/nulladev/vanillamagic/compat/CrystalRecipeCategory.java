package cn.nulladev.vanillamagic.compat;

import cn.nulladev.vanillamagic.crafting.DefaultCrystalRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CrystalRecipeCategory implements IRecipeCategory<DefaultCrystalRecipe> {

    private final ResourceLocation bg;
    private final ResourceLocation uid;
    private final ItemStack stack;
    private final String translation;
    private final int size;

    private IDrawable background, icon;


    public CrystalRecipeCategory(ResourceLocation uid, ItemStack stack, ResourceLocation bg, String translation, int size) {
        this.uid = uid;
        this.bg = bg;
        this.stack = stack;
        this.translation = translation;
        this.size = size;
    }

    public CrystalRecipeCategory init(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(bg, 7, 16, 162, 18 * size)
                .addPadding(0, 0, 0, 0)
                .build();
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM, stack);
        return this;
    }

    @Override
    public ResourceLocation getUid() {
        return uid;
    }

    @Override
    public Class<? extends DefaultCrystalRecipe> getRecipeClass() {
        return DefaultCrystalRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent(translation);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(DefaultCrystalRecipe recipe, IIngredients ingredients) {
        List<Ingredient> inputs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inputs.add(Optional.ofNullable(recipe.key.get("" + recipe.pattern[i].charAt(j))).orElse(Ingredient.EMPTY));
            }
        }
        ingredients.setInputIngredients(inputs);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DefaultCrystalRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup group = recipeLayout.getItemStacks();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                group.init(i * size + j, true, 45 - size * 9 + j * 18, i * 18);
                group.set(size * i + j, ingredients.getInputs(VanillaTypes.ITEM).get(i * size + j));
            }
        }
        group.init(size * size, false, 131, size * 9 - 9);
        group.set(size * size, recipe.getResultItem());
    }

}
