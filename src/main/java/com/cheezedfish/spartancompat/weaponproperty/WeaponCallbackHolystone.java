package com.cheezedfish.spartancompat.weaponproperty;

import java.util.List;
import java.util.Random;

import com.cheezedfish.spartancompat.SpartanCompatibility;
import com.legacy.aether.items.ItemsAether;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WeaponCallbackHolystone implements IWeaponCallback {
	
	
	public WeaponCallbackHolystone() { }

	@Override
	public void onTooltip(ToolMaterialEx arg0, ItemStack arg1, World arg2, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(TextFormatting.YELLOW  + StringHelper.translateString("holystone_ambrosium", "tooltip", SpartanCompatibility.MODID));
		if(GuiScreen.isShiftKeyDown())
		{
			tooltip.add(TextFormatting.ITALIC + " " + StringHelper.translateString("holystone_ambrosium.desc", "tooltip", SpartanCompatibility.MODID));
		}
	}
	
	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
		if(projectile != null) {
	        if ((new Random()).nextInt(20) == 0 && attacker != null && attacker instanceof EntityPlayer && target.hurtTime > 0 && target.deathTime <= 0)
	        {
	            if (!target.world.isRemote)
	            {
	            	target.dropItem(ItemsAether.ambrosium_shard, 1);
	            }
	        }
		}
	}

}
