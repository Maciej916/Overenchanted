package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.enchantment.effect.LifebinderEnchantmentEffect;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

public class LifebinderEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                EnchantmentRarity.RARE.weight(),
                                3,
                                Enchantment.dynamicCost(10, 20),
                                Enchantment.dynamicCost(40, 70),
                                2,
                                EquipmentSlotGroup.MAINHAND)
                )
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new LifebinderEnchantmentEffect()
                );
    }
}