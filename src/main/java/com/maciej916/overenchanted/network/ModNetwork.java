package com.maciej916.overenchanted.network;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.network.handler.*;
import com.maciej916.overenchanted.network.payload.*;
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

        registrar.playToClient(RicochetArrowPayload.TYPE, RicochetArrowPayload.STREAM_CODEC, RicochetArrowPayloadHandler::handleDataOnNetwork);
        registrar.playToClient(ReflectArrowPayload.TYPE, ReflectArrowPayload.STREAM_CODEC, ReflectArrowPayloadHandler::handleDataOnNetwork);
    }
}
