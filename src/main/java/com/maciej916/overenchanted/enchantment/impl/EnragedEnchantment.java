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

public class EnragedEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.ENRAGED_ENCHANTABLE),
                                items.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                                EnchantmentRarity.VERY_RARE.weight(),
                                3,
                                Enchantment.dynamicCost(1, 8),
                                Enchantment.dynamicCost(21, 8),
                                2,
                                EquipmentSlotGroup.CHEST
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "enchantment.enraged_attack_damage"),
                                Attributes.ATTACK_DAMAGE,
                                LevelBasedValue.perLevel(0.3f, 0.3f),
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "enchantment.enraged_attack_armor"),
                                Attributes.ARMOR,
                                LevelBasedValue.perLevel(-0.2f, -0.2f),
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                );
    }
}
