package com.maciej916.overenchanted.enchantment.impl;

import com.maciej916.overenchanted.Overenchanted;
import com.maciej916.overenchanted.data.ModDataAttachments;
import com.maciej916.overenchanted.data.impl.PlayerDataAttachment;
import com.maciej916.overenchanted.enchantment.ModEnchantments;
import com.maciej916.overenchanted.tag.ModTags;
import com.maciej916.overenchanted.util.EnchantmentRarity;
import com.maciej916.overenchanted.util.EnchantmentUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@EventBusSubscriber(modid = Overenchanted.MOD_ID)
public class LumberjackEnchantment {
    private static final int MAX_TREE_SIZE = 512;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        execute(event, event.getState(), event.getPos(), event.getPlayer());
    }

    private static void execute(BlockEvent.BreakEvent event, BlockState blockState, BlockPos pos, Player player) {
        if (blockState.is(ModTags.Blocks.LUMBERJACK_BLOCKS)) {
            ItemStack stack = player.getItemInHand(player.getUsedItemHand());
            int lvl = EnchantmentUtil.getEnchantmentLevel(player.level(), stack, ModEnchantments.LUMBERJACK);

            if (lvl > 0 && player.hasData(ModDataAttachments.PLAYER_DATA)) {
                PlayerDataAttachment playerDataAttachment = player.getData(ModDataAttachments.PLAYER_DATA);

                if (playerDataAttachment.isLumberjackActive()) {
                    Level level = player.level();
                    Set<BlockPos> treeBlocks = findTree(level, pos, blockState);

                    if (treeBlocks.size() <= MAX_TREE_SIZE) {
                        doBreak(player, level, pos, treeBlocks, stack);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    private static void doBreak(Player player, Level level, BlockPos pos, Set<BlockPos> treeBlocks, ItemStack stack) {
        Block block = level.getBlockState(pos).getBlock();

        int logsToBreak = stack.isDamageableItem() ? Math.min(MAX_TREE_SIZE, stack.getDamageValue()) : MAX_TREE_SIZE;
        int logsBroken = 0;

        for (BlockPos point : treeBlocks) {
            if (logsBroken <= logsToBreak) {
                level.setBlock(point, Blocks.AIR.defaultBlockState(), 3);
                logsBroken++;
            } else {
                break;
            }
        }

        if (logsBroken > 0) {
            ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block, logsBroken));
            level.addFreshEntity(item);

            if (!player.isCreative() && !player.isSpectator()) {
                stack.hurtAndBreak(logsBroken, player, player.getEquipmentSlotForItem(stack));
            }
        }
    }

    private static Set<BlockPos> findTree(Level level, BlockPos start, BlockState state) {
        Set<BlockPos> treeBlocks = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty() && treeBlocks.size() <= MAX_TREE_SIZE) {
            BlockPos pos = queue.poll();

            if (treeBlocks.contains(pos)) continue;

            BlockState currentState = level.getBlockState(pos);
            if (currentState.getBlock() == state.getBlock()) {
                treeBlocks.add(pos);

                for (int offsetX = -1; offsetX <= 1; offsetX++) {
                    for (int offsetY = -1; offsetY <= 1; offsetY++) {
                        for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                            BlockPos newPos = pos.offset(offsetX, offsetY, offsetZ);
                            if (!treeBlocks.contains(newPos)) {
                                queue.add(newPos);
                            }
                        }
                    }
                }
            }
        }

        return treeBlocks;
    }

    public static Enchantment.Builder builder(BootstrapContext<Enchantment> context) {
        var items = context.lookup(Registries.ITEM);

        return Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.LUMBERJACK_ENCHANTABLE),
                                EnchantmentRarity.VERY_RARE.weight(),
                                1,
                                Enchantment.constantCost(15),
                                Enchantment.constantCost(65),
                                8,
                                EquipmentSlotGroup.HAND
                        )
                );
    }
}