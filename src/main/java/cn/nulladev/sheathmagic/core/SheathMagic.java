package cn.nulladev.sheathmagic.core;


import cn.nulladev.sheathmagic.proxy.ClientProxy;
import cn.nulladev.sheathmagic.proxy.IProxy;
import cn.nulladev.sheathmagic.proxy.ServerProxy;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SheathMagic.MODID)
public class SheathMagic {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "sheathmagic";
    // Directly reference a slf4j logger
    //private static final Logger LOGGER = LogUtils.getLogger();

    private final IProxy proxy;

    public SheathMagic()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        proxy = DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
        SheathMagicRegistry.INSTANCE.registerEvents();
    }

    public void setup(final FMLClientSetupEvent event) {
        proxy.setup();
    }

}
