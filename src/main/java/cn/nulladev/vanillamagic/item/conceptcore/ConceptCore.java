package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ConceptCore extends Item {

    public int UsingCD;

    private static final String TAG_CD = "cd";

    public ConceptCore(Properties props) {
        super(props.stacksTo(1));
        this.setUsingCD(10);
    }

    public abstract InteractionResult wandUse(UseOnContext ctx);

    public void setUsingCD(int CD) {
        this.UsingCD = CD;
    }

    public static int getCD(ItemStack stack) {
        return stack.getOrCreateTag().getInt(TAG_CD);
    }

    public static void setCD(ItemStack stack, int cd) {
        stack.getOrCreateTag().putInt(TAG_CD, cd);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (getCD(stack) > 0) {
            setCD(stack, getCD(stack) - 1);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        Component cdinfo;
        if (getCD(stack) > 0)
            cdinfo = new TranslatableComponent("vanillamagic.misc.cd2", getCD(stack));
        else
            cdinfo = new TranslatableComponent("vanillamagic.misc.cd1");
        list.add(cdinfo);
    }

}
