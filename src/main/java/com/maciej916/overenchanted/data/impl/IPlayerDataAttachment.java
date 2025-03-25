package com.maciej916.overenchanted.data.impl;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public interface IPlayerDataAttachment extends INBTSerializable<CompoundTag> {
    int getRevealCountdown();
    void setRevealCountdown(int seconds);

    boolean isLumberjackActive();
    void setLumberjackActive(boolean isActive);

    void tick(int tickCount);

    void scheduleTask(ScheduledTask task);
}
