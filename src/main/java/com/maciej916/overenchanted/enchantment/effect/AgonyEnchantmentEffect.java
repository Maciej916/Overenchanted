package com.maciej916.overenchanted.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record AgonyEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<AgonyEnchantmentEffect> CODEC = MapCodec.unit(AgonyEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (entity instanceof ServerPlayer player && player.tickCount % 20 == 0 && !player.getAbilities().instabuild && !player.isSpectator()) {
            player.hurtServer(serverLevel, player.damageSources().genericKill(), 0.5F * enchantmentLevel);
            spawnDamageParticles(serverLevel, player);
        }
    }

    private void spawnDamageParticles(ServerLevel serverLevel, ServerPlayer player) {
        for(int i = 0; i < 7; ++i) {
            double d0 = serverLevel.random.nextGaussian() * 0.02;
            double d1 = serverLevel.random.nextGaussian() * 0.02;
            double d2 = serverLevel.random.nextGaussian() * 0.02;
            serverLevel.sendParticles(
                    ParticleTypes.ENCHANTED_HIT,
                    player.getRandomX(1.0F),
                    player.getRandomY(),
                    player.getRandomZ(1.0F),
                    1,
                    d0,
                    d1,
                    d2,
                    0.0
            );
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
