package cn.nulladev.sheathmagic.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientProxy implements IProxy{
    public Level getLevel() {
        return Minecraft.getInstance().level;
    }

    public Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    @Override
    public void setup() {
    }
}
