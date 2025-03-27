package com.maciej916.overenchanted.capability.impl;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.projectile.Arrow;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

public class RicochetArrowCapabilityProvider implements ICapabilityProvider<Arrow, Void, IRicochetArrowCapability>, INBTSerializable<CompoundTag> {
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
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return getOrCreatePlayerCapability().serializeNBT(provider);
    }


    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        getOrCreatePlayerCapability().deserializeNBT(provider, compoundTag);
    }
}