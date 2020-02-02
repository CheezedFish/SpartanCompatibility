package com.cheezedfish.spartancompat.event;

import com.cheezedfish.spartancompat.init.ItemRegistrySC;
import com.legacy.aether.entities.bosses.EntityValkyrie;
import com.legacy.aether.items.ItemsAether;
import com.legacy.aether.items.dungeon.ItemDungeonKey;
import com.oblivioussp.spartanweaponry.item.ItemSwordBase;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpartanCompatibilityEventHandler {

	@SubscribeEvent
	public void onEntityDropLoot(LivingDropsEvent event) {
		if (event.getSource() instanceof EntityDamageSource) {
			EntityLivingBase entity = event.getEntityLiving();
			EntityDamageSource source = (EntityDamageSource) event.getSource();

			if (source.getImmediateSource() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) source.getImmediateSource();
				Item currentItem = player.inventory.getCurrentItem().getItem();
				
				if(currentItem instanceof ItemSwordBase) {
					if (((ItemSwordBase) currentItem).getMaterialEx() == ItemRegistrySC.materialSkyroot && !(entity instanceof EntityPlayer) && !(entity instanceof EntityWither) && !(entity instanceof EntityValkyrie)) {
						for (EntityItem items : event.getDrops()) {
							ItemStack stack = items.getItem();

							if (!(stack.getItem() instanceof ItemDungeonKey) && stack.getItem() != ItemsAether.victory_medal && stack.getItem() != Items.SKULL) {
								EntityItem item = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, items.getItem());

								entity.world.spawnEntity(item);
							}
						}
					}
				}
			}
		}
	}


}
