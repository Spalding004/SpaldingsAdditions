package com.misterspalding.spaldingsadditions.integrations.jei.autofrak;

import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractAutoFrakRecipeCategory implements IRecipeCategory<AutoFrakRecipeWrapper> {
	   protected static final ResourceLocation TEXTURES = new ResourceLocation("spaldingsadditions:textures/gui/frakhammer_jei.png");
	   protected final int INPUT = 0;
	   protected final int CARD = 1;
	   protected final int FUEL = 3;
	   protected final int OUTPUT = 2;
	   
	  
	   protected final IDrawableAnimated animatedArrow;

	   public AbstractAutoFrakRecipeCategory(IGuiHelper helper) {
	      IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 0, 53, 22, 16);
	      this.animatedArrow = helper.createAnimatedDrawable(staticArrow, 200, StartDirection.LEFT, false);
	   }
	}
