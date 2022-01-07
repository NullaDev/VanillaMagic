package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Random;

public class ConceptCoreChickenRoast extends ConceptCore {

    public ConceptCoreChickenRoast(Properties props) {
        super(props);
        this.setUsingCD(400);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(false)) {
            player.getFoodData().eat(6, 1.2F);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

    @Override
    public ArrayList<ItemStack> getMachineOutputs() {
        ArrayList<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(Items.COOKED_CHICKEN));
        int rand = new Random().nextInt(3);
        if (rand > 0)
            list.add(new ItemStack(Items.FEATHER, rand));
        return list;
    }
}