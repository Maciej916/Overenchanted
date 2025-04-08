package com.maciej916.overenchanted.network.handler;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.network.payload.TrueShotArrowPayload;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class TrueShotArrowPayloadHandler {

    public static void handleDataOnNetwork(final TrueShotArrowPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            Entity entity = player.level().getEntity(data.entityId());
            if (entity instanceof AbstractArrow abstractArrow) {
                abstractArrow.setDeltaMovement(new Vec3(data.motion()));
                abstractArrow.setNoGravity(data.noGravity());
                spawnParticles(abstractArrow, player.level());
            }
        })
        .exceptionally(e -> {
            context.disconnect(Component.translatable(Overenchanted.MOD_ID + ".networking.failed", e.getMessage()));
            return null;
        });
    }

    private static void spawnParticles(AbstractArrow arrow, LevelAccessor level) {
        level.addParticle(ParticleTypes.ENCHANT,
                arrow.getX(),
                arrow.getY(),
                arrow.getZ(),
                0, 0.01, 0
        );
    }
}
