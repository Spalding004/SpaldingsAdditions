package com.misterspalding.spaldingsadditions.blocks;

import com.misterspalding.spaldingsadditions.inits.FlammablesDec;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ModPlanks extends Block {

	public ModPlanks() {
		super(Block.Properties
				.create(Material.WOOD)
				.hardnessAndResistance(1F, 1.5F)
				.harvestTool(ToolType.AXE)
				.harvestLevel(0)
				);
		
	
		FlammablesDec.registerFlammable(this.getBlock(), 5, 5);
		
		
	}

}
