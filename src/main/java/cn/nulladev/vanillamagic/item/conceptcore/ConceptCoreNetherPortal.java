package cn.nulladev.vanillamagic.item.conceptcore;

import net.minecraft.resources.ResourceKey;
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
        if (level instanceof ServerLevel) {
            ServerLevel serverlevel = (ServerLevel)level;
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = level.dimension() == Level.NETHER ? Level.OVERWORLD : Level.NETHER;
            ServerLevel serverlevel1 = minecraftserver.getLevel(resourcekey);
            if (serverlevel1 != null && minecraftserver.isNetherEnabled() && !player.isPassenger()) {
                level.getProfiler().push("portal");
                player.setPortalCooldown();
                player.changeDimension(serverlevel1);
                level.getProfiler().pop();
                return InteractionResultHolder.success(player.getItemInHand(hand));
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));

    }
}