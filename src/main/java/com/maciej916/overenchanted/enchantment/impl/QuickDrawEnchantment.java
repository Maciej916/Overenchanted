package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID)
public class QuickDrawEnchantment {

    @SubscribeEvent
    public static void onLivingEntityUseItem(LivingEntityUseItemEvent.Start event) {
        execute(event, event.getEntity(), event.getItem(), event.getDuration());
    }

    private static void execute(LivingEntityUseItemEvent.Start event, LivingEntity livingEntity, ItemStack itemStack, int duration) {
//        int lvl = EnchantmentUtil.getEnchantmentLevel(livingEntity.level(), itemStack, ModEnchantments.QUICK_DRAW);
//        if (lvl > 0) {
//            float multiplier = 1.0F - (lvl * 0.2F);
//            int newDuration = Math.max(Mth.floor(duration * multiplier), 5);
//            event.setDuration(newDuration);
//        }
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                25,
                                3,
                                Enchantment.dynamicCost(5, 7),
                                Enchantment.dynamicCost(25, 75),
                                2,
                                EquipmentSlotGroup.HAND
                        )
                );
    }
}
