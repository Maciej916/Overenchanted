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

public class FreeRidingEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.FREE_RIDING_ENCHANTABLE),
                                EnchantmentRarity.VERY_RARE.weight(),
                                1,
                                Enchantment.dynamicCost(5, 7),
                                Enchantment.dynamicCost(25, 75),
                                4,
                                EquipmentSlotGroup.HAND
                        )
                )
                .exclusiveWith(enchantments.getOrThrow(ModTags.Enchantments.FREE_RIDING_EXCLUSIVE))
                .withEffect(
                        EnchantmentEffectComponents.ITEM_DAMAGE,
                        new SetValue(LevelBasedValue.constant(0F))
                );
    }
}
