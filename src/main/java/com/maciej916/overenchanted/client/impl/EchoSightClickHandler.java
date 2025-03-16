package com.maciej916.overenchanted.client.impl;

import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.network.payload.EchoSightPayload;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;

public class EchoSightClickHandler {

    public static void handle () {
        Minecraft instance = Minecraft.getInstance();
        int totalLevel = 0;

        if (instance.player != null) {
            for (ItemStack itemStack : instance.player.getArmorSlots()) {
                int lvl = EnchantmentUtil.getEnchantmentLevel(instance.level, itemStack, ModEnchantments.ECHO_SIGHT);
                totalLevel += lvl;
            }
        }

        if (totalLevel > 0) {
            PacketDistributor.sendToServer(new EchoSightPayload(totalLevel));
        }
    }
}
