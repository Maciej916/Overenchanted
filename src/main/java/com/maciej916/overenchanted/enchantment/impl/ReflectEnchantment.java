package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.network.payload.ReflectArrowPayload;
import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ReflectEnchantment {
    private static final double REFLECT_SCALE_SPEED = 5.0; // Increase speed by 5x per level

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onParticleImpact(ProjectileImpactEvent event) {
        if (event.getRayTraceResult() instanceof EntityHitResult entityHitResult) {
            Entity targetEntity = entityHitResult.getEntity();

            if (event.getEntity() instanceof AbstractArrow abstractArrow && targetEntity instanceof Player player) {
                Level level = abstractArrow.level();
                ItemStack useItemStack = player.getUseItem();

                if (useItemStack.is(ModTags.Items.REFLECT_ENCHANTABLE)) {
                    int lvl = EnchantmentUtil.getEnchantmentLevel(level, useItemStack, ModEnchantments.REFLECT);
                    if (player.isUsingItem() && lvl > 0) {
                        if (!level.isClientSide()) {
                            Vec3 arrowMotion = abstractArrow.getDeltaMovement();

                            double scaleFactor = REFLECT_SCALE_SPEED * lvl;
                            arrowMotion = arrowMotion.normalize().scale(arrowMotion.length() * scaleFactor);

                            abstractArrow.setDeltaMovement(arrowMotion);

                            PacketDistributor.sendToPlayersTrackingEntityAndSelf(abstractArrow, new ReflectArrowPayload(abstractArrow.getId(), arrowMotion.toVector3f()));
                        }
                    }
                }
            }
        }
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.REFLECT_ENCHANTABLE),
                                EnchantmentRarity.COMMON.weight(),
                                2,
                                Enchantment.dynamicCost(2, 10),
                                Enchantment.dynamicCost(18, 10),
                                1,
                                EquipmentSlotGroup.HAND
                        )
                );
    }
}
