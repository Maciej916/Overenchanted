package com.maciej916.overenchanted.network.handler;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.capability.ModCapabilities;
import com.maciej916.overenchanted.capability.impl.IRicochetArrowCapability;
import com.maciej916.overenchanted.network.payload.RicochetArrowPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class RicochetArrowPayloadHandler {

    public static void handleDataOnNetwork(final RicochetArrowPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            Entity entity = player.level().getEntity(data.entityId());
            if (entity instanceof AbstractArrow abstractArrow) {
                abstractArrow.setDeltaMovement(new Vec3(data.motion()));
                abstractArrow.setYRot(data.yRot());
                abstractArrow.setXRot(data.xRot());

                IRicochetArrowCapability capability = abstractArrow.getCapability(ModCapabilities.RICOCHET_ARROW);
                if (capability != null) {
                    capability.setMotion(new Vec3(data.motion()));
                    capability.setYRot(data.yRot());
                    capability.setXRot(data.xRot());
                    capability.setBouncesLeft(data.bouncesLeft());
                }
            }
        })
        .exceptionally(e -> {
            context.disconnect(Component.translatable(Overenchanted.MOD_ID + ".networking.failed", e.getMessage()));
            return null;
        });
    }
}
