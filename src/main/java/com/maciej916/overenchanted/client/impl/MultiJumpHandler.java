package com.maciej916.overenchanted.client.impl;

import com.maciej916.overenchanted.data.ModDataComponents;
import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.network.payload.MultiJumpPayload;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;

public class MultiJumpHandler {

    public static void handle () {
        Minecraft instance = Minecraft.getInstance();

        if (instance.player != null) {
            ItemStack stack = instance.player.getItemBySlot(EquipmentSlot.FEET);

            int lvl = EnchantmentUtil.getEnchantmentLevel(instance.level, stack, ModEnchantments.MULTI_JUMP);
            if (lvl > 0) {
                if (stack.has(ModDataComponents.MULTI_JUMP)) {
                    int multiJump = stack.getOrDefault(ModDataComponents.MULTI_JUMP, 0);
                    if (multiJump < lvl && allowJump(instance.player)) {
                        PacketDistributor.sendToServer(new MultiJumpPayload(multiJump + 1));
                        instance.player.jumpFromGround();
                    }
                } else {
                    stack.set(ModDataComponents.MULTI_JUMP, 1);
                }
            }
        }
    }

    private static boolean allowJump(Player player) {
        ItemStack itemstack = player.getItemBySlot(EquipmentSlot.CHEST);
        boolean fallFlyingReady = LivingEntity.canGlideUsing(itemstack, EquipmentSlot.CHEST);

        return !player.isCreative() &&
                !player.isCrouching() &&
                !player.onGround() && player.getPassengers().isEmpty() && !player.getAbilities().flying &&
                !player.isInLiquid() &&
                !fallFlyingReady;

    }
}
