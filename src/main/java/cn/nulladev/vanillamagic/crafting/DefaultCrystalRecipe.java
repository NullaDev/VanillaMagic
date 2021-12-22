package cn.nulladev.vanillamagic.crafting;

import cn.nulladev.vanillamagic.client.CrystalMenu;
import cn.nulladev.vanillamagic.core.VMRegistry;
import com.lcy0x1.core.util.SerialClass;
import net.minecraft.resources.ResourceLocation;

@SerialClass
public class DefaultCrystalRecipe extends AbstractCrystalRecipe<DefaultCrystalRecipe> {

    public DefaultCrystalRecipe(ResourceLocation id) {
        super(id, VMRegistry.RS_CRYSTAL_DEFAULT.get());
    }

}
