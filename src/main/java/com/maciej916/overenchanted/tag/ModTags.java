package com.maciej916.overenchanted.tag;

import com.maciej916.overenchanted.Overenchanted;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class ModTags {


    public static class Blocks {

        public static final TagKey<Block> REINFORCED_TIP_BLOCKS = tag("reinforced_tip_blocks");



        public static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, name));
        }

        public static TagKey<Block> commonTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Items {

        public static final TagKey<Item> FREE_RIDING_ENCHANTABLE = tag("enchantable/free_riding");
        public static final TagKey<Item> ETERNAL_ENCHANTABLE = tag("enchantable/eternal");
        public static final TagKey<Item> IGNORANCE_ENCHANTABLE = tag("enchantable/ignorance");
        public static final TagKey<Item> ENRAGED_ENCHANTABLE = tag("enchantable/enraged");
        public static final TagKey<Item> FLEETFOOT_ENCHANTABLE = tag("enchantable/fleetfoot");
        public static final TagKey<Item> AQUA_MINER_ENCHANTABLE = tag("enchantable/aqua_miner");



        public static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, name));
        }

        public static TagKey<Item> commonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }


    public static class Enchantments {
        public static final TagKey<Enchantment> FREE_RIDING_EXCLUSIVE = tag("exclusive_set/free_riding_exclusive");

        public static TagKey<Enchantment> tag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Overenchanted.MOD_ID, name));
        }
    }

}
