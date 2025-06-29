package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID)
public class WeightlessEnchantment {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handlePlayerFallEvent(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            if (event.getDistance() > 5 && event.getDistance() < 8) {
                ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
                int lvl = EnchantmentUtil.getEnchantmentLevel(event.getEntity().level(), stack, ModEnchantments.WEIGHTLESS);
                if (lvl > 0) {
                    event.setCanceled(true);
                }
            }
        }
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                EnchantmentRarity.UNCOMMON.weight(),
                                2,
                                Enchantment.dynamicCost(1, 8),
                                Enchantment.dynamicCost(20, 8),
                                2,
                                EquipmentSlotGroup.FEET
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "enchantment.weightless_gravity"),
                                Attributes.GRAVITY,
                                LevelBasedValue.perLevel(-0.02f),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                );
    }
}