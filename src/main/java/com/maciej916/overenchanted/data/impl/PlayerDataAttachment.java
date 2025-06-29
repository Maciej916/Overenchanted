package com.maciej916.overenchanted.data.impl;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PlayerDataAttachment implements IPlayerDataAttachment {
    private final List<ScheduledTask> scheduledTasks = new LinkedList<>();

    private int currentTick = 0;

    private int revealCountdown = 0;
    private boolean lumberjackActive = false;

    @Override
    public int getRevealCountdown() {
        return revealCountdown;
    }

    @Override
    public void setRevealCountdown(int seconds) {
        revealCountdown = seconds;
    }

    @Override
    public boolean isLumberjackActive() {
        return lumberjackActive;
    }

    @Override
    public void setLumberjackActive(boolean isActive) {
        lumberjackActive = isActive;
    }

    private void decreaseCooldowns() {
        if (revealCountdown > 0) {
            revealCountdown--;
        }
    }

    @Override
    public void tick(int tickCount) {
        currentTick++;
        if (currentTick % 20 == 0) {
            currentTick = 0;
        }

        if (currentTick == 0) {
            decreaseCooldowns();
        }

        Iterator<ScheduledTask> iterator = scheduledTasks.iterator();
        while (iterator.hasNext()) {
            ScheduledTask task = iterator.next();
            if (task.getOnTick() <= tickCount) {
                task.run();
                iterator.remove();
            }
        }
    }

    @Override
    public void scheduleTask(ScheduledTask task) {
        scheduledTasks.add(task);
    }

    @Override
    public void serialize(ValueOutput valueOutput) {
        valueOutput.putInt("revealCountdown", revealCountdown);
    }

    @Override
    public void deserialize(ValueInput valueInput) {
        revealCountdown = valueInput.getIntOr("revealCountdown", 0);
    }
}
