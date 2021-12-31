package cn.nulladev.vanillamagic.item;

import cn.nulladev.vanillamagic.core.VMItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class InfiniteFuel extends Item {
    public InfiniteFuel(Properties props) {
        super(props.stacksTo(1));
    }

    public void initCraftingRemain() {
        try {
            Field field = this.getClass().getField("craftingRemainingItem");
            field.setAccessible(true);
            field.set(this, VMItems.INFINITE_FUEL.get());
        } catch (Exception e) {

        }
    }

    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == VMItems.INFINITE_FUEL.get())
            event.setBurnTime(67);
    }

}
