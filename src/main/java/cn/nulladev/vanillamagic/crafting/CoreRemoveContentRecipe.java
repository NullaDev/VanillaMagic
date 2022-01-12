package cn.nulladev.vanillamagic.crafting;

import cn.nulladev.vanillamagic.item.conceptcore.ConceptCoreWithContent;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class CoreRemoveContentRecipe extends CustomRecipe {

    public static final RecipeSerializer<CoreRemoveContentRecipe> SERIALIZER = new SimpleRecipeSerializer<>(CoreRemoveContentRecipe::new);

    public CoreRemoveContentRecipe(ResourceLocation rl) {
        super(rl);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level) {
        boolean foundCore = false;

        for (int i = 0; i < inv.getMaxStackSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof ConceptCoreWithContent && ConceptCoreWithContent.hasContent(stack)) {
                    if (foundCore)
                        return false;
                    else
                        foundCore = true;
                } else {
                    return false;
                }
            }
        }
        return foundCore;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv) {
        ItemStack core = ItemStack.EMPTY;
        for (int i = 0; i < inv.getMaxStackSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof ConceptCoreWithContent) {
                    core = stack;
                }
            }
        }
        ItemStack core_empty = core.copy();
        core_empty.setCount(1);
        ConceptCoreWithContent.setContent(core_empty, ItemStack.EMPTY);

        return core_empty;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(@Nonnull CraftingContainer inv) {
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < ret.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.getItem() instanceof ConceptCoreWithContent) {
                ret.set(i, ConceptCoreWithContent.getContent(stack));
            }
        }
        return ret;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

}
