package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.SetValue;

public class IgnoranceEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.IGNORANCE_ENCHANTABLE),
                                EnchantmentRarity.VERY_RARE.weight(),
                                1,
                                Enchantment.dynamicCost(25, 0),
                                Enchantment.dynamicCost(50, 0),
                                8,
                                EquipmentSlotGroup.ANY
                        )
                )
                .withEffect(EnchantmentEffectComponents.BLOCK_EXPERIENCE, new SetValue(LevelBasedValue.constant(0.0F)))
                .withEffect(EnchantmentEffectComponents.MOB_EXPERIENCE, new SetValue(LevelBasedValue.perLevel(0.0F)));
    }
}