package com.maciej916.overenchanted.enchantment.effect;

import com.maciej916.overenchanted.effect.ModEffects;
import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;

public record StunEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<StunEnchantmentEffect> CODEC = MapCodec.unit(StunEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (entity instanceof LivingEntity living) {
            LivingEntity source = enchantedItemInUse.owner();
            if (source instanceof Player player) {
                if (player.fallDistance >= 2.0F) {
                    if (entity.getType().is(Tags.EntityTypes.BOSSES)) return;
                    living.addEffect(new MobEffectInstance(ModEffects.STUN_EFFECT, 30 * enchantmentLevel, 255, false, true), player);
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}