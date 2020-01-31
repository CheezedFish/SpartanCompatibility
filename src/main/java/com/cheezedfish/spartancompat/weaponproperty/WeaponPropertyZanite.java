package com.cheezedfish.spartancompat.weaponproperty;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;


// Replicates scaling damage functionality of zanite weapons

public class WeaponPropertyZanite extends WeaponPropertyWithCallback {

	public WeaponPropertyZanite(String propType, String propModId) {
		super(propType, propModId);
	}
	
	@Override
	public float modifyDamageTaken(ToolMaterialEx material, float baseDamage, DamageSource source, EntityLivingBase attacker, EntityLivingBase victim)
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
