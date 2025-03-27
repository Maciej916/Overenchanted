package com.maciej916.overenchanted.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ParalysisEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ParalysisEffect> CODEC = MapCodec.unit(ParalysisEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;

            livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, enchantmentLevel * 20, 100, false, true));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, enchantmentLevel * 20, 100, false, true));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, enchantmentLevel * 20, 100, false, true));
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}