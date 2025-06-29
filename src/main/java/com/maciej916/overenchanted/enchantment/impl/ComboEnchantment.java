package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.data.ModDataComponents;
import com.maciej916.overenchanted.enchantment.effect.ComboEnchantmentEffect;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID)
public class ComboEnchantment {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onItemTooltipEvent(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.has(ModDataComponents.COMBO)) {
            int comboLevel = stack.getOrDefault(ModDataComponents.COMBO, 0);
            ChatFormatting color = comboLevel < 50 ? ChatFormatting.RED : comboLevel < 99 ? ChatFormatting.YELLOW : ChatFormatting.GREEN;
            event.getToolTip().add(Component.translatable("overenchanted.combo_lvl", Component.literal(comboLevel + "/100").withStyle(color)).withStyle(ChatFormatting.GRAY));
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        ItemStack stack = event.getItemStack();
        if (stack.has(ModDataComponents.COMBO)) {
            stack.set(ModDataComponents.COMBO, 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack stack = event.getItemStack();
        if (stack.has(ModDataComponents.COMBO)) {
            stack.set(ModDataComponents.COMBO, 0);
        }
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);
        var entities = context.lookup(Registries.ENTITY_TYPE);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                EnchantmentRarity.VERY_RARE.weight(),
                                1,
                                Enchantment.constantCost(15),
                                Enchantment.constantCost(65),
                                2,
                                EquipmentSlotGroup.HAND
                        )
                )
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new ComboEnchantmentEffect()
                );
    }
}