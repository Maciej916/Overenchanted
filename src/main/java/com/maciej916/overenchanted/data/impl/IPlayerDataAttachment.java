package com.maciej916.overenchanted.data.impl;

import net.neoforged.neoforge.common.util.ValueIOSerializable;

public interface IPlayerDataAttachment extends ValueIOSerializable {
    int getRevealCountdown();
    void setRevealCountdown(int seconds);

    boolean isLumberjackActive();
    void setLumberjackActive(boolean isActive);

    void tick(int tickCount);

    void scheduleTask(ScheduledTask task);
}
