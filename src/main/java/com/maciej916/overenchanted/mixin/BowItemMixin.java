package com.maciej916.overenchanted.mixin;

import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BowItem.class)
public class BowItemMixin {

//    private static int BASE_DRAW_DURATION = 72000;
//
//    @Overwrite
//    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
//        int lvl = EnchantmentUtil.getEnchantmentLevel(livingEntity.level(), itemStack, ModEnchantments.QUICK_DRAW);
//
//        if (lvl > 0) {
//            // Default to BASE_DRAW_DURATION, then modify it based on Quick Draw level
//            float multiplier = 1.0F - (lvl * 0.2F);
//
//            // Calculate new duration based on base duration and the multiplier
//            int duration = Mth.floor(BASE_DRAW_DURATION * multiplier);
//
//            // Ensure that the duration is not too short (min 5 ticks)
//            return Math.max(duration, 5);
//        } else {
//           return BASE_DRAW_DURATION;
//        }
//    }
}
