package com.cheezedfish.spartancompat.weaponproperty;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityVelocity;

// Replicates launching functionality of gravitite weapons

public class WeaponPropertyGravitite extends WeaponPropertyWithCallback {

	public WeaponPropertyGravitite(String propType, String propModId) {
		super(propType, propModId);
	}

	@Override
	public void  onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
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
