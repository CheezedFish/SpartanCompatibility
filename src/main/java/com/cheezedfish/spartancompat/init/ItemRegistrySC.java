package com.cheezedfish.spartancompat.init;

import java.util.ArrayList;

import alexndr.api.config.types.ConfigTool;
import alexndr.plugins.SimpleOres.Settings;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import com.cheezedfish.spartancompat.SpartanCompatability;
import com.cheezedfish.spartancompat.weaponproperty.WeaponCallbackFlame;
import com.cheezedfish.spartancompat.weaponproperty.WeaponPropertySkyroot;
import com.cheezedfish.spartancompat.weaponproperty.WeaponPropertyHolystone;
import com.cheezedfish.spartancompat.weaponproperty.WeaponPropertySC;
import com.cheezedfish.spartancompat.weaponproperty.WeaponPropertyZanite;
import com.cheezedfish.spartancompat.weaponproperty.WeaponPropertyZanite;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.client.gui.CreativeTabsSW;
import com.oblivioussp.spartanweaponry.util.LogHelper;

/**
 * Handles item registration for supported materials
 * @author CheezedFish
 *
 */

@Mod.EventBusSubscriber
public class ItemRegistrySC {
	
	public static ToolMaterialEx materialAdamantium,
	materialMythril,
	materialOnyx;
	
	public static ToolMaterialEx materialSkyroot,
	materialHolystone,
	materialZanite,
	materialGravitite;
	
	public static ArrayList<Item> weapons = new ArrayList<Item>();			   
	
	static {
		if(Loader.isModLoaded("simpleores")) {
			ConfigTool adamantium = Settings.adamantiumTools;
			materialAdamantium = new ToolMaterialEx("adamantium", "ingotAdamantium", 0x0, 0x0, adamantium.getHarvestLevel(), adamantium.getUses(), adamantium.getHarvestSpeed(), adamantium.getDamageVsEntity(), adamantium.getEnchantability());
			ConfigTool mythril = Settings.mythrilTools;
			materialMythril = new ToolMaterialEx("mythril", "ingotMythril", 0x0, 0x0, mythril.getHarvestLevel(), mythril.getUses(), mythril.getHarvestSpeed(), mythril.getDamageVsEntity(), mythril.getEnchantability());
			ConfigTool onyx = Settings.onyxTools;
			materialOnyx = new ToolMaterialEx("onyx", "gemOnyx", 0x0, 0x0, onyx.getHarvestLevel(), onyx.getUses(), onyx.getHarvestSpeed(), onyx.getDamageVsEntity(), onyx.getEnchantability());
		}
		if(Loader.isModLoaded("aether_legacy")) {
			materialSkyroot = new ToolMaterialEx("skyroot", ToolMaterial.WOOD, "plankWood", SpartanCompatability.MODID, ToolMaterial.WOOD.getAttackDamage(), WeaponPropertySC.SkyrootDoubleDrops);
			materialHolystone = new ToolMaterialEx("holystone", ToolMaterial.STONE, "cobblestone", SpartanCompatability.MODID, ToolMaterial.STONE.getAttackDamage(), WeaponPropertySC.HolystoneAmbrosium);
			materialZanite = new ToolMaterialEx("zanite", ToolMaterial.IRON, "gemZanite", SpartanCompatability.MODID, ToolMaterial.WOOD.getAttackDamage(), WeaponPropertySC.ZaniteScaling);
			materialGravitite = new ToolMaterialEx("gravitite", ToolMaterial.DIAMOND, "blockGravitite", SpartanCompatability.MODID, ToolMaterial.DIAMOND.getAttackDamage(), WeaponPropertySC.GravititeLaunching);
		}
	}
	
	public ItemRegistrySC() {}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> registry = ev.getRegistry();
		
		if(Loader.isModLoaded("simpleores")) {
			registerSimpleOresItems();
		}
		if(Loader.isModLoaded("aether_legacy"))
		{
			registerAetherItems();
		}
		
		for(Item weapon : weapons)
		{
			registry.register(weapon);
			SpartanWeaponryAPI.addItemModelToRegistry(weapon);
		}
		
