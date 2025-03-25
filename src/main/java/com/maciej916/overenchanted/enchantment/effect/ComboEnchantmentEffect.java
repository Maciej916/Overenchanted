package com.maciej916.overenchanted.enchantment.effect;

import com.maciej916.overenchanted.data.ModDataComponents;
import com.maciej916.overenchanted.particle.ModParticles;
import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ComboEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ComboEnchantmentEffect> CODEC = MapCodec.unit(ComboEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        ItemStack stack = enchantedItemInUse.itemStack();
        if (stack.has(ModDataComponents.COMBO)) {
            LivingEntity ownerEntity = enchantedItemInUse.owner();

            if (ownerEntity != null) {
                int combo = stack.getOrDefault(ModDataComponents.COMBO, 0);

                if (ownerEntity instanceof Player player) {
                    entity.hurt(entity.damageSources().playerAttack(player), combo * .5f);
                } else {
                    entity.hurt(entity.damageSources().mobAttack(ownerEntity), combo * .5f);
                }

                spawnComboParticles((ServerLevel) entity.level(), entity);

                if (combo < 100) {
                    stack.set(ModDataComponents.COMBO, combo + 1);
                }
            }
        } else {
            stack.set(ModDataComponents.COMBO, 1);
        }
    }

    private void spawnComboParticles(ServerLevel serverLevel, Entity entity) {
        for(int i = 0; i < 4; ++i) {
            double d0 = serverLevel.random.nextGaussian() * 0.02;
            double d1 = serverLevel.random.nextGaussian() * 0.02;
            double d2 = serverLevel.random.nextGaussian() * 0.02;
            serverLevel.sendParticles(
                    ModParticles.COMBO_PARTICLES.get(),
                    entity.getRandomX(1.0F),
                    entity.getRandomY(),
                    entity.getRandomZ(1.0F),
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