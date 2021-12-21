package cn.nulladev.vanillamagic.core;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VanillaMagic.MODID)
public class VanillaMagic {

    public static final String MODID = "vanillamagic";

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public VanillaMagic() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        VMRegistry.INSTANCE.registerEvents();
    }

    public void setup(final FMLClientSetupEvent event) {
        proxy.setup();
    }

}
