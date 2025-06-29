package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.capability.ModCapabilities;
import com.maciej916.overenchanted.capability.impl.IRicochetArrowCapability;
import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.network.payload.RicochetArrowPayload;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Overenchanted.MOD_ID)
public class RicochetEnchantment {
    private static final double SPEED_DAMPENING = 0.7; // Reduce speed after each bounce
    private static final double ANGLE_VARIATION = 0.1; // Add small angle variation

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onArrowJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof AbstractArrow abstractArrow) {
            ItemStack weaponItem = abstractArrow.getWeaponItem();
            int lvl = EnchantmentUtil.getEnchantmentLevel(event.getLevel(), weaponItem, ModEnchantments.RICOCHET);
            if (lvl > 0) {
                IRicochetArrowCapability capability = abstractArrow.getCapability(ModCapabilities.RICOCHET_ARROW);
                if (capability != null) {
                    capability.setEntityId(abstractArrow.getId());
                    capability.setBouncesLeft(lvl);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onParticleImpact(ProjectileImpactEvent event) {
        if (event.getRayTraceResult() instanceof BlockHitResult blockHitResult) {
            if (event.getEntity() instanceof AbstractArrow abstractArrow) {
                Level level = abstractArrow.level();
                IRicochetArrowCapability capability = abstractArrow.getCapability(ModCapabilities.RICOCHET_ARROW);

                if (capability != null) {
                    if (capability.getBouncesLeft() > 0) {
                        if (!level.isClientSide()) {
                            Direction facing = ((BlockHitResult) event.getRayTraceResult()).getDirection();
                            Vec3 arrowMotion = abstractArrow.getDeltaMovement();

                            // Reverse velocity
                            switch (facing) {
                                case UP, DOWN -> arrowMotion = new Vec3(arrowMotion.x, -arrowMotion.y, arrowMotion.z);
                                case EAST, WEST -> arrowMotion = new Vec3(-arrowMotion.x, arrowMotion.y, arrowMotion.z);
                                case NORTH, SOUTH -> arrowMotion = new Vec3(arrowMotion.x, arrowMotion.y, -arrowMotion.z);
                            }

                            // Dampen speed
                            arrowMotion = arrowMotion.scale(SPEED_DAMPENING);

                            // Add angle variation
                            double variationX = (level.random.nextDouble() - 0.5) * ANGLE_VARIATION;
                            double variationY = (level.random.nextDouble() - 0.5) * ANGLE_VARIATION;
                            double variationZ = (level.random.nextDouble() - 0.5) * ANGLE_VARIATION;
                            arrowMotion = arrowMotion.add(variationX, variationY, variationZ);

                            // Update motion
                            abstractArrow.setDeltaMovement(arrowMotion);

                            // Update rotation
                            float yRot = abstractArrow.getYRot() + (float)(level.random.nextDouble() * 10 - 5);
                            float xRot = abstractArrow.getXRot() + (float)(level.random.nextDouble() * 5 - 2.5);
                            abstractArrow.setYRot(yRot);
                            abstractArrow.setXRot(xRot);

                            capability.setMotion(arrowMotion);
                            capability.setYRot(yRot);
                            capability.setXRot(xRot);
                            capability.setBouncesLeft(capability.getBouncesLeft() - 1);

                            PacketDistributor.sendToPlayersTrackingEntityAndSelf(abstractArrow, new RicochetArrowPayload(abstractArrow.getId(), arrowMotion.toVector3f(), yRot, xRot, capability.getBouncesLeft()));
                        }
                        event.setCanceled(true);
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
                                items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                EnchantmentRarity.COMMON.weight(),
                                4,
                                Enchantment.dynamicCost(2, 10),
                                Enchantment.dynamicCost(18, 10),
                                1,
                                EquipmentSlotGroup.HAND
                        )
                )
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.BOW_EXCLUSIVE));
    }
}
