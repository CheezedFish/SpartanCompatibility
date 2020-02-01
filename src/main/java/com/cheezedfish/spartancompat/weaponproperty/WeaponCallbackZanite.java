package com.cheezedfish.spartancompat.weaponproperty;

import java.util.List;

import com.cheezedfish.spartancompat.SpartanCompatability;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WeaponCallbackZanite implements IWeaponCallback {
	
	
	public WeaponCallbackZanite() { }

	@Override
	public void onTooltip(ToolMaterialEx arg0, ItemStack arg1, World arg2, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(TextFormatting.DARK_PURPLE  + StringHelper.translateString("zanite_scaling", "tooltip", SpartanCompatability.MODID));
		if(GuiScreen.isShiftKeyDown())
		{
			tooltip.add(TextFormatting.ITALIC + " " + StringHelper.translateString("zanite_scaling.desc", "tooltip", SpartanCompatability.MODID));
		}
	}
	
	@Override
	public float modifyDamageDealt(ToolMaterialEx material, float baseDamage, DamageSource source, EntityLivingBase attacker, EntityLivingBase victim)
	{
		float bonus = getBonus(attacker.getHeldItemMainhand());
		return baseDamage + bonus;
	}
	
	public float getBonus(ItemStack item) {
		int currDmg = item.getItemDamage();
		int maxDmg = item.getMaxDamage();
		
		if(currDmg >= maxDmg - 50) { return 4.0f; }
		else if(currDmg >= maxDmg - 110) { return 3.0f; }
		else if(currDmg >= maxDmg - 200) { return 2.0f; }
		else if(currDmg >= maxDmg - 239) { return 1.0f; }
		else { return 0.0f;}
	}

}
