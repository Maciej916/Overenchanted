package com.maciej916.overenchanted.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record IncreaseDamageEffect(LevelBasedValue chance) implements EnchantmentValueEffect {
    public static final MapCodec<IncreaseDamageEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(LevelBasedValue.CODEC.fieldOf("chance").forGetter(IncreaseDamageEffect::chance)).apply(instance, IncreaseDamageEffect::new)
    );

    @Override
    public float process(int level, RandomSource random, float damage) {
        float f = this.chance.calculate(level);
        int increasedDamage = 0;

        for (int j = 0; j < damage; j++) {
            if (random.nextFloat() < f) {
                increasedDamage++;
            }
        }

        return damage + (float) increasedDamage;
    }

    @Override
    public MapCodec<IncreaseDamageEffect> codec() {
        return CODEC;
    }
}