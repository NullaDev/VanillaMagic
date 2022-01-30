package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.Random;

public class ConceptCoreTree extends ConceptCore {

    public ConceptCoreTree(Properties props) {
        super(props);
        this.setUsingCD(20);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return GenericInteractions.place(new BlockPlaceContext(ctx), Blocks.OAK_LOG);
    }

    @Override
    public ArrayList<ItemStack> getMachineOutputs(ItemStack coreStack) {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(Blocks.OAK_LOG));
        Random ran = new Random();
        double rand = ran.nextDouble();
        if (rand > 0.5D)
            list.add(new ItemStack(Blocks.AZALEA));
        double rand2 = ran.nextDouble();
        if (rand2 > 0.875D)
            list.add(new ItemStack(Blocks.FLOWERING_AZALEA));
        double rand3 = ran.nextDouble();
        if (rand3 > 0.75D)
            list.add(new ItemStack(Items.WHEAT_SEEDS));
        double rand4 = ran.nextDouble();
        if (rand4 > 0.25D)
            list.add(new ItemStack(Blocks.MOSS_CARPET));
        return list;
    }

    @Override
    public boolean canProvideItem(ItemStack stack) {
        return true;
    }
}