package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID)
public class EternalEnchantment {

    @SubscribeEvent
    public static void onItemExpire(ItemExpireEvent event) {
        execute(event, event.getEntity());
    }

    private static void execute(ItemExpireEvent event, ItemEntity itemEntity) {
        int lvl = EnchantmentUtil.getEnchantmentLevel(itemEntity.level(), itemEntity.getItem(), ModEnchantments.ETERNAL);
        if (lvl > 0) {
            event.getEntity().setUnlimitedLifetime();
        }
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.ETERNAL_ENCHANTABLE),
                                25,
                                1,
                                Enchantment.dynamicCost(5, 7),
                                Enchantment.dynamicCost(25, 75),
                                2,
                                EquipmentSlotGroup.ANY
                        )
                );
    }
}
