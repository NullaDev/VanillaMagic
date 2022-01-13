package cn.nulladev.vanillamagic.client;

import com.lcy0x1.base.BaseContainerScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CoreBagGui extends BaseContainerScreen<CoreBagMenu> {

    private static final ResourceLocation BACKGROUND = new ResourceLocation("textures/gui/container/generic_54.png");

    public CoreBagGui(CoreBagMenu menu, Inventory plInv, Component title) {
        super(menu, plInv, title);
        this.imageHeight = 114 + 3 * 18;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(PoseStack p_98418_, int p_98419_, int p_98420_, float p_98421_) {
        this.renderBackground(p_98418_);
        super.render(p_98418_, p_98419_, p_98420_, p_98421_);
        this.renderTooltip(p_98418_, p_98419_, p_98420_);
    }

    @Override
    protected void renderBg(PoseStack p_97787_, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(p_97787_, i, j, 0, 0, this.imageWidth, 3 * 18 + 17);
        this.blit(p_97787_, i, j + 3 * 18 + 17, 0, 126, this.imageWidth, 96);
    }

}
