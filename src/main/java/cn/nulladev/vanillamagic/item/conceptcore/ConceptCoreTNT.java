package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ConceptCoreTNT extends ConceptCore {

    public ConceptCoreTNT(Properties props) {
        super(props);
        this.setUsingCD(5);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        double x = player.getX();
        double y = player.getY() + 0.5D;
        double z = player.getZ();
        PrimedTnt primedtnt = new PrimedTnt(level, x, y, z, null);
        level.addFreshEntity(primedtnt);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

}