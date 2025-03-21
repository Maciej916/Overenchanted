package com.maciej916.overenchanted.painting;

import com.maciej916.overenchanted.Overenchanted;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.Optional;

public class ModPaintings {
    public static final ResourceKey<PaintingVariant> RUNES_COLOR = create("runes_colors");


    private static ResourceKey<PaintingVariant> create(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, name));
    }

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, RUNES_COLOR, 5, 3);
    }

    private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
        register(context, key, width, height, true);
    }

    private static void register(BootstrapContext<PaintingVariant> registry, ResourceKey<PaintingVariant> key, int width, int height, boolean hasAuthor) {
        registry.register(key, new PaintingVariant(width, height, key.location()));
    }
}