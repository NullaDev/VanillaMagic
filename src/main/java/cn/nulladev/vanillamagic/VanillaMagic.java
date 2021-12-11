package cn.nulladev.vanillamagic;

import net.minecraftforge.fml.common.Mod;

@Mod("vanillamagic")
public class VanillaMagic {

    public VanillaMagic() {

        VMRegistry.INSTANCE.registerEvents();

    }

}
