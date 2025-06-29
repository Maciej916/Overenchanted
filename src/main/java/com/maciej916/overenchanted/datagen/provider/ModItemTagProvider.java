package com.maciej916.overenchanted.datagen.provider;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Overenchanted.MOD_ID);
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

        tag(ModTags.Items.ENRAGED_ENCHANTABLE)
                .addTag(ItemTags.CHEST_ARMOR)
                .add(Items.ELYTRA)
        ;

        tag(ModTags.Items.FLEETFOOT_ENCHANTABLE)
                .addTag(ItemTags.FOOT_ARMOR)
        ;

        tag(ModTags.Items.AQUA_MINER_ENCHANTABLE)
                .addTag(ItemTags.HEAD_ARMOR)
        ;

        tag(ModTags.Items.ECHO_SIGHT_ENCHANTABLE)
                .addTag(ModTags.Items.commonTag("armors"))
        ;

        tag(ModTags.Items.LUMBERJACK_ENCHANTABLE)
                .addTag(ItemTags.AXES)
        ;

        tag(ModTags.Items.SONIC_BOOM_ENCHANTABLE)
                .addTag(ModTags.Items.commonTag("armors"))
        ;

        tag(ModTags.Items.REFLECT_ENCHANTABLE)
                .addTag(ModTags.Items.commonTag("tools/shield"))
        ;
    }
}

