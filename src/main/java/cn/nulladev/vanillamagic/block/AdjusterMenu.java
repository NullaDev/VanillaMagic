package cn.nulladev.vanillamagic.block;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class AdjusterMenu extends AbstractContainerMenu {
    private static final int CRAFT_SLOT_START = 0;
    private static final int CRAFT_SLOT_NUM = 25;
    private static final int INPUT_SLOT = 25;
    private static final int OUTPUT_SLOT = 26;

    private final AdjusterContainer container = new AdjusterContainer(this);
    private final ContainerLevelAccess access;
    private final Player player;

    public AdjusterMenu(int index, Inventory inventory, ContainerLevelAccess access) {
        super(MenuType.CRAFTING, index);
        this.access = access;
        this.player = inventory.player;

        for (int i = 0; i < CRAFT_SLOT_NUM; i++)
            this.addSlot(new AdjusterCraftSlot(this.container, CRAFT_SLOT_START + i, 8 + 18 * (i % 5), 5 + 18 * (i / 5)));

        this.addSlot(new CrystalSlot(this.container, INPUT_SLOT, 124, 25));
        this.addSlot(new InvalidSlot(this.container, OUTPUT_SLOT, 124, 45));

        for (int i = 0; i < 27; i++)
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 155));

        for (int i = 0; i < 9; i++)
            this.addSlot(new Slot(inventory, 9 + i, 8 + 18 * (i % 9), 97 + 18 * (i / 9)));

    }

    protected static void slotChange(AbstractContainerMenu menu, Level level, Player player, AdjusterContainer container) {
        if (!level.isClientSide) {
            ServerPlayer serverplayer = (ServerPlayer)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<Recipe> optional = null; //level.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, container, level);
            if (optional.isPresent()) {
                Recipe recipe = optional.get();
                itemstack = recipe.assemble(container);
            }

            container.setItem(25, itemstack);
            menu.setRemoteSlot(OUTPUT_SLOT, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), OUTPUT_SLOT, itemstack));
        }
    }

    @Override
    public void slotsChanged(Container p_39366_) {
        this.access.execute((level, p_39387_) -> {
            slotChange(this, level, this.player, this.container);
        });
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return true;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.index != OUTPUT_SLOT && super.canTakeItemForPickAll(stack, slot);
    }

}
