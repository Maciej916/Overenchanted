package com.maciej916.overenchanted.capability.impl;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.UnknownNullability;

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
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();

        tag.putInt("entityId", entityId);
        tag.putInt("bouncesLeft", bouncesLeft);
        tag.putDouble("motionX", motion.x);
        tag.putDouble("motionY", motion.y);
        tag.putDouble("motionZ", motion.z);
        tag.putFloat("yRot", yRot);
        tag.putFloat("xRot", xRot);

        return tag;
    }


    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        entityId = compoundTag.getInt("entityId");
        bouncesLeft = compoundTag.getInt("bouncesLeft");
        motion = new Vec3(compoundTag.getDouble("motionX"), compoundTag.getDouble("motionY"), compoundTag.getDouble("motionZ"));
        yRot = compoundTag.getFloat("yRot");
        xRot = compoundTag.getFloat("xRot");
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
