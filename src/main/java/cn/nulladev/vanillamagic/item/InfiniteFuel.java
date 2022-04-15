package cn.nulladev.vanillamagic.item;

import cn.nulladev.vanillamagic.core.VMItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class InfiniteFuel extends Item {
    public InfiniteFuel(Properties props) {
        super(props.stacksTo(1));
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == VMItems.INFINITE_FUEL.get())
            event.setBurnTime(67);
    }

    @Override
    public boolean hasContainerItem(ItemStack itemStack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(VMItems.INFINITE_FUEL.get());
    }

}
