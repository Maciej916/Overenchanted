package com.maciej916.overenchanted.network.payload;

import com.maciej916.overenchanted.Overenchanted;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record EchoSightPayload(int level) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<EchoSightPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "echo_sight_payload"));

    public static final StreamCodec<ByteBuf, EchoSightPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            EchoSightPayload::level,
            EchoSightPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
