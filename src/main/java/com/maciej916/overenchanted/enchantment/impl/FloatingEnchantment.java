package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.enchantment.effect.FloatingEffect;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

public class FloatingEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                EnchantmentRarity.CUSTOM.setWeight(3).weight(),
                                3,
                                Enchantment.dynamicCost(12, 20),
                                Enchantment.dynamicCost(37, 20),
                                3,
                                EquipmentSlotGroup.HAND
                        )
                )
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.BOW_EXCLUSIVE))
                .withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new FloatingEffect()
                );
    }
}
