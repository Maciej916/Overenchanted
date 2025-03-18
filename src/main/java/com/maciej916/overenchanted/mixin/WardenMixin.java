package com.maciej916.overenchanted.mixin;

import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Warden.class)
public class WardenMixin {
    @Inject(method = "canTargetEntity", at = @At("RETURN"), cancellable = true)
    private void canTargetEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            if (entity instanceof Player player) {
                ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
                int level = EnchantmentUtil.getEnchantmentLevel(player.level(), boots, ModEnchantments.DEEPFOOT);
                if (level > 0) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}