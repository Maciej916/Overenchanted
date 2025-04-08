package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.network.payload.TrueShotArrowPayload;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.WeakHashMap;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class TrueShotEnchantment {
    private static final double MIN_SPEED_FOR_GRAVITY = 0.05;
    private static final WeakHashMap<AbstractArrow, Integer> trueShotArrows = new WeakHashMap<>();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onArrowJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof AbstractArrow abstractArrow) {
            ItemStack weaponItem = abstractArrow.getWeaponItem();
            int lvl = EnchantmentUtil.getEnchantmentLevel(event.getLevel(), weaponItem, ModEnchantments.TRUE_SHOT);
            if (lvl > 0) {
                abstractArrow.setNoGravity(true);
                trueShotArrows.put(abstractArrow, lvl);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelTickEvent(LevelTickEvent.Post event) {
        if (event.getLevel().isClientSide()) return;

        trueShotArrows.entrySet().removeIf(entry -> {
            AbstractArrow arrow = entry.getKey();
            int lvl = entry.getValue();
            if (arrow.isRemoved()) return true;

            Vec3 velocity = arrow.getDeltaMovement();
            double speed = velocity.length();

            double damping;
            if (lvl == 1) {
                damping = 0.94;
            } else {
                damping = 0.98;
            }

            arrow.setDeltaMovement(velocity.scale(damping));

            if (speed < MIN_SPEED_FOR_GRAVITY) {
                arrow.setNoGravity(false);
                return true;
            }

            PacketDistributor.sendToPlayersTrackingEntityAndSelf(arrow, new TrueShotArrowPayload(arrow.getId(), arrow.getDeltaMovement().toVector3f(), arrow.isNoGravity()));
            return false;
        });
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                EnchantmentRarity.COMMON.weight(),
                                2,
                                Enchantment.dynamicCost(15, 9),
                                Enchantment.dynamicCost(65, 9),
                                1,
                                EquipmentSlotGroup.HAND
                        )
                )
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.BOW_EXCLUSIVE));
    }
}
