package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.enchantment.effect.IncreaseDamageEffect;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;

public class BreakingEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                                5,
                                3,
                                Enchantment.dynamicCost(5, 8),
                                Enchantment.dynamicCost(55, 8),
                                2,
                                EquipmentSlotGroup.ANY
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ITEM_DAMAGE,
                        new IncreaseDamageEffect(new LevelBasedValue.Fraction(LevelBasedValue.perLevel(2.0F), LevelBasedValue.perLevel(10.0F, 5.0F))),
                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(items, ItemTags.ARMOR_ENCHANTABLE))
                )
                .withEffect(
                        EnchantmentEffectComponents.ITEM_DAMAGE,
                        new IncreaseDamageEffect(new LevelBasedValue.Fraction(LevelBasedValue.perLevel(1.0F), LevelBasedValue.perLevel(2.0F, 1.0F))),
                        InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(items, ItemTags.ARMOR_ENCHANTABLE)))
                );
    }
}