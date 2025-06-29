package com.maciej916.overenchanted.event;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.data.ModDataAttachments;
import com.maciej916.overenchanted.data.impl.PlayerDataAttachment;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
    }

    @SubscribeEvent
    public static void onPlayerTickPos(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (!player.level().isClientSide()) {
            if (player.hasData(ModDataAttachments.PLAYER_DATA)) {
                ServerLevel level = (ServerLevel) player.level();
                player.getData(ModDataAttachments.PLAYER_DATA).tick(level.getServer().getTickCount());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        if (!player.hasData(ModDataAttachments.PLAYER_DATA)) {
            player.setData(ModDataAttachments.PLAYER_DATA, new PlayerDataAttachment());
        }
    }
}
