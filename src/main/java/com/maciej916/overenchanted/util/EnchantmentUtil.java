package com.maciej916.overenchanted.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.LevelAccessor;

public class EnchantmentUtil {

    public static int getEnchantmentLevel(LevelAccessor level, ItemStack itemStack, ResourceKey<Enchantment> enchantment) {
        try {
            return itemStack.getEnchantmentLevel(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment));
        } catch (Exception e) {
            return 0;
        }
    }

}
