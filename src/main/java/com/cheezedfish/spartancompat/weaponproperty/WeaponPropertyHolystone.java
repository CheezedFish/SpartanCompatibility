package com.cheezedfish.spartancompat.weaponproperty;

import java.util.Random;

import com.legacy.aether.items.ItemsAether;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponPropertyWithCallback;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

// Replicates ambrosium drop functionality of Holystone weapons

public class WeaponPropertyHolystone extends WeaponPropertyWithCallback {

	public WeaponPropertyHolystone(String propType, String propModId) {
		super(propType, propModId);
	}

	// TBI Requires some more functionality
	
	@Override
	public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
        if ((new Random()).nextInt(20) == 0 && attacker != null && attacker instanceof EntityPlayer && target.hurtTime > 0 && target.deathTime <= 0)
        {
            if (!target.world.isRemote)
            {
            	target.dropItem(ItemsAether.ambrosium_shard, 1);
            }
        }

        stack.damageItem(1, attacker);
    }
	
}
