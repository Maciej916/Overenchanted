package com.maciej916.overenchanted.datagen;

import com.maciej916.overenchanted.enchantment.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class ModMinecraftEnchantmentTagProvider extends EnchantmentTagsProvider {

    public ModMinecraftEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, "minecraft");
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .add(ModEnchantments.BLAZING_EDGE)
                .add(ModEnchantments.BLAZING_WALKER)
                .add(ModEnchantments.EXPLOSIVE_SHOT)
                .add(ModEnchantments.FLOATING)
                .add(ModEnchantments.FREE_RIDING)
                .add(ModEnchantments.PARALYSIS)
                .add(ModEnchantments.REINFORCED_TIP)
                .add(ModEnchantments.HIGH_STEP)
                .add(ModEnchantments.STUN)
                .add(ModEnchantments.LUMBERJACK)
        ;

        tag(EnchantmentTags.CURSE)
                .add(ModEnchantments.BREAKING_CURSE)
                .add(ModEnchantments.INEFFICIENCY_CURSE)
                .add(ModEnchantments.IGNORANCE_CURSE)
                .add(ModEnchantments.AGONY_CURSE)
        ;

        tag(EnchantmentTags.ON_MOB_SPAWN_EQUIPMENT)
                .add(ModEnchantments.ETERNAL)
                .add(ModEnchantments.BLAZING_EDGE)
                .add(ModEnchantments.ENRAGED)
                .add(ModEnchantments.FLEETFOOT)
                .add(ModEnchantments.PARALYSIS)
                .add(ModEnchantments.AGONY_CURSE)
                .add(ModEnchantments.LUMBERJACK)
        ;

        tag(EnchantmentTags.ON_RANDOM_LOOT)
                .add(ModEnchantments.BREAKING_CURSE)
                .add(ModEnchantments.INEFFICIENCY_CURSE)
                .add(ModEnchantments.IGNORANCE_CURSE)
                .add(ModEnchantments.ETERNAL)
                .add(ModEnchantments.FREE_RIDING)
                .add(ModEnchantments.FLOATING)
                .add(ModEnchantments.PARALYSIS)
                .add(ModEnchantments.HIGH_STEP)
                .add(ModEnchantments.EXPLOSIVE_SHOT)
                .add(ModEnchantments.AGONY_CURSE)
                .add(ModEnchantments.LUMBERJACK)
        ;

        tag(EnchantmentTags.TRADEABLE)
                .add(ModEnchantments.BLAZING_EDGE)
                .add(ModEnchantments.REINFORCED_TIP)
                .add(ModEnchantments.FREE_RIDING)
                .add(ModEnchantments.FLOATING)
                .add(ModEnchantments.EXPLOSIVE_SHOT)
                .add(ModEnchantments.PARALYSIS)
                .add(ModEnchantments.ENRAGED)
                .add(ModEnchantments.BLAZING_WALKER)
                .add(ModEnchantments.FLEETFOOT)
                .add(ModEnchantments.HIGH_STEP)
                .add(ModEnchantments.AQUA_GLIDE)
                .add(ModEnchantments.AQUA_MINER)
                .add(ModEnchantments.ETERNAL)
                .add(ModEnchantments.BREAKING_CURSE)
                .add(ModEnchantments.INEFFICIENCY_CURSE)
                .add(ModEnchantments.IGNORANCE_CURSE)
                .add(ModEnchantments.STUN)
                .add(ModEnchantments.FASTER_ATTACK)
                .add(ModEnchantments.LIFEBINDER)
                .add(ModEnchantments.AGONY_CURSE)
                .add(ModEnchantments.ECHO_SIGHT)
                .add(ModEnchantments.LUMBERJACK)
        ;

        tag(EnchantmentTags.TREASURE)
                .add(ModEnchantments.AQUA_GLIDE)
                .add(ModEnchantments.AQUA_MINER)
                .add(ModEnchantments.FASTER_ATTACK)
                .add(ModEnchantments.ECHO_SIGHT)
        ;
    }
}

