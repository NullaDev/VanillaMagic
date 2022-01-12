package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class ConceptCore extends Item {

    public int UsingCD;

    private static final String TAG_CD = "cd";

    public ConceptCore(Properties props) {
        super(props.stacksTo(1));
        this.setUsingCD(10);
    }

    public InteractionResult wandUseOn(UseOnContext ctx) {
        return InteractionResult.PASS;
    }

    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    public void setUsingCD(int CD) {
        this.UsingCD = CD;
    }

    public static int getCD(ItemStack stack) {
        return stack.getOrCreateTag().getInt(TAG_CD);
    }

    public static void setCD(ItemStack stack, int cd) {
        stack.getOrCreateTag().putInt(TAG_CD, cd);
    }

    public ItemStack getMachineOutput(ItemStack coreStack) {
        return ItemStack.EMPTY;
    }

    public ArrayList<ItemStack> getMachineOutputs(ItemStack coreStack) {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(this.getMachineOutput(coreStack));
        return list;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (getCD(stack) > 0) {
            setCD(stack, getCD(stack) - 1);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getCD(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13F - 13F * getCD(stack) / UsingCD);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float f = Math.max(0.0F, (this.UsingCD - getCD(stack)) / (float)UsingCD);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        Component total_cd = new TranslatableComponent("vanillamagic.misc.total_cd", UsingCD);
        Component cd_info;
        if (getCD(stack) > 0)
            cd_info = new TranslatableComponent("vanillamagic.misc.cd2", getCD(stack)).withStyle(ChatFormatting.RED);
        else
            cd_info = new TranslatableComponent("vanillamagic.misc.cd1").withStyle(ChatFormatting.GREEN);
        list.add(total_cd);
        list.add(cd_info);
    }

}
