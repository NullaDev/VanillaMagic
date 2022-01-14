package cn.nulladev.vanillamagic.compat;

import cn.nulladev.vanillamagic.core.VanillaMagic;
import cn.nulladev.vanillamagic.crafting.DefaultCrystalRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CrystalRecipeCategory implements IRecipeCategory<DefaultCrystalRecipe> {

    private final ResourceLocation bg;
    private final ResourceLocation uid;
    private final ItemStack stack;
    private final String translation;

    private IDrawable background, icon;


    public CrystalRecipeCategory(ResourceLocation uid, ItemStack stack,ResourceLocation bg, String translation) {
        this.uid = uid;
        this.bg = bg;
        this.stack = stack;
        this.translation = translation;
    }

    public CrystalRecipeCategory init(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(bg, 0, 168, 125, 18)
                .addPadding(0, 20, 0, 0)
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
        //TODO
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DefaultCrystalRecipe recipe, IIngredients ingredients) {
        //TODO
    }

}
