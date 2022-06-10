package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ConceptCoreNetherPortal extends ConceptCore {

    public ConceptCoreNetherPortal(Properties props) {
        super(props);
        this.setUsingCD(300);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel serverlevel) {
            MinecraftServer server = serverlevel.getServer();
            ServerLevel overworld = server.getLevel(Level.OVERWORLD);
            ServerLevel nether = server.getLevel(Level.NETHER);
            if (!player.isPassenger() && !player.isOnPortalCooldown()) {
                if (level == overworld) {
                    player.changeDimension(nether);
                    player.setPortalCooldown();
                    return InteractionResultHolder.success(player.getItemInHand(hand));
                } else if (level == nether) {
                    player.changeDimension(overworld);
                    player.setPortalCooldown();
                    return InteractionResultHolder.success(player.getItemInHand(hand));
                }
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}