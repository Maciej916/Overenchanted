package com.maciej916.overenchanted.network.payload;

import com.maciej916.overenchanted.Overenchanted;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;

public record ReflectArrowPayload(
        int entityId,
        Vector3f motion

) implements CustomPacketPayload {
    public static final Type<ReflectArrowPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "reflect_arrow"));

    public static final StreamCodec<ByteBuf, ReflectArrowPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            ReflectArrowPayload::entityId,
            ByteBufCodecs.VECTOR3F,
            ReflectArrowPayload::motion,
            ReflectArrowPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
