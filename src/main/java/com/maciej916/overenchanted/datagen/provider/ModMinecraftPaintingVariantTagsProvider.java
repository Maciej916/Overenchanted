package com.maciej916.overenchanted.datagen.provider;

import com.maciej916.overenchanted.painting.ModPaintings;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;

import java.util.concurrent.CompletableFuture;

public class ModMinecraftPaintingVariantTagsProvider extends PaintingVariantTagsProvider {

    public ModMinecraftPaintingVariantTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, "minecraft");
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(PaintingVariantTags.PLACEABLE)
                .add(ModPaintings.RUNES_COLOR)
        ;
    }
}

