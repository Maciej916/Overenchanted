package com.maciej916.overenchanted.datagen.provider;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends EntityTypeTagsProvider {

    public ModEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Overenchanted.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Entities.SENSITIVE_TO_ECHO_SLAYER)
                .add(EntityType.WARDEN)
        ;
    }
}

