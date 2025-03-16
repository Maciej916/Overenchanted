package com.maciej916.overenchanted.network.payload;

import com.maciej916.overenchanted.Overenchanted;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record LumberjackPayload(boolean isDown) implements CustomPacketPayload {
    public static final Type<LumberjackPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "lumberjack_payload"));

    public static final StreamCodec<ByteBuf, LumberjackPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            LumberjackPayload::isDown,
            LumberjackPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
