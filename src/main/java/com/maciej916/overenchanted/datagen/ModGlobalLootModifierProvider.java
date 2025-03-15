package com.maciej916.overenchanted.datagen;

import com.maciej916.overenchanted.Overenchanted;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Overenchanted.MOD_ID);
    }

    @Override
    protected void start() {
//        add("add_loot_simple_dungeon" , loot(
//                name("simple_dungeon"),
//                LootTableIdCondition.builder(BuiltInLootTables.UNDERWATER_RUIN_SMALL.location()).or(LootTableIdCondition.builder(BuiltInLootTables.UNDERWATER_RUIN_BIG.location())).build()
//                )
//        );

        modifyChestLoot("simple_dungeon", Stream.of("chests/simple_dungeon"));
    }

    private void modifyChestLoot(String lootTableName, Stream<String> targets) {
        var mappedTargetConditions = targets.map(r -> LootTableIdCondition.builder(ResourceLocation.parse(r))).toArray(LootTableIdCondition.Builder[]::new);
        add(lootTableName,
                new AddTableLootModifier(
                        new LootItemCondition[] { AnyOfCondition.anyOf(mappedTargetConditions).build() },
                        ResourceKey.create(Registries.LOOT_TABLE, name(lootTableName))
                )
        );
    }

    private static ResourceLocation name(String lootTableName) {
        return ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, "chests/" + lootTableName);
    }
}