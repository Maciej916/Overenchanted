package com.maciej916.overenchanted.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityUtil {

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

}
