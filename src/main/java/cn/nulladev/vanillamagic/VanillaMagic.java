package cn.nulladev.vanillamagic;

import net.minecraftforge.fml.common.Mod;

@Mod(VanillaMagic.MODID)
public class VanillaMagic {

    public static final String MODID = "vanillamagic";

    public VanillaMagic() {

        VMRegistry.INSTANCE.registerEvents();

    }

}
