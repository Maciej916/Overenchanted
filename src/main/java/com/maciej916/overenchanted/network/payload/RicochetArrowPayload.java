package com.maciej916.overenchanted.network.payload;

import com.maciej916.overenchanted.Overenchanted;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;

public record RicochetArrowPayload(
        int entityId,
        Vector3f motion,
        float yRot,
        float xRot,
        int bouncesLeft

) implements CustomPacketPayload {
    public static final Type<RicochetArrowPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "ricochet_arrow"));

    public static final StreamCodec<ByteBuf, RicochetArrowPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            RicochetArrowPayload::entityId,
            ByteBufCodecs.VECTOR3F,
            RicochetArrowPayload::motion,
            ByteBufCodecs.FLOAT,
            RicochetArrowPayload::yRot,
            ByteBufCodecs.FLOAT,
            RicochetArrowPayload::xRot,
            ByteBufCodecs.VAR_INT,
            RicochetArrowPayload::bouncesLeft,
            RicochetArrowPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
