package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.Random;

public class ConceptCoreIron extends ConceptCore {

    public ConceptCoreIron(Properties props) {
        super(props);
        this.setUsingCD(300);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return GenericInteractions.place(new BlockPlaceContext(ctx), Blocks.POPPY);
    }

    @Override
    public ArrayList<ItemStack> getMachineOutputs(ItemStack coreStack) {
        ArrayList<ItemStack> list = new ArrayList<>();
        int rand1 = 3 + new Random().nextInt(3);
        list.add(new ItemStack(Items.IRON_INGOT, rand1));
        int rand2 = new Random().nextInt(3);
        if (rand2 > 0)
            list.add(new ItemStack(Blocks.POPPY, rand2));
        return list;
    }
}