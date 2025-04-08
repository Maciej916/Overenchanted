package com.maciej916.overenchanted.datagen.provider;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Overenchanted.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ModTags.Blocks.REINFORCED_TIP_BLOCKS)
                // Pickaxe
                .addTag(ModTags.Blocks.commonTag("obsidians"))
                .add(Blocks.ANCIENT_DEBRIS)
                .add(Blocks.NETHERITE_BLOCK)
                .add(Blocks.ENCHANTING_TABLE)
                .add(Blocks.RESPAWN_ANCHOR)
                .add(Blocks.RESPAWN_ANCHOR)
                // Axe
                .addTag(ModTags.Blocks.commonTag("bookshelves"))
                .add(Blocks.BEEHIVE)
                .add(Blocks.BEE_NEST)
                .add(Blocks.CHISELED_BOOKSHELF)
                // Shovel
                .add(Blocks.CLAY)
                .add(Blocks.MUD)
                .add(Blocks.ROOTED_DIRT)
                .add(Blocks.PODZOL)
                 // Hoe
                .add(Blocks.NETHER_WART_BLOCK)
                .add(Blocks.WARPED_WART_BLOCK)
                .add(Blocks.HAY_BLOCK)
        ;


        tag(ModTags.Blocks.LUMBERJACK_BLOCKS)
                .addTag(BlockTags.LOGS)
                .addTag(ModTags.Blocks.commonTag("stripped_logs"))
        ;
    }
}
