package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ConceptCorePorkchop extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCorePorkchop(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 200;
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
}
