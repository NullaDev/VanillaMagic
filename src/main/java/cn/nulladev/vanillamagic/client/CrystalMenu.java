package cn.nulladev.vanillamagic.client;

import cn.nulladev.vanillamagic.core.VMItems;
import cn.nulladev.vanillamagic.core.VMRegistry;
import cn.nulladev.vanillamagic.core.VanillaMagic;
import cn.nulladev.vanillamagic.crafting.AbstractCrystalRecipe;
import cn.nulladev.vanillamagic.item.SpaceCrystal;
import com.lcy0x1.base.BaseContainerMenu;
import com.lcy0x1.base.BaseRecipe;
import com.lcy0x1.core.util.SpriteManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class CrystalMenu extends BaseContainerMenu<CrystalMenu> {

    public static class CrystalContainer extends BaseContainer<CrystalMenu> implements BaseRecipe.RecInv<AbstractCrystalRecipe<?>> {

        public CrystalContainer(int size, CrystalMenu menu) {
            super(size, menu);
        }

        public int getWidth() {
            return parent.getSize();
        }
    }

    public static final SpriteManager SPRITE_3 = new SpriteManager(VanillaMagic.MODID, "crystal_3");
    public static final SpriteManager SPRITE_4 = new SpriteManager(VanillaMagic.MODID, "crystal_4");
    public static final SpriteManager SPRITE_5 = new SpriteManager(VanillaMagic.MODID, "crystal_5");

    private final Player player;
    private final ItemStack crystal;

    public static CrystalMenu fromNetwork(int windowId, Inventory inv, FriendlyByteBuf buf) {
        InteractionHand hand = buf.readBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        return new CrystalMenu(windowId, inv, inv.player.getItemInHand(hand));
    }

    public int getSize() {
        return SpaceCrystal.getSize(this.crystal);
    }

    public static SpriteManager getSprite(ItemStack crystal) {
        if (crystal.getItem() == VMItems.SPACE_CRYSTAL_ULTIMATE.get())
            return SPRITE_5;
        else if (crystal.getItem() == VMItems.SPACE_CRYSTAL_ADVANCED.get())
            return SPRITE_4;
        else
            return SPRITE_3;
    }

    public CrystalMenu(int windowID, Inventory inventory, ItemStack crystal) {
        super(VMRegistry.MT_CRYSTAL.get(), windowID, inventory, getSprite(crystal),
                menu -> new CrystalContainer(SpaceCrystal.getSize(crystal) * SpaceCrystal.getSize(crystal) + 1, menu),
                true);
        this.player = inventory.player;
        this.crystal = crystal;
        this.addSlot("grid", stack -> true);
        this.addSlot("output_slot", stack -> false, slot -> slot.setPickup(() -> false));
    }

    @Override
    protected void bindPlayerInventory(Inventory plInv, int x, int y) {
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(plInv, j + i * 9 + 9, x + j * 18, y + i * 18));
        for (int k = 0; k < 9; ++k)
            if (k == plInv.selected && plInv.getItem(k).getItem() instanceof SpaceCrystal) {
                this.addSlot(new SlotLocked(plInv, k, x + k * 18, y + 58));
            } else {
                this.addSlot(new Slot(plInv, k, x + k * 18, y + 58));
            }
    }

    @Override
    public void slotsChanged(Container container) {
        if (!this.player.level.isClientSide) {
            Optional<AbstractCrystalRecipe<?>> optional = player.getServer().getRecipeManager().getRecipeFor(VMRegistry.RT_CRYSTAL, (CrystalContainer) this.container, this.player.level);
            if (optional.isPresent()) {
                AbstractCrystalRecipe<?> craftingrecipe = optional.get();
                ItemStack itemstack = craftingrecipe.assemble((CrystalContainer) this.container);
                this.container.setItem(getSize() * getSize(), itemstack);
            }
        }
        super.slotsChanged(container);
    }

    @Override
    protected void clearContainer(Player player, Container container) {
        Optional<AbstractCrystalRecipe<?>> optional = player.getServer().getRecipeManager().getRecipeFor(VMRegistry.RT_CRYSTAL, (CrystalContainer) this.container, this.player.level);
        if (optional.isPresent()) {
            for (int i = 0; i < getSize() * getSize(); i++) {
                ItemStack stack = container.getItem(i);
                if (!stack.isEmpty())
                    stack.shrink(1);
            }
            crystal.shrink(1);
        }
        super.clearContainer(player, container);
    }

}
