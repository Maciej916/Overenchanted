package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;

public class EchoSightEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.ECHO_SIGHT_ENCHANTABLE),
                                EnchantmentRarity.VERY_RARE.weight(),
                                3,
                                Enchantment.dynamicCost(30, 2),
                                Enchantment.dynamicCost(60, 2),
                                2,
                                EquipmentSlotGroup.ARMOR
                        )
                );
    }
}