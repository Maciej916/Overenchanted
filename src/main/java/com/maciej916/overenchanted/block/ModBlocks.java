package com.maciej916.overenchanted.block;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.block.impl.MeltedCobblestoneBlock;
import com.maciej916.overenchanted.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Overenchanted.MOD_ID);

    public static final DeferredBlock<Block> MELTED_COBBLESTONE = registerBlock("melted_cobblestone", MeltedCobblestoneBlock::new, false);

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, boolean hasBlockItem) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);

        if (hasBlockItem) {
            registerBlockItem(name, toReturn);
        }

        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}