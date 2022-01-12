package cn.nulladev.vanillamagic.crafting;

import cn.nulladev.vanillamagic.item.conceptcore.ConceptCoreWithContent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;

public class CoreContentRecipe extends CustomRecipe {

    public static final RecipeSerializer<CoreContentRecipe> SERIALIZER = new SimpleRecipeSerializer<>(CoreContentRecipe::new);

    public CoreContentRecipe(ResourceLocation rl) {
        super(rl);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level) {
        ConceptCoreWithContent core = null;
        int coreIndex = -1;
        for (int i = 0; i < inv.getMaxStackSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof ConceptCoreWithContent && !ConceptCoreWithContent.hasContent(stack)) {
                    if (core != null) {
                        return false;
                    } else {
                        core = (ConceptCoreWithContent) stack.getItem();
                        coreIndex = i;
                    }
                }
            }
        }
        if (core != null) {
            boolean foundContent = false;
            for (int i = 0; i < inv.getMaxStackSize(); i++) {
                if (i == coreIndex)
                    continue;
                ItemStack stack = inv.getItem(i);
                if (!stack.isEmpty()) {
                    if (foundContent) {
                        return false;
                    } else if(core.isContentValid(stack)) {
                        foundContent = true;
                    }
                }
            }
            return foundContent;
        } else {
            return false;
        }
    }

    @Override
    public ItemStack assemble(CraftingContainer inv) {
        ItemStack core = ItemStack.EMPTY;
        ItemStack content = ItemStack.EMPTY;

        for (int i = 0; i < inv.getMaxStackSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof ConceptCoreWithContent) {
                    core = stack;
                } else {
                    content = stack;
                }
            }
        }

        if (core.isEmpty() || content.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack core_with_content = core.copy();
        ItemStack content_size_1 = content.copy();
        content_size_1.setCount(1);
        ConceptCoreWithContent.setContent(core_with_content, content_size_1);

        return core_with_content;
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
