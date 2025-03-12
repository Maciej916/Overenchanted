package com.maciej916.overenchanted.datagen;

import com.maciej916.overenchanted.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.FREE_RIDING_ENCHANTABLE)
                .add(Items.CARROT_ON_A_STICK)
                .add(Items.WARPED_FUNGUS_ON_A_STICK)
        ;

        tag(ModTags.Items.ETERNAL_ENCHANTABLE)
                .addTag(ModTags.Items.commonTag("armors"))
                .addTag(ModTags.Items.commonTag("tools"))
        ;

        tag(ModTags.Items.IGNORANCE_ENCHANTABLE)
                .addTag(ItemTags.DURABILITY_ENCHANTABLE)
        ;

        tag(ModTags.Items.ENRAGED_ITEMS)
                .addTag(ItemTags.CHEST_ARMOR)
                .add(Items.ELYTRA)
        ;
    }
}

