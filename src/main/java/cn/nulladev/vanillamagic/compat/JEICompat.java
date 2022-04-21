package cn.nulladev.vanillamagic.compat;


import cn.nulladev.vanillamagic.core.VMItems;
import cn.nulladev.vanillamagic.core.VMRegistry;
import cn.nulladev.vanillamagic.core.VanillaMagic;
import cn.nulladev.vanillamagic.crafting.AbstractCrystalRecipe;
import cn.nulladev.vanillamagic.crafting.DefaultCrystalRecipe;
import com.lcy0x1.base.Proxy;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@JeiPlugin
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class JEICompat implements IModPlugin {

    public static JEICompat INSTANCE;

    public static final ResourceLocation UID = new ResourceLocation(VanillaMagic.MODID, "jei_plugin");
    public static final CrystalRecipeCategory BASIC = new CrystalRecipeCategory(
            new ResourceLocation(VanillaMagic.MODID, "crystal_basic"),
            VMItems.SPACE_CRYSTAL_BASIC.get().getDefaultInstance(),
            new ResourceLocation(VanillaMagic.MODID, "textures/gui/container/crystal_3.png"),
            "jei.title.crystal.basic", 3);
    public static final CrystalRecipeCategory ADVANCED = new CrystalRecipeCategory(
            new ResourceLocation(VanillaMagic.MODID, "crystal_advanced"),
            VMItems.SPACE_CRYSTAL_ADVANCED.get().getDefaultInstance(),
            new ResourceLocation(VanillaMagic.MODID, "textures/gui/container/crystal_4.png"),
            "jei.title.crystal.advanced", 4);
    public static final CrystalRecipeCategory ULTIMATE = new CrystalRecipeCategory(
            new ResourceLocation(VanillaMagic.MODID, "crystal_ultimate"),
            VMItems.SPACE_CRYSTAL_ULTIMATE.get().getDefaultInstance(),
            new ResourceLocation(VanillaMagic.MODID, "textures/gui/container/crystal_5.png"),
            "jei.title.crystal.ultimate", 5);

    public JEICompat() {
        INSTANCE = this;
    }

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
    }

    @Override
    public void registerFluidSubtypes(ISubtypeRegistration registration) {
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registration) {
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(BASIC.init(helper));
        registration.addRecipeCategories(ADVANCED.init(helper));
        registration.addRecipeCategories(ULTIMATE.init(helper));
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<AbstractCrystalRecipe<?>> list = Proxy.getClientWorld().getRecipeManager().getAllRecipesFor(VMRegistry.RT_CRYSTAL);
        List<DefaultCrystalRecipe> def = list.stream().filter(e -> e instanceof DefaultCrystalRecipe).map(e -> (DefaultCrystalRecipe) e).toList();
        List<DefaultCrystalRecipe> basic = def.stream().filter(e -> e.pattern.length == 3).toList();
        List<DefaultCrystalRecipe> advanced = def.stream().filter(e -> e.pattern.length == 4).toList();
        List<DefaultCrystalRecipe> ultimate = def.stream().filter(e -> e.pattern.length == 5).toList();
        registration.addRecipes(BASIC.getRecipeType(), basic);
        registration.addRecipes(ADVANCED.getRecipeType(), advanced);
        registration.addRecipes(ULTIMATE.getRecipeType(), ultimate);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        //TODO
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(VMItems.SPACE_CRYSTAL_BASIC.get().getDefaultInstance(), BASIC.getRecipeType());
        registration.addRecipeCatalyst(VMItems.SPACE_CRYSTAL_ADVANCED.get().getDefaultInstance(), ADVANCED.getRecipeType());
        registration.addRecipeCatalyst(VMItems.SPACE_CRYSTAL_ULTIMATE.get().getDefaultInstance(), ULTIMATE.getRecipeType());
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    }

}
