package com.maciej916.overenchanted.datagen.provider;

import com.maciej916.overenchanted.Overenchanted;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeRagProvider extends DamageTypeTagsProvider {

    public ModDamageTypeRagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Overenchanted.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}

