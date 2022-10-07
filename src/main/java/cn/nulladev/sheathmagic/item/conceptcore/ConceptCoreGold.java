package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ConceptCoreGold extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCoreGold(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(false)) {
            double rand = new Random().nextDouble();
            if (rand > 0.5D)
                return InteractionResultHolder.success(player.getItemInHand(hand));
            player.getFoodData().eat(4, 0.2F);
            double rand2 = new Random().nextDouble();
            if (rand2 > 0.8D)
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0));
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }
}
