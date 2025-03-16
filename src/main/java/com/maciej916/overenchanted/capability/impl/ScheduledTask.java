package com.maciej916.overenchanted.capability.impl;

public class ScheduledTask implements Runnable {
    private final Runnable runnable;
    private final int onTick;

    public ScheduledTask(int onTick, Runnable runnable) {
        this.onTick = onTick;
        this.runnable = runnable;
    }

    public int getOnTick() {
        return onTick;
    }

    @Override
    public void run() {
        runnable.run();
    }
}
