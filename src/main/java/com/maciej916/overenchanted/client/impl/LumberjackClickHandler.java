package com.maciej916.overenchanted.client.impl;

import com.maciej916.overenchanted.network.payload.LumberjackPayload;
import net.neoforged.neoforge.network.PacketDistributor;

public class LumberjackClickHandler {

    public static void handle (boolean isDown) {
        PacketDistributor.sendToServer(new LumberjackPayload(isDown));
    }
}
