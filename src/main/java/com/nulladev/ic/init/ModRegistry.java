package com.nulladev.ic.init;

import net.minecraftforge.eventbus.api.IEventBus;

public class ModRegistry {

    public static void initAllRegistries(IEventBus bus) {
        BlockRegistry.BLOCK.register(bus);
        ItemRegistry.ITEM.register(bus);
    }

}
