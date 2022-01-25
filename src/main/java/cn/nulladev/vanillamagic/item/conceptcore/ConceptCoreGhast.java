package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Random;

public class ConceptCoreGhast extends ConceptCore {

    public ConceptCoreGhast(Properties props) {
        super(props);
        this.setUsingCD(200);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GHAST_SHOOT, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            Vec3 vec3 = player.getViewVector(1F);
            LargeFireball largefireball = new LargeFireball(level, player, vec3.x, vec3.y, vec3.z, 1);
            largefireball.setPos(player.getX() + vec3.x, player.getY(0.5D), player.getZ());
            level.addFreshEntity(largefireball);
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public ArrayList<ItemStack> getMachineOutputs(ItemStack coreStack) {
        ArrayList<ItemStack> list = new ArrayList<>();
        int rand1 = new Random().nextInt(2);
        if (rand1 > 0)
            list.add(new ItemStack(Items.GHAST_TEAR, rand1));
        int rand2 = new Random().nextInt(3);
        if (rand2 > 0)
            list.add(new ItemStack(Items.GUNPOWDER, rand2));
        return list;
    }
}