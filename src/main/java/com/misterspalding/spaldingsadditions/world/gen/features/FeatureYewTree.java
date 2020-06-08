package com.misterspalding.spaldingsadditions.world.gen.features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

public class FeatureYewTree extends  AbstractTreeFeature<FeatureYewTreeConfig> {
	  public FeatureYewTree(Function<Dynamic<?>, FeatureYewTreeConfig> function) {
	    super(function);
	  }
	  
	  @SuppressWarnings("deprecation")
	  @Override
	  protected boolean place(IWorldGenerationReader world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, MutableBoundingBox box, FeatureYewTreeConfig config) {
	    // Total log length
	    int length = rand.nextInt(3) + config.baseHeight;

	    // Axis
	    Direction.Axis axis = rand.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
	    Direction direction = Direction.getFacingFromAxisDirection(axis, rand.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);

	    BlockPos below = origin.down();
	    if (!isSoilOrFarm(world, below, null)) {
	      return false;
	    }

	    BlockPos.Mutable pos = new BlockPos.Mutable(origin);

	    int air = 0;
	    for (int i = 0; i < length; i++) {
	      pos.move(direction);

	      if (!world.hasBlockState(pos.move(Direction.DOWN), BlockState::isSolid)) {
	        air++;
	      }

	      if (!isAirOrLeaves(world, pos.move(Direction.UP))) {
	        return false;
	      }
	    }

	    // No floating logs
	    if (air * 2 > length) {
	      return false;
	    }

	    pos.setPos(origin);
	    for (int i = 0; i < length; i++) {
	      pos.move(direction);

	      BlockState log = config.trunkProvider.getBlockState(rand, pos);

	      if (isAirOrLeaves(world, pos) || isDirtOrGrassBlock(world, pos) || isWater(world, pos)) {
	        func_227217_a_(world, pos, log.with(LogBlock.AXIS, axis), box);
	        logs.add(pos.toImmutable());
	      }

	      pos.move(Direction.DOWN);

	      if (isSoilOrFarm(world, pos, null)) {
	        func_227217_a_(world, pos, Blocks.DIRT.getDefaultState(), box);
	      }

	      pos.move(Direction.UP);
	    }

	    return true;
	  }
	}