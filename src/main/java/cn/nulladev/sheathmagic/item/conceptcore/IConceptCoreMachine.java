package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.ArrayList;

public interface IConceptCoreMachine extends IConceptCore {

    ItemStack getMachineOutput();

    ArrayList<ItemStack> getMachineOutputList();

    @Nullable
    Block getMachineGenerate();
}
