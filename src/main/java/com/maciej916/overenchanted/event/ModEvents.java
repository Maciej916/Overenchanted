package com.maciej916.overenchanted.event;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.capability.ModCapabilities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
    }

    @SubscribeEvent
    public static void onPlayerTickPos(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide) {
            var capability = player.getCapability(ModCapabilities.OVERENCHANTED_PLAYER);
            if (capability != null) {
                ServerLevel level = (ServerLevel) player.level();
                capability.tick(level.getServer().getTickCount());
            }
        }
    }
}
