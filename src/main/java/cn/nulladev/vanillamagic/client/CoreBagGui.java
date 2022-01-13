package cn.nulladev.vanillamagic.client;

import com.lcy0x1.base.BaseContainerScreen;
import com.lcy0x1.core.util.SpriteManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CoreBagGui extends BaseContainerScreen<CoreBagMenu> {

    public CoreBagGui(CoreBagMenu menu, Inventory plInv, Component title) {
        super(menu, plInv, title);
    }

    @Override
    public void render(PoseStack p_98418_, int p_98419_, int p_98420_, float p_98421_) {
        this.renderBackground(p_98418_);
        super.render(p_98418_, p_98419_, p_98420_, p_98421_);
        this.renderTooltip(p_98418_, p_98419_, p_98420_);
    }

    @Override
    protected void renderBg(PoseStack matrix, float p_97788_, int p_97789_, int p_97790_) {
        SpriteManager sm = menu.sprite;
        SpriteManager.ScreenRenderer sr = sm.getRenderer(this);
        sr.start(matrix);
    }

}
