package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class SonicBoomProtectionEnchantment {

    @SubscribeEvent
    public static void onLivingHurt(LivingDamageEvent.Pre event) {
        execute(event, event.getEntity(), event.getSource());
    }

    private static void execute(LivingDamageEvent.Pre event, LivingEntity entity, DamageSource source) {
        if (source.is(DamageTypes.SONIC_BOOM)) {
            if (entity instanceof Player player) {
                int totalLevel = 0;
                for (ItemStack itemStack : player.getArmorSlots()) {
                    int lvl = EnchantmentUtil.getEnchantmentLevel(player.level(), itemStack, ModEnchantments.SONIC_BOOM_PROTECTION);
                    totalLevel += lvl;
                }

                if (totalLevel > 0) {
                    if (totalLevel >= 16) {
                        event.setNewDamage(0);
                    } else {
                        float reductionFactor = 1 - (totalLevel / 16.0f);
                        float newDamage = event.getOriginalDamage() * reductionFactor;
                        event.setNewDamage(newDamage);
                    }
                }
            }
        }
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.SONIC_BOOM_ENCHANTABLE),
                                EnchantmentRarity.RARE.weight(),
                                4,
                                Enchantment.dynamicCost(6, 9),
                                Enchantment.dynamicCost(14, 9),
                                2,
                                EquipmentSlotGroup.ARMOR
                        )
                );
    }

}