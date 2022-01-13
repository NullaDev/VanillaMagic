package cn.nulladev.vanillamagic.crafting;

import cn.nulladev.vanillamagic.client.CrystalMenu;
import com.lcy0x1.base.BaseRecipe;
import com.lcy0x1.core.util.SerialClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.HashMap;

@SerialClass
public class AbstractCrystalRecipe<Rec extends AbstractCrystalRecipe<Rec>> extends BaseRecipe<Rec,
        AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer> {

    @SerialClass.SerialField
    public String[] pattern;
    @SerialClass.SerialField(generic = {String.class, Ingredient.class})
    public HashMap<String, Ingredient> key;
    @SerialClass.SerialField
    public ItemStack result;

    public AbstractCrystalRecipe(ResourceLocation id, RecType<Rec, AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer> fac) {
        super(id, fac);
    }

    @Override
    public boolean matches(CrystalMenu.CrystalContainer inv, Level world) {
        if (inv.getWidth() != pattern.length) {
            return false;
        }
        for (int i = 0; i < inv.getWidth(); i++) {
            for (int j = 0; j < inv.getWidth(); j++) {
                Ingredient ing = key.get("" + pattern[i].charAt(j));
                if (ing == null) {
                    ing = Ingredient.EMPTY;
                }
                if (!ing.test(inv.getItem(i * inv.getWidth() + j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(CrystalMenu.CrystalContainer inv) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int r, int c) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }

}
