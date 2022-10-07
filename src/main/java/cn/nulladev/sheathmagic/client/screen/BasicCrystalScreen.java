package cn.nulladev.sheathmagic.client.screen;

import cn.nulladev.sheathmagic.core.SheathMagic;
import cn.nulladev.sheathmagic.menu.BasicCrystalMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

import javax.annotation.Nullable;

public class BasicCrystalScreen extends AbstractContainerScreen<BasicCrystalMenu> {
    private static final ResourceLocation texture = new ResourceLocation(SheathMagic.MODID, "textures/gui/container/crystal_3.png");

    public BasicCrystalScreen(BasicCrystalMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void renderBg(PoseStack matrix, float p_97788_, int p_97789_, int p_97790_) {
        var guiLeft = getGuiLeft();
        var guiTop = getGuiTop();
        var width = getXSize();
        var height = getYSize();

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        renderBackground(matrix);
        RenderSystem.setShaderTexture(0, texture);
        blit(matrix, guiLeft, guiTop, 0, 0, width, height);
    }

    @Override
    public void render(PoseStack p_97795_, int p_97796_, int p_97797_, float p_97798_) {
        this.renderBackground(p_97795_);
        super.render(p_97795_, p_97796_, p_97797_, p_97798_);
        this.renderTooltip(p_97795_, p_97796_, p_97797_);
    }

    @Override
    protected void slotClicked(Slot slot, int index, int key, ClickType type) {
        if (slot.index == 36 + BasicCrystalMenu.SIZE * BasicCrystalMenu.SIZE && slot.hasItem()) {
            this.onClose();
        } else {
            super.slotClicked(slot, index, key, type);
        }
    }
}
