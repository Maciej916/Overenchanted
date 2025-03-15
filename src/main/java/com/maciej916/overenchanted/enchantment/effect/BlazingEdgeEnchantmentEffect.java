package com.maciej916.overenchanted.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import static com.maciej916.overenchanted.util.EntityUtil.hasLineOfSight;

public record BlazingEdgeEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<BlazingEdgeEnchantmentEffect> CODEC = MapCodec.unit(BlazingEdgeEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        int RANGE = 3;
        List<LivingEntity> nearbyEntities = serverLevel.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(RANGE));
        for (LivingEntity nearbyEntity : nearbyEntities) {
            if (!(nearbyEntity instanceof Monster)) continue;
            if (!hasLineOfSight(entity, nearbyEntity, serverLevel)) continue;

            int random = serverLevel.random.nextInt(100);
            if (random < (10 * enchantmentLevel)) {
                igniteEntity(nearbyEntity, enchantmentLevel);
            }
        }
    }

    private void igniteEntity(LivingEntity entity, int enchantmentLevel) {
        entity.igniteForSeconds(enchantmentLevel);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}