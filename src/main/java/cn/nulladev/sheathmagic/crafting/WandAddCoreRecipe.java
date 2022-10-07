package cn.nulladev.sheathmagic.crafting;

import cn.nulladev.sheathmagic.item.WorldInteroperationWand;
import cn.nulladev.sheathmagic.item.conceptcore.IConceptCoreWand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;

public class WandAddCoreRecipe extends CustomRecipe {
    public WandAddCoreRecipe(ResourceLocation location) {
        super(location);
    }

    public static final RecipeSerializer<WandAddCoreRecipe> SERIALIZER = new SimpleRecipeSerializer<>(WandAddCoreRecipe::new);

    @Override
    public boolean matches(CraftingContainer inventory, Level level) {
        boolean foundCore = false;
        boolean foundWand = false;
        for (int i = 0; i < inventory.getMaxStackSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof WorldInteroperationWand && !WorldInteroperationWand.hasCore(stack)) {
                    if (foundWand) {
                        return false;
                    } else {
                        foundWand = true;
                    }
                } else if (stack.getItem() instanceof IConceptCoreWand && ((IConceptCoreWand) stack.getItem()).canUse(stack)) {
                    if (foundCore) {
                        return false;
                    } else {
                        foundCore = true;
                    }
                } else {
                    return false;
                }
            }
        }
        return foundCore && foundWand;
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory) {
        ItemStack core = ItemStack.EMPTY;
        ItemStack wand = ItemStack.EMPTY;

        for (int i = 0; i < inventory.getMaxStackSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof WorldInteroperationWand) {
                    wand = stack;
                } else if (stack.getItem() instanceof IConceptCoreWand) {
                    core = stack;
                }
            }
        }

        if (core.isEmpty() || wand.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack wand_with_core = wand.copy();
        WorldInteroperationWand.setCore(wand_with_core, core);

        return Items.APPLE.getDefaultInstance();
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
