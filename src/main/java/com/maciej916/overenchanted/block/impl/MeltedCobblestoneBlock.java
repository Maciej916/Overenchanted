package com.maciej916.overenchanted.block.impl;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.Nullable;

public class MeltedCobblestoneBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public MeltedCobblestoneBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(6F)
                .sound(SoundType.STONE)
                .isValidSpawn(Blocks::never)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        level.scheduleTick(pos, this, Mth.nextInt(level.getRandom(), 60, 120));
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if ((random.nextInt(3) == 0 || this.fewerNeigboursThan(level, pos, 4))
                && level.getMaxLocalRawBrightness(pos) > 11 - state.getValue(AGE) - state.getLightBlock(level, pos)
                && this.slightlyMelt(state, level, pos)) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (Direction direction : Direction.values()) {
                blockpos$mutableblockpos.setWithOffset(pos, direction);
                BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
                if (blockstate.is(this) && !this.slightlyMelt(blockstate, level, blockpos$mutableblockpos)) {
                    level.scheduleTick(blockpos$mutableblockpos, this, Mth.nextInt(random, 20, 40));
                }
            }
        } else {
            level.scheduleTick(pos, this, Mth.nextInt(random, 20, 40));
        }
    }

    private boolean slightlyMelt(BlockState state, Level level, BlockPos pos) {
        int i = state.getValue(AGE);
        if (i < 3) {
            level.setBlock(pos, state.setValue(AGE, Integer.valueOf(i + 1)), 2);
            return false;
        } else {
            this.melt(state, level, pos);
            return true;
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (block.defaultBlockState().is(this) && this.fewerNeigboursThan(level, pos, 2)) {
            this.melt(state, level, pos);
        }

        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
    }

    private boolean fewerNeigboursThan(BlockGetter level, BlockPos pos, int neighborsRequired) {
        int i = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pos, direction);
            if (level.getBlockState(blockpos$mutableblockpos).is(this)) {
                if (++i >= neighborsRequired) {
                    return false;
                }
            }
        }

        return true;
    }


    public static BlockState meltsInto() {
        return Blocks.LAVA.defaultBlockState();
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        super.playerDestroy(level, player, pos, state, te, stack);
        if (!EnchantmentHelper.hasTag(stack, EnchantmentTags.PREVENTS_ICE_MELTING)) {
            if (level.dimensionType().ultraWarm()) {
                level.removeBlock(pos, false);
                return;
            }

            BlockState blockstate = level.getBlockState(pos.below());
            if (blockstate.blocksMotion() || blockstate.liquid()) {
                level.setBlockAndUpdate(pos, meltsInto());
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(level, pos)) {
            this.melt(state, level, pos);
        }
    }

    protected void melt(BlockState state, Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, meltsInto());
        level.neighborChanged(pos, meltsInto().getBlock(), pos);
    }
}
