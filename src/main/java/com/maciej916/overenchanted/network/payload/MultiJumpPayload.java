package com.maciej916.overenchanted.network.payload;

import com.maciej916.overenchanted.Overenchanted;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record MultiJumpPayload(int multiJump) implements CustomPacketPayload {
    public static final Type<MultiJumpPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "multi_jump_payload"));

    public static final StreamCodec<ByteBuf, MultiJumpPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            MultiJumpPayload::multiJump,
            MultiJumpPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
