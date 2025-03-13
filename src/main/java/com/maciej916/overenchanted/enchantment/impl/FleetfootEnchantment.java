package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

public class FleetfootEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.FLEETFOOT_ENCHANTABLE),
                                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                EnchantmentRarity.RARE.weight(),
                                3,
                                Enchantment.dynamicCost(2, 8),
                                Enchantment.dynamicCost(24, 8),
                                2,
                                EquipmentSlotGroup.FEET
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "enchantment.fleetfoot_movement_speed"),
                                Attributes.MOVEMENT_SPEED,
                                LevelBasedValue.perLevel( 0.15f, 0.15f),
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "enchantment.fleetfoot_knockback_resistance"),
                                Attributes.KNOCKBACK_RESISTANCE,
                                LevelBasedValue.perLevel(-0.2f, -0.2f),
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                );
    }
}
