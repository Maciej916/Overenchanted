package com.maciej916.overenchanted.network.handler;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.data.ModDataComponents;
import com.maciej916.overenchanted.network.payload.MultiJumpPayload;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class MultiJumpPayloadHandler {

    public static void handleDataOnNetwork(final MultiJumpPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            ServerLevel serverLevel = (ServerLevel) player.level();

            player.jumpFromGround();

            ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
            stack.set(ModDataComponents.MULTI_JUMP, data.multiJump());

            spawnCloudParticles(serverLevel, player);
        })
        .exceptionally(e -> {
            context.disconnect(Component.translatable(Overenchanted.MOD_ID + ".networking.failed", e.getMessage()));
            return null;
        });
    }

    private static void spawnCloudParticles(ServerLevel serverLevel, Player player) {
        for(int i = 0; i < 5; ++i) {
            double d0 = serverLevel.random.nextGaussian() * 0.2;
            double d1 = serverLevel.random.nextGaussian() * 0.2;
            double d2 = serverLevel.random.nextGaussian() * 0.2;
            serverLevel.sendParticles(
                    ParticleTypes.CLOUD,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    1,
                    d0,
                    d1,
                    d2,
                    0.0
            );
        }
    }
}
