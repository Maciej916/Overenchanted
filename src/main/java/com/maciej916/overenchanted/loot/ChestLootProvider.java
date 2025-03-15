package com.maciej916.overenchanted.loot;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ChestLootProvider implements LootTableSubProvider {

    public static final String COMMON_LOOT_TABLE_NAME = "chests/common_loot";
    public static final String ALLOY_LOOT_TABLE_NAME = "chests/alloy_loot";

    private final HolderLookup.Provider registries;

    public ChestLootProvider(HolderLookup.Provider registries) {
        this.registries = registries;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> writer) {
        generateCommonLoot(writer);
    }

    private void generateCommonLoot(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> writer) {
        var lootPool = LootPool
                .lootPool()
                .name("Overenchanted")
                .setRolls(UniformGenerator.between(1.0f, 3.0f))
//                .add(LootItem.lootTableItem(ModBlocks.MELTED_COBBLESTONE.get())
//                        .when(LootItemRandomChanceCondition.randomChance(0.25f))
//                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)))
//                )
                ;


        var lootTable = LootTable
                .lootTable()
                .withPool(lootPool)
                .setParamSet(LootContextParamSets.CHEST);

        writer.accept(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, COMMON_LOOT_TABLE_NAME)), lootTable);
    }
}