		LogHelper.info("Spartans Compatability Items Registered!");
	}
	
	public static void registerSimpleOresItems() {
		Item daggerAdamantium, daggerMythril, daggerOnyx,
		   longswordAdamantium, longswordMythril, longswordOnyx,
		   halberdAdamantium, halberdMythril, halberdOnyx,
		   saberAdamantium, saberMythril, saberOnyx,
		   rapierAdamantium, rapierMythril, rapierOnyx,
		   greatswordAdamantium, greatswordMythril, greatswordOnyx,
		   hammerAdamantium, hammerMythril, hammerOnyx,
		   warhammerAdamantium, warhammerMythril, warhammerOnyx,
		   spearAdamantium, spearMythril, spearOnyx,
		   pikeAdamantium, pikeMythril, pikeOnyx,
		   lanceAdamantium, lanceMythril, lanceOnyx,
		   longbowAdamantium, longbowMythril, longbowOnyx,
		   crossbowAdamantium, crossbowMythril, crossbowOnyx,
		   throwingKnifeAdamantium, throwingKnifeMythril, throwingKnifeOnyx,
		   throwingAxeAdamantium, throwingAxeMythril, throwingAxeOnyx,
		   javelinAdamantium, javelinMythril, javelinOnyx,
		   boomerangAdamantium, boomerangMythril, boomerangOnyx,
		   battleaxeAdamantium, battleaxeMythril, battleaxeOnyx,
		   maceAdamantium, maceMythril, maceOnyx,
		   glaiveAdamantium, glaiveMythril, glaiveOnyx, 
		   staffAdamantium, staffMythril, staffOnyx;

		daggerAdamantium = SpartanWeaponryAPI.createDagger(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerMythril = SpartanWeaponryAPI.createDagger(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerOnyx = SpartanWeaponryAPI.createDagger(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(daggerAdamantium, daggerMythril, daggerOnyx);
		
		longswordAdamantium = SpartanWeaponryAPI.createLongsword(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordMythril = SpartanWeaponryAPI.createLongsword(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordOnyx = SpartanWeaponryAPI.createLongsword(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(longswordAdamantium, longswordMythril, longswordOnyx);
		
		halberdAdamantium = SpartanWeaponryAPI.createHalberd(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		halberdMythril = SpartanWeaponryAPI.createHalberd(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		halberdOnyx = SpartanWeaponryAPI.createHalberd(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(halberdAdamantium, halberdMythril, halberdOnyx);
        
		saberAdamantium = SpartanWeaponryAPI.createSaber(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberMythril = SpartanWeaponryAPI.createSaber(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberOnyx = SpartanWeaponryAPI.createSaber(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(saberAdamantium, saberMythril, saberOnyx);
        
		rapierAdamantium = SpartanWeaponryAPI.createRapier(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		rapierMythril = SpartanWeaponryAPI.createRapier(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		rapierOnyx = SpartanWeaponryAPI.createRapier(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(rapierAdamantium, rapierMythril, rapierOnyx);
        
		greatswordAdamantium = SpartanWeaponryAPI.createGreatsword(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		greatswordMythril = SpartanWeaponryAPI.createGreatsword(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		greatswordOnyx = SpartanWeaponryAPI.createGreatsword(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(greatswordAdamantium, greatswordMythril, greatswordOnyx);
        
		hammerAdamantium = SpartanWeaponryAPI.createHammer(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		hammerMythril = SpartanWeaponryAPI.createHammer(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		hammerOnyx = SpartanWeaponryAPI.createHammer(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(hammerAdamantium, hammerMythril, hammerOnyx);
        
		warhammerAdamantium = SpartanWeaponryAPI.createWarhammer(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		warhammerMythril = SpartanWeaponryAPI.createWarhammer(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		warhammerOnyx = SpartanWeaponryAPI.createWarhammer(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(warhammerAdamantium, warhammerMythril, warhammerOnyx);
        
		spearAdamantium = SpartanWeaponryAPI.createSpear(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearMythril = SpartanWeaponryAPI.createSpear(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearOnyx = SpartanWeaponryAPI.createSpear(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(spearAdamantium, spearMythril, spearOnyx);
        
		pikeAdamantium = SpartanWeaponryAPI.createPike(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeMythril = SpartanWeaponryAPI.createPike(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeOnyx = SpartanWeaponryAPI.createPike(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(pikeAdamantium, pikeMythril, pikeOnyx);
        
		lanceAdamantium = SpartanWeaponryAPI.createLance(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		lanceMythril = SpartanWeaponryAPI.createLance(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		lanceOnyx = SpartanWeaponryAPI.createLance(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(lanceAdamantium, lanceMythril, lanceOnyx);
        
		longbowAdamantium = SpartanWeaponryAPI.createLongbow(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		longbowMythril = SpartanWeaponryAPI.createLongbow(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		longbowOnyx = SpartanWeaponryAPI.createLongbow(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		addWeaponsToRegister(longbowAdamantium, longbowMythril, longbowOnyx);
        
		crossbowAdamantium = SpartanWeaponryAPI.createCrossbow(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		crossbowMythril = SpartanWeaponryAPI.createCrossbow(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		crossbowOnyx = SpartanWeaponryAPI.createCrossbow(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		addWeaponsToRegister(crossbowAdamantium, crossbowMythril, crossbowOnyx);
        
		throwingKnifeAdamantium = SpartanWeaponryAPI.createThrowingKnife(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingKnifeMythril = SpartanWeaponryAPI.createThrowingKnife(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingKnifeOnyx = SpartanWeaponryAPI.createThrowingKnife(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(throwingKnifeAdamantium, throwingKnifeMythril, throwingKnifeOnyx);
        
		throwingAxeAdamantium = SpartanWeaponryAPI.createThrowingAxe(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingAxeMythril = SpartanWeaponryAPI.createThrowingAxe(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingAxeOnyx = SpartanWeaponryAPI.createThrowingAxe(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(throwingAxeAdamantium, throwingAxeMythril, throwingAxeOnyx);
        
		javelinAdamantium = SpartanWeaponryAPI.createJavelin(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinMythril = SpartanWeaponryAPI.createJavelin(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinOnyx = SpartanWeaponryAPI.createJavelin(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(javelinAdamantium, javelinMythril, javelinOnyx);
        
		boomerangAdamantium = SpartanWeaponryAPI.createBoomerang(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		boomerangMythril = SpartanWeaponryAPI.createBoomerang(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		boomerangOnyx = SpartanWeaponryAPI.createBoomerang(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(boomerangAdamantium, boomerangMythril, boomerangOnyx);
        
		battleaxeAdamantium = SpartanWeaponryAPI.createBattleaxe(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		battleaxeMythril = SpartanWeaponryAPI.createBattleaxe(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		battleaxeOnyx = SpartanWeaponryAPI.createBattleaxe(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(battleaxeAdamantium, battleaxeMythril, battleaxeOnyx);
        
		maceAdamantium = SpartanWeaponryAPI.createMace(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		maceMythril = SpartanWeaponryAPI.createMace(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		maceOnyx = SpartanWeaponryAPI.createMace(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(maceAdamantium, maceMythril, maceOnyx);
        
		glaiveAdamantium = SpartanWeaponryAPI.createGlaive(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveMythril = SpartanWeaponryAPI.createGlaive(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveOnyx = SpartanWeaponryAPI.createGlaive(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(glaiveAdamantium, glaiveMythril, glaiveOnyx);
        
		staffAdamantium = SpartanWeaponryAPI.createQuarterstaff(materialAdamantium, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		staffMythril = SpartanWeaponryAPI.createQuarterstaff(materialMythril, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		staffOnyx = SpartanWeaponryAPI.createQuarterstaff(materialOnyx, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(staffAdamantium, staffMythril, staffOnyx);
	}

	public static void registerAetherItems() {
		Item daggerSkyroot, daggerHolystone, daggerZanite, daggerGravitite,
		   longswordSkyroot, longswordHolystone, longswordZanite, longswordGravitite,
		   halberdSkyroot, halberdHolystone, halberdZanite, halberdGravitite,
		   saberSkyroot, saberHolystone, saberZanite, saberGravitite,
		   rapierSkyroot, rapierHolystone, rapierZanite, rapierGravitite,
		   greatswordSkyroot, greatswordHolystone, greatswordZanite, greatswordGravitite,
		   hammerSkyroot, hammerHolystone, hammerZanite, hammerGravitite,
		   warhammerSkyroot, warhammerHolystone, warhammerZanite, warhammerGravitite,
		   spearSkyroot, spearHolystone, spearZanite, spearGravitite,
		   pikeSkyroot, pikeHolystone, pikeZanite, pikeGravitite,
		   lanceSkyroot, lanceHolystone, lanceZanite, lanceGravitite,
		   longbowSkyroot, longbowHolystone, longbowZanite, longbowGravitite,
		   crossbowSkyroot, crossbowHolystone, crossbowZanite, crossbowGravitite,
		   throwingKnifeSkyroot, throwingKnifeHolystone, throwingKnifeZanite, throwingKnifeGravitite,
		   throwingAxeSkyroot, throwingAxeHolystone, throwingAxeZanite, throwingAxeGravitite,
		   javelinSkyroot, javelinHolystone, javelinZanite, javelinGravitite,
		   boomerangSkyroot, boomerangHolystone, boomerangZanite, boomerangGravitite,
		   battleaxeSkyroot, battleaxeHolystone, battleaxeZanite, battleaxeGravitite,
		   maceSkyroot, maceHolystone, maceZanite, maceGravitite,
		   glaiveSkyroot, glaiveHolystone, glaiveZanite, glaiveGravitite,
		   staffSkyroot, staffHolystone, staffZanite, staffGravitite;

		daggerSkyroot = SpartanWeaponryAPI.createDagger(materialSkyroot, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerHolystone = SpartanWeaponryAPI.createDagger(materialHolystone, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerZanite = SpartanWeaponryAPI.createDagger(materialZanite, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerGravitite = SpartanWeaponryAPI.createDagger(materialGravitite, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(daggerSkyroot, daggerHolystone, daggerZanite, daggerGravitite);
		
		longswordSkyroot = SpartanWeaponryAPI.createLongsword(materialSkyroot, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordHolystone = SpartanWeaponryAPI.createLongsword(materialHolystone, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordZanite = SpartanWeaponryAPI.createLongsword(materialZanite, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordGravitite = SpartanWeaponryAPI.createLongsword(materialGravitite, SpartanCompatability.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(daggerSkyroot, daggerHolystone, daggerZanite, daggerGravitite);
	}
	

protected static void addWeaponsToRegister(Item... weaponsToAdd)
{
	for(Item weapon : weaponsToAdd)
	{
		if(weapon != null)
			weapons.add(weapon);
	}
}

}
