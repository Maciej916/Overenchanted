package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.enchantment.effect.AgonyEnchantmentEffect;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;

public class AgonyEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                                EnchantmentRarity.VERY_RARE.weight(),
                                3,
                                Enchantment.dynamicCost(5, 8),
                                Enchantment.dynamicCost(35, 8),
                                8,
                                EquipmentSlotGroup.ARMOR
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.TICK,
                        new AgonyEnchantmentEffect()
                );
    }
}