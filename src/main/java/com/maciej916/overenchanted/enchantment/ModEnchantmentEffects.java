package com.maciej916.overenchanted.enchantment;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.enchantment.effect.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentLocationBasedEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Overenchanted.MOD_ID);
    public static final DeferredRegister<MapCodec<? extends LevelBasedValue>> LEVEL_ENCHANTMENT_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_LEVEL_BASED_VALUE_TYPE, Overenchanted.MOD_ID);
    public static final DeferredRegister<MapCodec<? extends EnchantmentLocationBasedEffect>> LOCATION_ENCHANTMENT_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_LOCATION_BASED_EFFECT_TYPE, Overenchanted.MOD_ID);
    public static final DeferredRegister<MapCodec<? extends EnchantmentValueEffect>> VALUE_ENCHANTMENT_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_VALUE_EFFECT_TYPE, Overenchanted.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BLAZING_EDGE = ENTITY_ENCHANTMENT_EFFECTS.register("blazing_edge", () -> BlazingEdgeEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> FLOATING = ENTITY_ENCHANTMENT_EFFECTS.register("floating", () -> FloatingEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> DETONATION = ENTITY_ENCHANTMENT_EFFECTS.register("detonation", () -> DetonationEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> PARALYSIS = ENTITY_ENCHANTMENT_EFFECTS.register("paralysis", () -> ParalysisEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> STUN = ENTITY_ENCHANTMENT_EFFECTS.register("stun", () -> StunEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LIFEBINDER = ENTITY_ENCHANTMENT_EFFECTS.register("lifebinder", () -> LifebinderEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> AGONY = ENTITY_ENCHANTMENT_EFFECTS.register("agony", () -> AgonyEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> AQUA_GLIDE = ENTITY_ENCHANTMENT_EFFECTS.register("aqua_glide", () -> AquaGlideEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentValueEffect>> INCREASE_DAMAGE = VALUE_ENCHANTMENT_EFFECTS.register("increase_damage", () -> IncreaseDamageEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
        VALUE_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}