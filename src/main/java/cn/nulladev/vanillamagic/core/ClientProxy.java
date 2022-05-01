package cn.nulladev.vanillamagic.core;

import cn.nulladev.vanillamagic.client.CollectorGui;
import cn.nulladev.vanillamagic.client.CoreBagGui;
import cn.nulladev.vanillamagic.client.CrystalGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
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
		MenuScreens.register(VMRegistry.MT_CORE_BAG.get(), CoreBagGui::new);
		MenuScreens.register(VMRegistry.MT_COLLECTOR.get(), CollectorGui::new);
	}
}
