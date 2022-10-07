package cn.nulladev.sheathmagic.registry;

import cn.nulladev.sheathmagic.menu.BasicCrystalMenu;
import cn.nulladev.sheathmagic.core.SheathMagic;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SheathMenus {
    public static final DeferredRegister<MenuType<?>> MENU_TYPE = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SheathMagic.MODID);

    public static final RegistryObject<MenuType<BasicCrystalMenu>> BASIC_CRYSTAL_MENU = MENU_TYPE.register("basic_crystal", () -> IForgeMenuType.create(BasicCrystalMenu::fromNetwork));
}
