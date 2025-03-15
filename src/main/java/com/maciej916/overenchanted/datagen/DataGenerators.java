package com.maciej916.overenchanted.datagen;

import com.maciej916.overenchanted.Overenchanted;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Overenchanted.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new ModDatapackProvider(packOutput, lookupProvider));
        generator.addProvider(true, new ModEnchantmentTagProvider(packOutput, lookupProvider));

        generator.addProvider(true, new ModMinecraftEnchantmentTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new ModMinecraftPaintingVariantTagsProvider(packOutput, lookupProvider));

        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider);

        generator.addProvider(true, blockTagsProvider);
        generator.addProvider(true, new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter()));

//        generator.addProvider(true, new ModGlobalLootModifierProvider(packOutput, lookupProvider));
//        generator.addProvider(true,
//                new LootTableProvider(packOutput, Collections.emptySet(), List.of(
//                        new LootTableProvider.SubProviderEntry(ChestLootProvider::new, LootContextParamSets.CHEST)),
//                        lookupProvider)
//                );
    }
}