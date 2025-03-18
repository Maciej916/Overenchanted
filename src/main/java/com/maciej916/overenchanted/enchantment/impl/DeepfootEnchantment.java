package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;

public class DeepfootEnchantment {

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.FLEETFOOT_ENCHANTABLE),
                                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                EnchantmentRarity.VERY_RARE.weight(),
                                1,
                                Enchantment.constantCost(40),
                                Enchantment.constantCost(60),
                                4,
                                EquipmentSlotGroup.FEET
                        )
                );
    }

}