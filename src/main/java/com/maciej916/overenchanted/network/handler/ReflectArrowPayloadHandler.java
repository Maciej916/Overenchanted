package com.maciej916.overenchanted.network.handler;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.network.payload.ReflectArrowPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ReflectArrowPayloadHandler {

    public static void handleDataOnNetwork(final ReflectArrowPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            Entity entity = player.level().getEntity(data.entityId());
            if (entity instanceof AbstractArrow abstractArrow) {
                abstractArrow.setDeltaMovement(new Vec3(data.motion()));
            }
        })
        .exceptionally(e -> {
            context.disconnect(Component.translatable(Overenchanted.MOD_ID + ".networking.failed", e.getMessage()));
            return null;
        });
    }
}
