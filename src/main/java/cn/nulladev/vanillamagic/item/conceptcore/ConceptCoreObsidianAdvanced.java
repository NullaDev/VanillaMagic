package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.Random;

public class ConceptCoreObsidianAdvanced extends ConceptCore {

    public ConceptCoreObsidianAdvanced(Properties props) {
        super(props);
        this.setUsingCD(5);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return GenericInteractions.place(new BlockPlaceContext(ctx), Blocks.OBSIDIAN);
    }

    @Override
    public ArrayList<ItemStack> getMachineOutputs(ItemStack coreStack) {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(Blocks.OBSIDIAN));
        Random ran = new Random();
        double rand = ran.nextDouble();
        if (rand > 0.5D)
            list.add(new ItemStack(Blocks.COBBLESTONE));
        return list;
    }

    @Override
    public boolean canProvideItem(ItemStack stack) {
        return true;
    }
}