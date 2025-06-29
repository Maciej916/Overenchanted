package com.maciej916.overenchanted.capability.impl;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class RicochetArrowCapability implements IRicochetArrowCapability {
    private int entityId;
    private int bouncesLeft;
    private Vec3 motion;
    private float yRot;
    private float xRot;

    public int getBouncesLeft() {
        return bouncesLeft;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public void setBouncesLeft(int bouncesLeft) {
        this.bouncesLeft = bouncesLeft;
    }

    public void setMotion(Vec3 motion) {
        this.motion = motion;
    }

    public Vec3 getMotion() {
        return motion;
    }

    public void setYRot(float yRot) {
        this.yRot = yRot;
    }

    public float getYRot() {
        return yRot;
    }

    public void setXRot(float xRot) {
        this.xRot = xRot;
    }

    public float getXRot() {
        return xRot;
    }

    @Override
    public void serialize(ValueOutput valueOutput) {
        valueOutput.putInt("entityId", entityId);
        valueOutput.putInt("bouncesLeft", bouncesLeft);
        valueOutput.putDouble("motionX", motion.x);
        valueOutput.putDouble("motionY", motion.y);
        valueOutput.putDouble("motionZ", motion.z);
        valueOutput.putFloat("yRot", yRot);
        valueOutput.putFloat("xRot", xRot);
    }

    @Override
    public void deserialize(ValueInput valueInput) {
        entityId = valueInput.getIntOr("entityId", 0);
        bouncesLeft = valueInput.getIntOr("bouncesLeft", 0);
        motion = new Vec3(valueInput.getDoubleOr("motionX", 0), valueInput.getDoubleOr("motionY", 0), valueInput.getDoubleOr("motionZ", 0));
        yRot = valueInput.getFloatOr("yRot", 0);
        xRot = valueInput.getFloatOr("xRot", 0);
    }

    @Override
    public String toString() {
        return "RicochetArrowCapability{" +
                "entityId=" + entityId +
                ", motion=" + motion +
                ", yRot=" + yRot +
                ", xRot=" + xRot +
                ", bouncesLeft=" + bouncesLeft +
                '}';
    }
}
