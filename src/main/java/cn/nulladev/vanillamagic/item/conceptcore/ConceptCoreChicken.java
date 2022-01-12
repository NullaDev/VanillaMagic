package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ConceptCoreChicken extends ConceptCore {

    public ConceptCoreChicken(Properties props) {
        super(props);
        this.setUsingCD(400);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            ThrownEgg thrownegg = new ThrownEgg(level, player);
            thrownegg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(thrownegg);
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public ItemStack getMachineOutput(ItemStack coreStack) {
        return new ItemStack(Items.EGG);
    }
}