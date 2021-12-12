package cn.nulladev.vanillamagic;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class VMRegistry {

    public static final VMRegistry INSTANCE = new VMRegistry();

    public void registerEvents() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(INSTANCE);
        VMBlocks.BLOCK.register(modBus);
        VMItems.ITEM.register(modBus);
        VMRecipes.RECIPE.register(modBus);
    }
}
