package com.maciej916.overenchanted.capability.impl;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

public class PlayerCapability implements ICapabilityProvider<Player, Void, IOPlayerCapability>, INBTSerializable<CompoundTag> {
    private IOPlayerCapability playerCapability = null;

    private IOPlayerCapability getOrCreatePlayerCapability() {
        if (playerCapability == null) {
            playerCapability = new OPlayerCapability();
        }

        return playerCapability;
    }

    @Override
    public @Nullable IOPlayerCapability getCapability(Player o, Void unused) {
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
