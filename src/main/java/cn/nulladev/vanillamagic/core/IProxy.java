package cn.nulladev.vanillamagic.core;

import cn.nulladev.vanillamagic.client.CrystalGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface IProxy {
    void setup();
}

class ClientProxy implements IProxy {
    public Level getLevel() {
        return Minecraft.getInstance().level;
    }
    public Player getPlayer() {
        return Minecraft.getInstance().player;
    }
    @Override
    public void setup() {
        MenuScreens.register(VMRegistry.MT_CRYSTAL.get(), CrystalGui::new);
    }
}

class ServerProxy implements IProxy {
    @Override
    public void setup() {
        VMRegistry.INSTANCE.registerReflection();
    }
}
