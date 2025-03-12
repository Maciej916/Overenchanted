package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ReinforcedTipEnchantment {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBlockBreak(PlayerEvent.BreakSpeed event) {
        execute(event, event.getOriginalSpeed(), event.getEntity(), event.getState());
    }

    private static void execute(PlayerEvent.BreakSpeed event, float originalSpeed, Player player,  BlockState blockState) {
        if (blockState.is(ModTags.Blocks.REINFORCED_TIP_BLOCKS)) {
            ItemStack stack = player.getItemInHand(player.getUsedItemHand());
            if (!stack.isDamageableItem()) return;

            int lvl = EnchantmentUtil.getEnchantmentLevel(player.level(), stack, ModEnchantments.REINFORCED_TIP);
            if (lvl > 0) {
                event.setNewSpeed(originalSpeed * lvl + 1);
            }
        }
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                        EnchantmentRarity.RARE.weight(),
                        3,
                        Enchantment.dynamicCost(15, 9),
                        Enchantment.dynamicCost(65, 9),
                        4,
                        EquipmentSlotGroup.HAND
                )
        );
    }
}

