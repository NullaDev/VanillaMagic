package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public abstract class ConceptCore extends Item {

    public int UsingCD;

    private static final String TAG_CD = "cd";

    public ConceptCore(Properties props) {
        super(props.stacksTo(1));
        this.setUsingCD(10);
    }

    public ActionResultType wandUseOn(ItemUseContext ctx) {
        return ActionResultType.PASS;
    }

    public ActionResult<ItemStack> wandUse(World world, PlayerEntity player, Hand hand) {
        return ActionResult.pass(player.getItemInHand(hand));
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

    public boolean isUsable(ItemStack stack) {
        return true;
    }

    public boolean canProvideItem(ItemStack stack) {
        return !getMachineOutput(stack).isEmpty();
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (getCD(stack) > 0) {
            setCD(stack, getCD(stack) - 1);
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return getCD(stack) > 0;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1D - 1D * getCD(stack) / UsingCD;
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flags) {
        IFormattableTextComponent total_cd = new TranslationTextComponent("vanillamagic.misc.total_cd", UsingCD);
        IFormattableTextComponent cd_info;
        if (getCD(stack) > 0)
            cd_info = new TranslationTextComponent("vanillamagic.misc.cd2", getCD(stack)).withStyle(TextFormatting.RED);
        else
            cd_info = new TranslationTextComponent("vanillamagic.misc.cd1").withStyle(TextFormatting.GREEN);
        list.add(total_cd);
        list.add(cd_info);
    }

}
