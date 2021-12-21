package cn.nulladev.vanillamagic.client;

import cn.nulladev.vanillamagic.core.VanillaMagic;
import com.lcy0x1.base.BaseContainerScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CrystalGui extends BaseContainerScreen<CrystalMenu> {
    public final CrystalMenu menu;

    public CrystalGui(CrystalMenu menu, Inventory plInv, Component title) {
        super(menu, plInv, title);
        this.menu = menu;
    }

    @Override
    protected void renderBg(PoseStack p_97787_, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, getTexture());
        int k = (width - imageWidth) / 2;
        int l = (height - imageHeight) / 2;
        blit(p_97787_, k, l, 0, 0, imageWidth, imageHeight);
    }

    private ResourceLocation getTexture() {
        return new ResourceLocation(VanillaMagic.MODID, "textures/gui/container/crystal_" + this.menu.getSize() + ".png");
    }
}
