package com.maciej916.overenchanted.network;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.network.handler.EchoSightPayloadHandler;
import com.maciej916.overenchanted.network.handler.LumberjackPayloadHandler;
import com.maciej916.overenchanted.network.handler.MultiJumpPayloadHandler;
import com.maciej916.overenchanted.network.payload.EchoSightPayload;
import com.maciej916.overenchanted.network.payload.LumberjackPayload;
import com.maciej916.overenchanted.network.payload.MultiJumpPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetwork {
    private static final String PROTOCOL_VERSION = "1.0";

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Overenchanted.MOD_ID).versioned(PROTOCOL_VERSION);

        registrar.playToServer(EchoSightPayload.TYPE, EchoSightPayload.STREAM_CODEC, EchoSightPayloadHandler::handleDataOnNetwork);
        registrar.playToServer(LumberjackPayload.TYPE, LumberjackPayload.STREAM_CODEC, LumberjackPayloadHandler::handleDataOnNetwork);
        registrar.playToServer(MultiJumpPayload.TYPE, MultiJumpPayload.STREAM_CODEC, MultiJumpPayloadHandler::handleDataOnNetwork);
    }
}
