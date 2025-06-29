package com.maciej916.overenchanted.capability.impl;

import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.ValueIOSerializable;

public interface IRicochetArrowCapability extends ValueIOSerializable {

    void setEntityId(int entityId);

    void setBouncesLeft(int bouncesLeft);
    int getBouncesLeft();

    void setMotion(Vec3 motion);
    Vec3 getMotion();

    void setYRot(float yRot);
    float getYRot();

    void setXRot(float xRot);
    float getXRot();
}
