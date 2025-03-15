package com.maciej916.overenchanted.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public record LifebinderEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LifebinderEnchantmentEffect> CODEC = MapCodec.unit(LifebinderEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        int RANGE = 5;

        if (entity instanceof Enemy) {
            System.out.println("IS ENEMY!");
        }

        List<TamableAnimal> nearbyEntities = serverLevel.getEntitiesOfClass(TamableAnimal.class, entity.getBoundingBox().inflate(RANGE));
        for (TamableAnimal tamableAnimal : nearbyEntities) {
            if (tamableAnimal != entity && tamableAnimal.isTame()) {
                float tamableHealth = tamableAnimal.getHealth();
                float tamableMaxHealth = tamableAnimal.getMaxHealth();

                int random = serverLevel.random.nextInt(100);
                if (random < (10 * enchantmentLevel)) {
                    if (tamableHealth < tamableMaxHealth) {
                        tamableAnimal.heal(2f * enchantmentLevel);
                        spawnHeartParticles(serverLevel, tamableAnimal);
                    }
                }
            }
        }
    }

    private void spawnHeartParticles(ServerLevel serverLevel, TamableAnimal tamableAnimal) {
        for(int i = 0; i < 7; ++i) {
            double d0 = serverLevel.random.nextGaussian() * 0.02;
            double d1 = serverLevel.random.nextGaussian() * 0.02;
            double d2 = serverLevel.random.nextGaussian() * 0.02;
            serverLevel.sendParticles(
                    ParticleTypes.HEART,
                    tamableAnimal.getRandomX(1.0F),
                    tamableAnimal.getRandomY(),
                    tamableAnimal.getRandomZ(1.0F),
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
