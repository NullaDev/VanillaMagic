package cn.nulladev.vanillamagic.item;

import cn.nulladev.vanillamagic.item.conceptcore.ConceptCore;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class WorldInteractionWand extends Item {

    private static final String TAG_CORE = "core";

    public WorldInteractionWand(Properties props) {
        super(props.stacksTo(1));
    }

    public static void setCore(ItemStack stack, ItemStack core) {
        CompoundNBT tag = new CompoundNBT();
        core.save(tag);
        stack.getOrCreateTag().put(TAG_CORE, tag);
    }

    public static ItemStack getCore(ItemStack stack) {
        if (stack.getOrCreateTag().contains(TAG_CORE)) {
            return ItemStack.of(stack.getOrCreateTag().getCompound(TAG_CORE));
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static boolean hasCore(ItemStack stack) {
        return !getCore(stack).isEmpty();
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (getCD(stack) > 0) {
            setCD(stack, getCD(stack) - 1);
        }
    }

    private static int getCD(ItemStack stack) {
        ItemStack core = getCore(stack);
        if (!core.isEmpty())
            return ConceptCore.getCD(core);
        else
            return 0;
    }

    private static void setCD(ItemStack stack, int cd) {
        ItemStack core = getCore(stack);
        if (!core.isEmpty())
            ConceptCore.setCD(core, cd);
    }

    private static int getMaxCD(ItemStack stack) {
        ItemStack core = getCore(stack);
        if (core.getItem() instanceof ConceptCore)
            return ((ConceptCore) core.getItem()).UsingCD;
        else
            return 0;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return getCD(stack) > 0;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1D - 1D * getCD(stack) / getMaxCD(stack);
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        if (hasCore(stack)) {
            IFormattableTextComponent component = super.getName(stack).plainCopy();
            component.append("(");
            component.append(getCore(stack).getDisplayName().plainCopy().withStyle(TextFormatting.GREEN));
            component.append(")");
            return component;
        } else {
            return super.getName(stack);
        }
    }

    @Override
    public ActionResultType useOn(ItemUseContext ctx) {
        if (getCD(ctx.getItemInHand()) > 0 && ctx.getPlayer() != null && !ctx.getPlayer().isCreative())
            return ActionResultType.PASS;

        if (getCore(ctx.getItemInHand()).getItem() instanceof ConceptCore item) {
            ActionResultType result = item.wandUseOn(ctx);
            if (result != ActionResultType.PASS)
                setCD(ctx.getItemInHand(), item.UsingCD);
            return result;
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (getCD(stack) > 0 && !player.isCreative())
            return ActionResult.pass(stack);

        if (getCore(stack).getItem() instanceof ConceptCore item) {
            ActionResult<ItemStack> result = item.wandUse(world, player, hand);
            if (result.getResult() != ActionResultType.PASS) {
                setCD(player.getItemInHand(hand), item.UsingCD);
                return ActionResult.success(stack);
            }
        }
        return ActionResult.pass(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flags) {
        IFormattableTextComponent cd_info;
        if (!hasCore(stack))
            cd_info = new TranslationTextComponent("vanillamagic.misc.miss_core").withStyle(TextFormatting.RED);
        else if (getCD(stack) > 0)
            cd_info = new TranslationTextComponent("vanillamagic.misc.cd2", getCD(stack)).withStyle(TextFormatting.RED);
        else
            cd_info = new TranslationTextComponent("vanillamagic.misc.cd1").withStyle(TextFormatting.GREEN);
        list.add(cd_info);
    }
}
