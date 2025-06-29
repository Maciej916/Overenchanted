package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.data.ModDataComponents;
import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Overenchanted.MOD_ID)
public class MultiJumpEnchantment {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handlerPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.onGround()) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
            if (stack.has(ModDataComponents.MULTI_JUMP)) {
                if (stack.getOrDefault(ModDataComponents.MULTI_JUMP, 0) != 0) {
                    stack.set(ModDataComponents.MULTI_JUMP, 0);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handlePlayerFallEvent(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            if (event.getDistance() > 3 && event.getDistance() < 6) {
                ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
                if (stack.has(ModDataComponents.MULTI_JUMP)) {
                    event.setCanceled(true);
                    if (entity instanceof ServerPlayer serverPlayer) {
                        spawnCloudParticles((ServerLevel) serverPlayer.level(), serverPlayer);
                    }
                }
            }
        }
    }

    private static void spawnCloudParticles(ServerLevel serverLevel, Player player) {
        for(int i = 0; i < 5; ++i) {
            double d0 = serverLevel.random.nextGaussian() * 0.2;
            double d1 = serverLevel.random.nextGaussian() * 0.2;
            double d2 = serverLevel.random.nextGaussian() * 0.2;
            serverLevel.sendParticles(
                    ParticleTypes.CLOUD,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    1,
                    d0,
                    d1,
                    d2,
                    0.0
            );
        }
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.FLEETFOOT_ENCHANTABLE),
                                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                                EnchantmentRarity.VERY_RARE.weight(),
                                2,
                                Enchantment.constantCost(20),
                                Enchantment.constantCost(50),
                                4,
                                EquipmentSlotGroup.FEET
                        )
                );
    }
}