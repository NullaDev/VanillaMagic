package cn.nulladev.vanillamagic.crafting;

import cn.nulladev.vanillamagic.client.CrystalMenu;
import net.minecraft.resources.ResourceLocation;

public class DefaultCrystalRecipe extends AbstractCrystalRecipe<DefaultCrystalRecipe> {

    public DefaultCrystalRecipe(ResourceLocation id, RecType<DefaultCrystalRecipe, AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer> fac) {
        super(id, fac);
    }

}
