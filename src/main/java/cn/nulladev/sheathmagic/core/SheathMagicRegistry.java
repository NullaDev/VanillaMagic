package cn.nulladev.sheathmagic.core;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cn.nulladev.sheathmagic.registry.SheathBlockEntities.BLOCK_ENTITY_TYPE;
import static cn.nulladev.sheathmagic.registry.SheathBlocks.BLOCK_REGISTER;
import static cn.nulladev.sheathmagic.registry.SheathItems.ITEM_REGISTER;
import static cn.nulladev.sheathmagic.registry.SheathMenus.MENU_TYPE;
import static cn.nulladev.sheathmagic.registry.SheathRecipeSerializers.RECIPE_SERIALIZER;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SheathMagicRegistry {

    public static final SheathMagicRegistry INSTANCE = new SheathMagicRegistry();


    public void registerEvents() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(INSTANCE);
        BLOCK_REGISTER.register(modBus);
        ITEM_REGISTER.register(modBus);
        BLOCK_ENTITY_TYPE.register(modBus);
        MENU_TYPE.register(modBus);
        RECIPE_SERIALIZER.register(modBus);
    }
}
