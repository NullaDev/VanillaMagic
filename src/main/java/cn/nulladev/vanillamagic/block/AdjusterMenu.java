package cn.nulladev.vanillamagic.block;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.crafting.Recipe;

public class AdjusterMenu extends RecipeBookMenu<Container> {
    private static final int CRAFT_SLOT_START = 0;
    private static final int CRAFT_SLOT_END = 24;
    private static final int INPUT_SLOT = 25;
    private static final int OUTPUT_SLOT = 26;
    private static final int INV_SLOT_START = 27;
    private static final int INV_SLOT_END = 53;
    private static final int USE_ROW_SLOT_START = 54;
    private static final int USE_ROW_SLOT_END = 63;

    private final CraftingContainer craftSlots = new CraftingContainer(this, 3, 3);
    private final ResultContainer resultSlots = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;

    public AdjusterMenu(int index, Inventory inventory, ContainerLevelAccess access) {
        super(MenuType.CRAFTING, index);
        this.access = access;
        this.player = inventory.player;

        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                this.addSlot(new AdjusterCraftSlot(this.craftSlots, j + i * 5, 30 + j * 18, 17 + i * 18));
            }
        }
        this.addSlot(new CrystalSlot(this.craftSlots, INPUT_SLOT, 124, 25));
        this.addSlot(new ResultSlot(inventory.player, this.craftSlots, this.resultSlots, OUTPUT_SLOT, 124, 35));
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents p_40117_) {

    }

    @Override
    public void clearCraftingContent() {

    }

    @Override
    public boolean recipeMatches(Recipe<? super Container> p_40118_) {
        return false;
    }

    @Override
    public int getResultSlotIndex() {
        return 0;
    }

    @Override
    public int getGridWidth() {
        return 0;
    }

    @Override
    public int getGridHeight() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return null;
    }

    @Override
    public boolean shouldMoveToInventory(int p_150635_) {
        return false;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return false;
    }
}
