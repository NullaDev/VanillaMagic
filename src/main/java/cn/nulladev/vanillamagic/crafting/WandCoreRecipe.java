package cn.nulladev.vanillamagic.crafting;

import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import cn.nulladev.vanillamagic.item.WorldInteractionWand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;

public class WandCoreRecipe extends CustomRecipe {

    public static final RecipeSerializer<WandCoreRecipe> SERIALIZER = new SimpleRecipeSerializer<>(WandCoreRecipe::new);

    public WandCoreRecipe(ResourceLocation rl) {
        super(rl);
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level) {
        System.out.println("woshisb");
        boolean foundCore = false;
        boolean foundWand = false;
        for (int i = 0; i < inv.getMaxStackSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof WorldInteractionWand && !WorldInteractionWand.hasCore(stack)) {
                    foundWand = true;
                } else if (stack.getItem() instanceof ConceptCore) {
                    foundCore = true;
                } else {
                    return false;
                }
            }
        }
        return foundCore && foundWand;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv) {
        ItemStack core = ItemStack.EMPTY;
        ItemStack wand = ItemStack.EMPTY;

        for (int i = 0; i < inv.getMaxStackSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof WorldInteractionWand) {
                    wand = stack;
                } else if (stack.getItem() instanceof ConceptCore) {
                    core = stack;
                }
            }
        }

        if (core.isEmpty() || wand.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack wand_with_core = wand.copy();
        WorldInteractionWand.setCore(wand_with_core, core);

        return wand_with_core;
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
