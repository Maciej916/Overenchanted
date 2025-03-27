package com.maciej916.overenchanted.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class EntityUtil {
    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.FEET,
            EquipmentSlot.LEGS,
            EquipmentSlot.CHEST,
            EquipmentSlot.HEAD
    };

    public static boolean hasLineOfSight(Entity source, LivingEntity target, ServerLevel level) {
        if (target.equals(source)) return true;

        Vec3 startPos = source.getEyePosition();
        Vec3 targetPos = target.getEyePosition();

        HitResult result = level.clip(new ClipContext(
                startPos, targetPos,
                ClipContext.Block.VISUAL,
                ClipContext.Fluid.NONE,
                source
        ));

        return result.getType() == HitResult.Type.MISS;
    }

    public static Iterable<ItemStack> getArmorSlots(LivingEntity entity) {
        List<ItemStack> armorSlots = new ArrayList<>();

        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack armorPiece = entity.getItemBySlot(slot);
            if (armorPiece.isEmpty()) continue;

            Equippable equippable = armorPiece.get(DataComponents.EQUIPPABLE);
            if (equippable != null && equippable.slot().isArmor()) {
                armorSlots.add(armorPiece);
            }
        }

        return armorSlots;
    }
}
