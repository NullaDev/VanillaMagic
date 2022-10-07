package cn.nulladev.sheathmagic.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ConceptCoreGlowBerries extends BaseConceptCore implements IConceptCoreWand{
    public ConceptCoreGlowBerries(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 86400;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(false)) {
            player.getFoodData().eat(2, 0.2F);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }
}
