package com.maciej916.overenchanted.capability.impl;

import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import org.jetbrains.annotations.Nullable;

public class RicochetArrowCapabilityProvider implements ICapabilityProvider<Arrow, Void, IRicochetArrowCapability>, ValueIOSerializable {
    private IRicochetArrowCapability capability = null;

    private IRicochetArrowCapability getOrCreatePlayerCapability() {
        if (capability == null) {
            capability = new RicochetArrowCapability();
        }

        return capability;
    }

    @Override
    public @Nullable IRicochetArrowCapability getCapability(Arrow arrow, Void unused) {
        return getOrCreatePlayerCapability();
    }

    @Override
    public void serialize(ValueOutput valueOutput) {
        getOrCreatePlayerCapability().serialize(valueOutput);
    }

    @Override
    public void deserialize(ValueInput valueInput) {
        getOrCreatePlayerCapability().deserialize(valueInput);
    }
}