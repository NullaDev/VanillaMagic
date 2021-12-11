package cn.nulladev.vanillamagic;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class VMRegistry {

    public static final VMRegistry INSTANCE = new VMRegistry();

    public void registerEvents() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(INSTANCE);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        //event.getRegistry().registerAll();
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(VMItems.world_interaction_wand);
        event.getRegistry().register(VMItems.concept_core_water);
        event.getRegistry().register(VMItems.concept_core_cobblestone);
        event.getRegistry().register(VMItems.concept_core_stone);
        //event.getRegistry().registerAll();
    }
}
