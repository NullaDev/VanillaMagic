package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class ConceptCoreStone extends ConceptCore {

    public ConceptCoreStone(Properties props) {
        super(props);
        this.setUsingCD(30);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return GenericInteractions.place(new BlockPlaceContext(ctx), Blocks.STONE);
    }

    @Override
    public ItemStack getMachineOutput(ItemStack coreStack) {
        return new ItemStack(Blocks.STONE);
    }


}