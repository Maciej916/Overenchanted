package com.maciej916.overenchanted.network.handler;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.data.impl.ScheduledTask;
import com.maciej916.overenchanted.data.ModDataAttachments;
import com.maciej916.overenchanted.data.impl.PlayerDataAttachment;
import com.maciej916.overenchanted.network.payload.EchoSightPayload;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;

public class EchoSightPayloadHandler {

    public static void handleDataOnNetwork(final EchoSightPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();

            if (player.hasData(ModDataAttachments.PLAYER_DATA)) {
                PlayerDataAttachment playerDataAttachment = player.getData(ModDataAttachments.PLAYER_DATA);

                if (playerDataAttachment.getRevealCountdown() > 0) {
                    player.displayClientMessage(Component.translatable(Overenchanted.MOD_ID + ".echo_sight_countdown", Component.literal("" + playerDataAttachment.getRevealCountdown())).withStyle(ChatFormatting.RED), true);
                } else {
                    ServerLevel serverLevel = (ServerLevel) player.level();
                    int duration = 20 * 4;

                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, duration + 20, 0, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 3, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 100, false, false));

                    serverLevel.playSound(null, player.getOnPos(), SoundEvents.WARDEN_SONIC_CHARGE, SoundSource.PLAYERS, 1.0F, 1.0F);

                    playerDataAttachment.scheduleTask(
                            new ScheduledTask(
                                    serverLevel.getServer().getTickCount() + 20 * 4,
                                    () -> {
                                        spawnRevealParticles(serverLevel, player);
                                        serverLevel.playSound(null, player.getOnPos(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 1.0F, 1.0F);

                                        int RANGE = 15 * data.level();
                                        List<Monster> nearbyEntities = serverLevel.getEntitiesOfClass(Monster.class, player.getBoundingBox().inflate(RANGE));
                                        for (Monster nearbyEntity : nearbyEntities) {
                                            nearbyEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 20 * 10, 100, false, false));
                                        }
                                    }
                            )
                    );

                    playerDataAttachment.setRevealCountdown(30);
                }
            }
        })
        .exceptionally(e -> {
            context.disconnect(Component.translatable(Overenchanted.MOD_ID + ".networking.failed", e.getMessage()));
            return null;
        });
    }

    private static void spawnRevealParticles(ServerLevel serverLevel, Player player) {
        double d0 = serverLevel.random.nextGaussian() * 0.02;
        double d1 = serverLevel.random.nextGaussian() * 0.02;
        double d2 = serverLevel.random.nextGaussian() * 0.02;
        serverLevel.sendParticles(
                ParticleTypes.SONIC_BOOM,
                player.getX(),
                player.getEyeY() - 1,
                player.getZ(),
                1,
                d0,
                d1,
                d2,
                0.0
        );
    }
}
