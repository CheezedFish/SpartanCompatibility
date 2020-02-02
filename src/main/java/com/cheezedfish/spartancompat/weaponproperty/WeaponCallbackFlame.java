package com.cheezedfish.spartancompat.weaponproperty;

import java.util.List;

import com.cheezedfish.spartancompat.SpartanCompatibility;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WeaponCallbackFlame implements IWeaponCallback {
	
	protected int duration = 5;
	
	public WeaponCallbackFlame(int flameTime) { 
		duration = flameTime;
	}

	@Override
	public void onTooltip(ToolMaterialEx arg0, ItemStack arg1, World arg2, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(TextFormatting.DARK_RED  + StringHelper.translateString("flame", "tooltip", SpartanCompatibility.MODID));
		if(GuiScreen.isShiftKeyDown())
		{
			tooltip.add(TextFormatting.ITALIC + " " + StringHelper.translateString("flame.desc", "tooltip", SpartanCompatibility.MODID));
		}
	}
	
	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		if(projectile != null) {
			target.setFire(duration);
		}
	}

}
