package cn.nulladev.vanillamagic;

import net.minecraftforge.fml.common.Mod;

@Mod("vanillamagic")
public class VanillaMagic {

    public static final String MODID = "vanillamagic";

    public VanillaMagic() {

        VMRegistry.INSTANCE.registerEvents();

    }

}
