package cn.nulladev.vanillamagic.crafting;

import cn.nulladev.vanillamagic.VMItems;
import cn.nulladev.vanillamagic.item.WorldInteractionWand;
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

    public static final RecipeSerializer<WandRemoveCoreRecipe> SERIALIZER = new SimpleRecipeSerializer<>(WandRemoveCoreRecipe::new);

    public WandRemoveCoreRecipe(ResourceLocation rl) {
        super(rl);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level) {
        boolean foundWand = false;

        for (int i = 0; i < inv.getMaxStackSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof WorldInteractionWand && WorldInteractionWand.hasCore(stack)) {
                    foundWand = true;
                } else {
                    return false;
                }
            }
        }
        return foundWand;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv) {
        ItemStack wand = ItemStack.EMPTY;
        for (int i = 0; i < inv.getMaxStackSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof WorldInteractionWand) {
                    wand = stack;
                }
            }
        }
        ItemStack wand_empty = wand.copy();
        wand_empty.setCount(1);
        WorldInteractionWand.setCore(wand_empty, "");

        return wand_empty;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(@Nonnull CraftingContainer inv) {
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < ret.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.getItem() instanceof WorldInteractionWand) {
                ret.set(i, new ItemStack(VMItems.getCoreFromName(WorldInteractionWand.getCore(stack))));
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
