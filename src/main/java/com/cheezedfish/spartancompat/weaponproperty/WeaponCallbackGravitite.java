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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WeaponCallbackGravitite implements IWeaponCallback {
	
	
	public WeaponCallbackGravitite() { }

	@Override
	public void onTooltip(ToolMaterialEx arg0, ItemStack arg1, World arg2, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(TextFormatting.LIGHT_PURPLE  + StringHelper.translateString("gravitite_launch", "tooltip", SpartanCompatability.MODID));
		if(GuiScreen.isShiftKeyDown())
		{
			tooltip.add(TextFormatting.ITALIC + " " + StringHelper.translateString("gravitite_launch.desc", "tooltip", SpartanCompatability.MODID));
		}
	}
	
	@Override
	public void  onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
        if(projectile == null) { // Only launch them if hit with melee
			if ((target.hurtTime > 0 || target.deathTime > 0))
	        {
	        	target.addVelocity(0.0D, 1.0D, 0.0D);
	        }
	
	        if (target instanceof EntityPlayerMP)
	        {
	            ((EntityPlayerMP)target).connection.sendPacket(new SPacketEntityVelocity(target));
	        }
        }

	}

}
