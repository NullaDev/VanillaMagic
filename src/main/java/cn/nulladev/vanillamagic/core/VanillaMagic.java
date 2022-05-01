package cn.nulladev.vanillamagic.core;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VanillaMagic.MODID)
public class VanillaMagic {

	public static final String MODID = "vanillamagic";

	private final IProxy proxy;

	public VanillaMagic() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		proxy = DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
		VMRegistry.INSTANCE.registerEvents();
	}

	public void setup(final FMLClientSetupEvent event) {
		proxy.setup();
	}

}
