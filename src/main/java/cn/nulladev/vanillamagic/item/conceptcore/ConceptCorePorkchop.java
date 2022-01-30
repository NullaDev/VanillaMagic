package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Random;

public class ConceptCorePorkchop extends ConceptCore {

    public ConceptCorePorkchop(Properties props) {
        super(props);
        this.setUsingCD(200);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(false)) {
            player.getFoodData().eat(8, 1.6F);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

    @Override
    public ArrayList<ItemStack> getMachineOutputs(ItemStack coreStack) {
        ArrayList<ItemStack> list = new ArrayList<>();
        int rand1 = 2 + new Random().nextInt(3);
        list.add(new ItemStack(Items.COOKED_PORKCHOP, rand1));
        int rand2 = new Random().nextInt(1);
        if (rand2 > 0)
            list.add(new ItemStack(Items.LEATHER, rand2));
        return list;
    }

    @Override
    public boolean canProvideItem(ItemStack stack) {
        return true;
    }
}