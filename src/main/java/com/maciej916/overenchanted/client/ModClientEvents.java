package com.maciej916.overenchanted.client;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.client.impl.EchoSightClickHandler;
import com.maciej916.overenchanted.client.impl.LumberjackClickHandler;
import com.maciej916.overenchanted.client.impl.MultiJumpHandler;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ModClientEvents {
    private static boolean lastLumberjack = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Player player = Minecraft.getInstance().player;
        Options options = Minecraft.getInstance().options;
        if (player == null || !(player.level() instanceof ClientLevel level)) return;

        while (ModKeyMappings.KEY_ECHO_SIGHT.consumeClick()) {
            EchoSightClickHandler.handle();
        }

        boolean currentLumberjack = ModKeyMappings.KEY_LUMBERJACK.isDown();
        if (currentLumberjack != lastLumberjack) {
            LumberjackClickHandler.handle(currentLumberjack);
            lastLumberjack = currentLumberjack;
            ModKeyMappings.KEY_LUMBERJACK.consumeClick();
        }
    }

    @SubscribeEvent
    public static void onClientTick(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        Options options = Minecraft.getInstance().options;
        if (player == null || !(player.level() instanceof ClientLevel level)) return;

        if (event.getAction() == 1 && event.getKey() == options.keyJump.getKey().getValue()) {
            MultiJumpHandler.handle();
        }
    }
}
