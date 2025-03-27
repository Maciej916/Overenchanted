package com.maciej916.overenchanted.network.handler;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.data.ModDataAttachments;
import com.maciej916.overenchanted.network.payload.LumberjackPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class LumberjackPayloadHandler {

    public static void handleDataOnNetwork(final LumberjackPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();

            if (player.hasData(ModDataAttachments.PLAYER_DATA)) {
                player.getData(ModDataAttachments.PLAYER_DATA).setLumberjackActive(data.isDown());
            }
        })
        .exceptionally(e -> {
            context.disconnect(Component.translatable(Overenchanted.MOD_ID + ".networking.failed", e.getMessage()));
            return null;
        });
    }
}
