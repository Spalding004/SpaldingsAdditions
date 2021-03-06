package com.misterspalding.spaldingsadditions.objects.blocks.machines;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class MachineCommon extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;



	public MachineCommon() {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(.8f));
		 this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, Boolean.FALSE));
	    
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }
	
	@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
	
	 @Nullable
	    @Override
	    public BlockState getStateForPlacement(BlockItemUseContext context) {
	        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	    }

	    @Override
	    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
	        return state.with(FACING, direction.rotate(state.get(FACING)));
	    }

	    @Override
	    public BlockState mirror(BlockState state, Mirror mirror) {
	        return state.rotate(mirror.toRotation(state.get(FACING)));
	    }

	    @Nullable
	    @Override
	    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

	    @Override
	    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	        
	        TileEntity tileEntity = worldIn.getTileEntity(pos);
	        if (tileEntity instanceof INamedContainerProvider && player instanceof ServerPlayerEntity) {
	            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity);
	            
	        }
	        return ActionResultType.SUCCESS;
	    }

	    @Override
	    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
	        return state.get(LIT) ? 3 : 0;
	    }
}
