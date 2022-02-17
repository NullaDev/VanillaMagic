package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Random;

public class ConceptCoreMobGrinder extends ConceptCore {

    public ConceptCoreMobGrinder(Properties props) {
        super(props);
        this.setUsingCD(100);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(false)) {
            player.getFoodData().eat(4, 0.2F);
            double rand = new Random().nextDouble();
            if (rand > 0.8D)
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0));
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

    @Override
    public ArrayList<ItemStack> getMachineOutputs(ItemStack coreStack) {
        ArrayList<ItemStack> list = new ArrayList<>();
        int rand = new Random().nextInt(4);
        if (rand == 0) {
            int rand2 = 2 + new Random().nextInt(3);
            int total = 0;
            for (int i = 0; i < rand2; i++) {
                total += new Random().nextInt(3);
            }
            if (total > 0)
                list.add(new ItemStack(Items.ROTTEN_FLESH, total));
        } else if (rand == 1) {
            int rand2 = new Random().nextInt(3);
            if (rand2 > 0)
                list.add(new ItemStack(Items.GUNPOWDER, rand2));
        } else if (rand == 2) {
            int rand2 = new Random().nextInt(3);
            if (rand2 > 0)
                list.add(new ItemStack(Items.STRING, rand2));
        } else {
            int rand2 = 1 + new Random().nextInt(2);
            int total1 = 0;
            int total2 = 0;
            for (int i = 0; i < rand2; i++) {
                total1 += new Random().nextInt(3);
                total2 += new Random().nextInt(3);
            }
            if (total1 > 0)
                list.add(new ItemStack(Items.BONE, total1));
            if (total2 > 0)
                list.add(new ItemStack(Items.ARROW, total2));
        }
        return list;
    }

    @Override
    public boolean canProvideItem(ItemStack stack) {
        return true;
    }
}
