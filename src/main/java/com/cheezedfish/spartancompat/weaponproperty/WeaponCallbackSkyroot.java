package com.cheezedfish.spartancompat.weaponproperty;

import java.util.List;

import com.cheezedfish.spartancompat.SpartanCompatability;
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

public class WeaponCallbackSkyroot implements IWeaponCallback {
	
	
	public WeaponCallbackSkyroot() { }

	@Override
	public void onTooltip(ToolMaterialEx arg0, ItemStack arg1, World arg2, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(TextFormatting.AQUA  + StringHelper.translateString("skyroot_double_drops", "tooltip", SpartanCompatability.MODID));
		if(GuiScreen.isShiftKeyDown())
		{
			tooltip.add(TextFormatting.ITALIC + " " + StringHelper.translateString("skyroot_double_drops.desc", "tooltip", SpartanCompatability.MODID));
		}
	}
	
	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		if(projectile != null) {
			// TODO: Implement double drop functionality
		}
	}

}
