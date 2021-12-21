package cn.nulladev.vanillamagic.client;

import cn.nulladev.vanillamagic.core.VMItems;
import cn.nulladev.vanillamagic.core.VMRegistry;
import cn.nulladev.vanillamagic.core.VanillaMagic;
import cn.nulladev.vanillamagic.crafting.AbstractCrystalRecipe;
import cn.nulladev.vanillamagic.item.SpaceCrystal;
import com.lcy0x1.base.BaseContainerMenu;
import com.lcy0x1.base.BaseRecipe;
import com.lcy0x1.base.PredSlot;
import com.lcy0x1.core.util.SpriteManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Predicate;

@ParametersAreNonnullByDefault
public class CrystalMenu extends BaseContainerMenu<CrystalMenu> implements ContainerListener {

    public static class CrystalContainer extends BaseContainer<CrystalMenu> implements BaseRecipe.RecInv<AbstractCrystalRecipe<?>> {

        public CrystalContainer(CrystalMenu menu) {
            super(menu);
        }

        public int getWidth(){
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
        if (this.crystal.getItem() instanceof SpaceCrystal)
            return ((SpaceCrystal) crystal.getItem()).size;
        else
            return 0;
    }

    public static SpriteManager getSprite(ItemStack crystal) {
        if (crystal.getItem() == VMItems.SPACE_CRYSTAL_LARGE.get())
            return SPRITE_5;
        else if (crystal.getItem() == VMItems.SPACE_CRYSTAL_MIDDLE.get())
            return SPRITE_4;
        else
            return SPRITE_3;
    }

    public CrystalMenu(int windowID, Inventory inventory, ItemStack crystal) {
        super(VMRegistry.MT_CRYSTAL.get(), windowID, inventory, getSprite(crystal), CrystalContainer::new);
        this.player = inventory.player;
        this.crystal = crystal;
        this.addSlot("output_slot", stack -> false, slot -> slot.setPickup(() -> false));
        for (int i = 0; i < getSize(); i++)
            for (int j = 0; j < getSize(); j++)
                this.addCraftingSlot("grid", i, j, getSize());
        this.container.addListener(this);
    }

    protected void addCraftingSlot(String name, int i, int j, int size) {
        sprite.getSlot(name, (x, y) -> new PredSlot(container, i * size + j + 1, x + 18 * j, y + 18 * i, stack -> true), this::addSlot);
    }

    @Override
    public void containerChanged(Container p_18983_) {

    }

}
