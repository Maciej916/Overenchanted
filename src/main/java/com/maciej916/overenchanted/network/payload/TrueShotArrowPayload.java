package com.maciej916.overenchanted.network.payload;

import com.maciej916.overenchanted.Overenchanted;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;

public record TrueShotArrowPayload(
        int entityId,
        Vector3f motion,
        boolean noGravity

) implements CustomPacketPayload {
    public static final Type<TrueShotArrowPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "true_shot_arrow"));

    public static final StreamCodec<ByteBuf, TrueShotArrowPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            TrueShotArrowPayload::entityId,
            ByteBufCodecs.VECTOR3F,
            TrueShotArrowPayload::motion,
            ByteBufCodecs.BOOL,
            TrueShotArrowPayload::noGravity,
            TrueShotArrowPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
