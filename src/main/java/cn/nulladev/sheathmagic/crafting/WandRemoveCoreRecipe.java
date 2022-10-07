package cn.nulladev.sheathmagic.crafting;

import cn.nulladev.sheathmagic.item.WorldInteroperationWand;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class WandRemoveCoreRecipe extends CustomRecipe {
    public WandRemoveCoreRecipe(ResourceLocation location) {
        super(location);
    }

    public static final RecipeSerializer<WandRemoveCoreRecipe> SERIALIZER = new SimpleRecipeSerializer<>(WandRemoveCoreRecipe::new);

    @Override
    public boolean matches(CraftingContainer inventory, Level level) {

        boolean foundWand = false;

        for (int i = 0; i < inventory.getMaxStackSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof WorldInteroperationWand && WorldInteroperationWand.hasCore(stack)) {
                    if (foundWand)
                        return false;
                    else
                        foundWand = true;
                } else {
                    return false;
                }
            }
        }
        return foundWand;
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory) {

        ItemStack wand = ItemStack.EMPTY;
        for (int i = 0; i < inventory.getMaxStackSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof WorldInteroperationWand) {
                    wand = stack;
                }
            }
        }
        ItemStack wand_empty = wand.copy();
        wand_empty.setCount(1);
        WorldInteroperationWand.setCore(wand_empty, ItemStack.EMPTY);

        return wand_empty;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(@Nonnull CraftingContainer inv) {
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < ret.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.getItem() instanceof WorldInteroperationWand) {
                ret.set(i, WorldInteroperationWand.getCore(stack));
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
