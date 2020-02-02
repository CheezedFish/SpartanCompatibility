package com.cheezedfish.spartancompat.init;

import java.util.ArrayList;

import alexndr.api.config.types.ConfigTool;
import alexndr.plugins.SimpleOres.Settings;
import betterwithmods.common.BWMItems;
import betterwithmods.common.items.ItemMaterial;
import betterwithmods.module.gameplay.AnvilRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import com.cheezedfish.spartancompat.SpartanCompatibility;
import com.cheezedfish.spartancompat.item.ItemSC;
import com.cheezedfish.spartancompat.weaponproperty.WeaponCallbackGravitite;
import com.cheezedfish.spartancompat.weaponproperty.WeaponCallbackHolystone;
import com.cheezedfish.spartancompat.weaponproperty.WeaponCallbackSkyroot;
import com.cheezedfish.spartancompat.weaponproperty.WeaponCallbackZanite;
import com.cheezedfish.spartancompat.weaponproperty.WeaponPropertySC;
import com.legacy.aether.api.enchantments.AetherEnchantment;
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
	
	public static ToolMaterialEx materialSoulSteel;
	
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
			materialSkyroot = new ToolMaterialEx("skyroot", ToolMaterial.WOOD, "plankSkyroot", SpartanCompatibility.MODID, ToolMaterial.WOOD.getAttackDamage(), WeaponPropertySC.SkyrootDoubleDrops);
			materialHolystone = new ToolMaterialEx("holystone", ToolMaterial.STONE, "blockHolystone", SpartanCompatibility.MODID, ToolMaterial.STONE.getAttackDamage(), WeaponPropertySC.HolystoneAmbrosium);
			materialZanite = new ToolMaterialEx("zanite", ToolMaterial.IRON, "gemZanite", SpartanCompatibility.MODID, ToolMaterial.WOOD.getAttackDamage(), WeaponPropertySC.ZaniteScaling);
			materialGravitite = new ToolMaterialEx("gravitite", ToolMaterial.DIAMOND, "blockEnchantedGravitite", SpartanCompatibility.MODID, ToolMaterial.DIAMOND.getAttackDamage(), WeaponPropertySC.GravititeLaunching);
		}
		if(Loader.isModLoaded("betterwithmods")) {
			materialSoulSteel = new ToolMaterialEx("soulforged_steel", BWMItems.SOULFORGED_STEEL, "ingotSoulforgedSteel", SpartanCompatibility.MODID, BWMItems.SOULFORGED_STEEL.getAttackDamage());
		}
	}
	
	public ItemRegistrySC() {}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> registry = ev.getRegistry();
		
		if(Loader.isModLoaded("simpleores")) {
			registerSimpleOresItems();
		}
		if(Loader.isModLoaded("aether_legacy")) {
			registerAetherItems();
			
			String[] itemsToCreate = new String[] {"handle_skyroot", "pole_skyroot"};
			for(int i = 0; i < itemsToCreate.length; i++) {
				ItemSC item = new ItemSC(itemsToCreate[i]);
				registry.register(item);
				SpartanWeaponryAPI.addItemModelToRegistry(item);
			}
		}
		if(Loader.isModLoaded("betterwithmods")) {
			registerBWMItems();
		}
		
		for(Item weapon : weapons)
		{
			registry.register(weapon);
			SpartanWeaponryAPI.addItemModelToRegistry(weapon);
		}
		
		if(Loader.isModLoaded("betterwithmods")) {
			registerBWMRecipes();
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
		   katanaAdamantium, katanaMythril, katanaOnyx,
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

		daggerAdamantium = SpartanWeaponryAPI.createDagger(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerMythril = SpartanWeaponryAPI.createDagger(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerOnyx = SpartanWeaponryAPI.createDagger(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(daggerAdamantium, daggerMythril, daggerOnyx);
		
		longswordAdamantium = SpartanWeaponryAPI.createLongsword(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordMythril = SpartanWeaponryAPI.createLongsword(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordOnyx = SpartanWeaponryAPI.createLongsword(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(longswordAdamantium, longswordMythril, longswordOnyx);
		
		halberdAdamantium = SpartanWeaponryAPI.createHalberd(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		halberdMythril = SpartanWeaponryAPI.createHalberd(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		halberdOnyx = SpartanWeaponryAPI.createHalberd(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(halberdAdamantium, halberdMythril, halberdOnyx);
        
		saberAdamantium = SpartanWeaponryAPI.createSaber(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberMythril = SpartanWeaponryAPI.createSaber(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberOnyx = SpartanWeaponryAPI.createSaber(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(saberAdamantium, saberMythril, saberOnyx);
        
		rapierAdamantium = SpartanWeaponryAPI.createRapier(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		rapierMythril = SpartanWeaponryAPI.createRapier(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		rapierOnyx = SpartanWeaponryAPI.createRapier(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(rapierAdamantium, rapierMythril, rapierOnyx);
        
		greatswordAdamantium = SpartanWeaponryAPI.createGreatsword(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		greatswordMythril = SpartanWeaponryAPI.createGreatsword(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		greatswordOnyx = SpartanWeaponryAPI.createGreatsword(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(greatswordAdamantium, greatswordMythril, greatswordOnyx);
        
		hammerAdamantium = SpartanWeaponryAPI.createHammer(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		hammerMythril = SpartanWeaponryAPI.createHammer(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		hammerOnyx = SpartanWeaponryAPI.createHammer(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(hammerAdamantium, hammerMythril, hammerOnyx);
        
		warhammerAdamantium = SpartanWeaponryAPI.createWarhammer(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		warhammerMythril = SpartanWeaponryAPI.createWarhammer(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		warhammerOnyx = SpartanWeaponryAPI.createWarhammer(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(warhammerAdamantium, warhammerMythril, warhammerOnyx);
        
		spearAdamantium = SpartanWeaponryAPI.createSpear(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearMythril = SpartanWeaponryAPI.createSpear(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearOnyx = SpartanWeaponryAPI.createSpear(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(spearAdamantium, spearMythril, spearOnyx);
        
		pikeAdamantium = SpartanWeaponryAPI.createPike(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeMythril = SpartanWeaponryAPI.createPike(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeOnyx = SpartanWeaponryAPI.createPike(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(pikeAdamantium, pikeMythril, pikeOnyx);
		
		katanaAdamantium = SpartanWeaponryAPI.createKatana(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		katanaMythril = SpartanWeaponryAPI.createKatana(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		katanaOnyx = SpartanWeaponryAPI.createKatana(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(katanaAdamantium, katanaMythril, katanaOnyx);
        
		lanceAdamantium = SpartanWeaponryAPI.createLance(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		lanceMythril = SpartanWeaponryAPI.createLance(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		lanceOnyx = SpartanWeaponryAPI.createLance(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(lanceAdamantium, lanceMythril, lanceOnyx);
        
		longbowAdamantium = SpartanWeaponryAPI.createLongbow(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		longbowMythril = SpartanWeaponryAPI.createLongbow(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		longbowOnyx = SpartanWeaponryAPI.createLongbow(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		addWeaponsToRegister(longbowAdamantium, longbowMythril, longbowOnyx);
        
		crossbowAdamantium = SpartanWeaponryAPI.createCrossbow(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		crossbowMythril = SpartanWeaponryAPI.createCrossbow(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		crossbowOnyx = SpartanWeaponryAPI.createCrossbow(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		addWeaponsToRegister(crossbowAdamantium, crossbowMythril, crossbowOnyx);
        
		throwingKnifeAdamantium = SpartanWeaponryAPI.createThrowingKnife(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingKnifeMythril = SpartanWeaponryAPI.createThrowingKnife(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingKnifeOnyx = SpartanWeaponryAPI.createThrowingKnife(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(throwingKnifeAdamantium, throwingKnifeMythril, throwingKnifeOnyx);
        
		throwingAxeAdamantium = SpartanWeaponryAPI.createThrowingAxe(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingAxeMythril = SpartanWeaponryAPI.createThrowingAxe(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingAxeOnyx = SpartanWeaponryAPI.createThrowingAxe(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(throwingAxeAdamantium, throwingAxeMythril, throwingAxeOnyx);
        
		javelinAdamantium = SpartanWeaponryAPI.createJavelin(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinMythril = SpartanWeaponryAPI.createJavelin(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinOnyx = SpartanWeaponryAPI.createJavelin(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(javelinAdamantium, javelinMythril, javelinOnyx);
        
		boomerangAdamantium = SpartanWeaponryAPI.createBoomerang(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		boomerangMythril = SpartanWeaponryAPI.createBoomerang(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		boomerangOnyx = SpartanWeaponryAPI.createBoomerang(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(boomerangAdamantium, boomerangMythril, boomerangOnyx);
        
		battleaxeAdamantium = SpartanWeaponryAPI.createBattleaxe(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		battleaxeMythril = SpartanWeaponryAPI.createBattleaxe(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		battleaxeOnyx = SpartanWeaponryAPI.createBattleaxe(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(battleaxeAdamantium, battleaxeMythril, battleaxeOnyx);
        
		maceAdamantium = SpartanWeaponryAPI.createMace(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		maceMythril = SpartanWeaponryAPI.createMace(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		maceOnyx = SpartanWeaponryAPI.createMace(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(maceAdamantium, maceMythril, maceOnyx);
        
		glaiveAdamantium = SpartanWeaponryAPI.createGlaive(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveMythril = SpartanWeaponryAPI.createGlaive(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveOnyx = SpartanWeaponryAPI.createGlaive(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegister(glaiveAdamantium, glaiveMythril, glaiveOnyx);
        
		staffAdamantium = SpartanWeaponryAPI.createQuarterstaff(materialAdamantium, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		staffMythril = SpartanWeaponryAPI.createQuarterstaff(materialMythril, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		staffOnyx = SpartanWeaponryAPI.createQuarterstaff(materialOnyx, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
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
		   katanaSkyroot, katanaHolystone, katanaZanite, katanaGravitite,
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

		daggerSkyroot = SpartanWeaponryAPI.createDagger(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerHolystone = SpartanWeaponryAPI.createDagger(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerZanite = SpartanWeaponryAPI.createDagger(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		daggerGravitite = SpartanWeaponryAPI.createDagger(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(daggerSkyroot, daggerHolystone, daggerZanite, daggerGravitite);
		
		longswordSkyroot = SpartanWeaponryAPI.createLongsword(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordHolystone = SpartanWeaponryAPI.createLongsword(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordZanite = SpartanWeaponryAPI.createLongsword(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		longswordGravitite = SpartanWeaponryAPI.createLongsword(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(longswordSkyroot, longswordHolystone, longswordZanite, longswordGravitite);
		
		halberdSkyroot = SpartanWeaponryAPI.createHalberd(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		halberdHolystone = SpartanWeaponryAPI.createHalberd(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		halberdZanite = SpartanWeaponryAPI.createHalberd(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		halberdGravitite = SpartanWeaponryAPI.createHalberd(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(halberdSkyroot, halberdHolystone, halberdZanite, halberdGravitite);
		
		saberSkyroot = SpartanWeaponryAPI.createSaber(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberHolystone = SpartanWeaponryAPI.createSaber(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberZanite = SpartanWeaponryAPI.createSaber(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		saberGravitite = SpartanWeaponryAPI.createSaber(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(saberSkyroot, saberHolystone, saberZanite, saberGravitite);
		
		rapierSkyroot = SpartanWeaponryAPI.createRapier(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		rapierHolystone = SpartanWeaponryAPI.createRapier(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		rapierZanite = SpartanWeaponryAPI.createRapier(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		rapierGravitite = SpartanWeaponryAPI.createRapier(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(rapierSkyroot, rapierHolystone, rapierZanite, rapierGravitite);
		
		greatswordSkyroot = SpartanWeaponryAPI.createGreatsword(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		greatswordHolystone = SpartanWeaponryAPI.createGreatsword(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		greatswordZanite = SpartanWeaponryAPI.createGreatsword(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		greatswordGravitite = SpartanWeaponryAPI.createGreatsword(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(greatswordSkyroot, greatswordHolystone, greatswordZanite, greatswordGravitite);
		
		hammerSkyroot = SpartanWeaponryAPI.createHammer(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		hammerHolystone = SpartanWeaponryAPI.createHammer(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		hammerZanite = SpartanWeaponryAPI.createHammer(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		hammerGravitite = SpartanWeaponryAPI.createHammer(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(hammerSkyroot, hammerHolystone, hammerZanite, hammerGravitite);
		
		warhammerSkyroot = SpartanWeaponryAPI.createWarhammer(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		warhammerHolystone = SpartanWeaponryAPI.createWarhammer(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		warhammerZanite = SpartanWeaponryAPI.createWarhammer(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		warhammerGravitite = SpartanWeaponryAPI.createWarhammer(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(warhammerSkyroot, warhammerHolystone, warhammerZanite, warhammerGravitite);
		
		spearSkyroot = SpartanWeaponryAPI.createSpear(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearHolystone = SpartanWeaponryAPI.createSpear(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearZanite = SpartanWeaponryAPI.createSpear(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearGravitite = SpartanWeaponryAPI.createSpear(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(spearSkyroot, spearHolystone, spearZanite, spearGravitite);
		
		pikeSkyroot = SpartanWeaponryAPI.createPike(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeHolystone = SpartanWeaponryAPI.createPike(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeZanite = SpartanWeaponryAPI.createPike(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeGravitite = SpartanWeaponryAPI.createPike(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(pikeSkyroot, pikeHolystone, pikeZanite, pikeGravitite);
		
		katanaSkyroot = SpartanWeaponryAPI.createKatana(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		katanaHolystone = SpartanWeaponryAPI.createKatana(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		katanaZanite = SpartanWeaponryAPI.createKatana(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		katanaGravitite = SpartanWeaponryAPI.createKatana(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(katanaSkyroot, katanaHolystone, katanaZanite, katanaGravitite);
		
		lanceSkyroot = SpartanWeaponryAPI.createLance(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		lanceHolystone = SpartanWeaponryAPI.createLance(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		lanceZanite = SpartanWeaponryAPI.createLance(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		lanceGravitite = SpartanWeaponryAPI.createLance(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(lanceSkyroot, lanceHolystone, lanceZanite, lanceGravitite);
		
		longbowSkyroot = SpartanWeaponryAPI.createLongbow(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, new WeaponCallbackSkyroot());
		longbowHolystone = SpartanWeaponryAPI.createLongbow(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, new WeaponCallbackHolystone());
		longbowZanite = SpartanWeaponryAPI.createLongbow(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, new WeaponCallbackZanite());
		longbowGravitite = SpartanWeaponryAPI.createLongbow(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, new WeaponCallbackGravitite());
		addWeaponsToRegisterAether(longbowSkyroot, longbowHolystone, longbowZanite, longbowGravitite);
		
		crossbowSkyroot = SpartanWeaponryAPI.createCrossbow(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, new WeaponCallbackSkyroot());
		crossbowHolystone = SpartanWeaponryAPI.createCrossbow(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, new WeaponCallbackHolystone());
		crossbowZanite = SpartanWeaponryAPI.createCrossbow(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, new WeaponCallbackZanite());
		crossbowGravitite = SpartanWeaponryAPI.createCrossbow(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, new WeaponCallbackGravitite());
		addWeaponsToRegisterAether(crossbowSkyroot, crossbowHolystone, crossbowZanite, crossbowGravitite);
		
		throwingKnifeSkyroot = SpartanWeaponryAPI.createThrowingKnife(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingKnifeHolystone = SpartanWeaponryAPI.createThrowingKnife(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingKnifeZanite = SpartanWeaponryAPI.createThrowingKnife(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingKnifeGravitite = SpartanWeaponryAPI.createThrowingKnife(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(throwingKnifeSkyroot, throwingKnifeHolystone, throwingKnifeZanite, throwingKnifeGravitite);
		
		throwingAxeSkyroot = SpartanWeaponryAPI.createThrowingAxe(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingAxeHolystone = SpartanWeaponryAPI.createThrowingAxe(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingAxeZanite = SpartanWeaponryAPI.createThrowingAxe(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingAxeGravitite = SpartanWeaponryAPI.createThrowingAxe(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(throwingAxeSkyroot, throwingAxeHolystone, throwingAxeZanite, throwingAxeGravitite);
		
		javelinSkyroot = SpartanWeaponryAPI.createJavelin(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinHolystone = SpartanWeaponryAPI.createJavelin(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinZanite = SpartanWeaponryAPI.createJavelin(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinGravitite = SpartanWeaponryAPI.createJavelin(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(javelinSkyroot, javelinHolystone, javelinZanite, javelinGravitite);
		
		boomerangSkyroot = SpartanWeaponryAPI.createBoomerang(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		boomerangHolystone = SpartanWeaponryAPI.createBoomerang(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		boomerangZanite = SpartanWeaponryAPI.createBoomerang(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		boomerangGravitite = SpartanWeaponryAPI.createBoomerang(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(boomerangSkyroot, boomerangHolystone, boomerangZanite, boomerangGravitite);
		
		battleaxeSkyroot = SpartanWeaponryAPI.createBattleaxe(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		battleaxeHolystone = SpartanWeaponryAPI.createBattleaxe(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		battleaxeZanite = SpartanWeaponryAPI.createBattleaxe(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		battleaxeGravitite = SpartanWeaponryAPI.createBattleaxe(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(battleaxeSkyroot, battleaxeHolystone, battleaxeZanite, battleaxeGravitite);
		
		maceSkyroot = SpartanWeaponryAPI.createMace(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		maceHolystone = SpartanWeaponryAPI.createMace(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		maceZanite = SpartanWeaponryAPI.createMace(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		maceGravitite = SpartanWeaponryAPI.createMace(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(maceSkyroot, maceHolystone, maceZanite, maceGravitite);
		
		glaiveSkyroot = SpartanWeaponryAPI.createGlaive(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveHolystone = SpartanWeaponryAPI.createGlaive(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveZanite = SpartanWeaponryAPI.createGlaive(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveGravitite = SpartanWeaponryAPI.createGlaive(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(glaiveSkyroot, glaiveHolystone, glaiveZanite, glaiveGravitite);
		
		staffSkyroot = SpartanWeaponryAPI.createQuarterstaff(materialSkyroot, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		staffHolystone = SpartanWeaponryAPI.createQuarterstaff(materialHolystone, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		staffZanite = SpartanWeaponryAPI.createQuarterstaff(materialZanite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		staffGravitite = SpartanWeaponryAPI.createQuarterstaff(materialGravitite, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		addWeaponsToRegisterAether(staffSkyroot, staffHolystone, staffZanite, staffGravitite);

	}
	
	public static void registerBWMItems() {
		Item daggerSoulSteel,
		   longswordSoulSteel,
		   halberdSoulSteel,
		   saberSoulSteel,
		   rapierSoulSteel,
		   greatswordSoulSteel,
		   hammerSoulSteel,
		   warhammerSoulSteel,
		   spearSoulSteel,
		   pikeSoulSteel,
		   katanaSoulSteel,
		   lanceSoulSteel,
		   longbowSoulSteel,
		   crossbowSoulSteel,
		   throwingKnifeSoulSteel,
		   throwingAxeSoulSteel,
		   javelinSoulSteel,
		   boomerangSoulSteel,
		   battleaxeSoulSteel,
		   maceSoulSteel,
		   glaiveSoulSteel,
		   staffSoulSteel;
		
		daggerSoulSteel = SpartanWeaponryAPI.createDagger(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);	
		longswordSoulSteel = SpartanWeaponryAPI.createLongsword(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);		
		halberdSoulSteel = SpartanWeaponryAPI.createHalberd(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);	
		saberSoulSteel = SpartanWeaponryAPI.createSaber(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);	
		rapierSoulSteel = SpartanWeaponryAPI.createRapier(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		greatswordSoulSteel = SpartanWeaponryAPI.createGreatsword(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);	
		hammerSoulSteel = SpartanWeaponryAPI.createHammer(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		warhammerSoulSteel = SpartanWeaponryAPI.createWarhammer(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		spearSoulSteel = SpartanWeaponryAPI.createSpear(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		pikeSoulSteel = SpartanWeaponryAPI.createPike(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);	
		katanaSoulSteel = SpartanWeaponryAPI.createKatana(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		lanceSoulSteel = SpartanWeaponryAPI.createLance(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		longbowSoulSteel = SpartanWeaponryAPI.createLongbow(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		crossbowSoulSteel = SpartanWeaponryAPI.createCrossbow(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD, null);
		throwingKnifeSoulSteel = SpartanWeaponryAPI.createThrowingKnife(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		throwingAxeSoulSteel = SpartanWeaponryAPI.createThrowingAxe(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		javelinSoulSteel = SpartanWeaponryAPI.createJavelin(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);	
		boomerangSoulSteel = SpartanWeaponryAPI.createBoomerang(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		battleaxeSoulSteel = SpartanWeaponryAPI.createBattleaxe(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		maceSoulSteel = SpartanWeaponryAPI.createMace(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		glaiveSoulSteel = SpartanWeaponryAPI.createGlaive(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		staffSoulSteel = SpartanWeaponryAPI.createQuarterstaff(materialSoulSteel, SpartanCompatibility.MODID, CreativeTabsSW.TAB_SW_MOD);
		
		addWeaponsToRegister(daggerSoulSteel, longswordSoulSteel, halberdSoulSteel, saberSoulSteel, rapierSoulSteel, greatswordSoulSteel,
				hammerSoulSteel, warhammerSoulSteel, spearSoulSteel, pikeSoulSteel, katanaSoulSteel, lanceSoulSteel, longbowSoulSteel,
				crossbowSoulSteel, throwingKnifeSoulSteel, throwingAxeSoulSteel, javelinSoulSteel, boomerangSoulSteel,
				battleaxeSoulSteel, maceSoulSteel, glaiveSoulSteel, staffSoulSteel);
	}
	
private static void registerRecipeBWM(String itemName, String... crafting) {
    Item weapon = ForgeRegistries.ITEMS.getValue(new ResourceLocation(SpartanCompatibility.MODID, itemName));
    if (weapon != null){
        AnvilRecipes.addSteelShapedRecipe(new ResourceLocation(SpartanCompatibility.MODID, itemName),
                new ItemStack(weapon), crafting,
                'X', "ingotSoulforgedSteel",
                'H', ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.HAFT));
    }
}

private static void registerLongbowRecipeBWM(String itemName, String... crafting) {
    Item weapon = ForgeRegistries.ITEMS.getValue(new ResourceLocation(SpartanCompatibility.MODID, itemName));
    if (weapon != null){
        AnvilRecipes.addSteelShapedRecipe(new ResourceLocation(SpartanCompatibility.MODID, itemName),
                new ItemStack(weapon), crafting,
                'X', "ingotSoulforgedSteel",
                'H', ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.HAFT),
                'S', ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.HEMP_FIBERS));
    }
}

private static void registerCrossbowRecipeBWM(String itemName, String... crafting) {
    Item weapon = ForgeRegistries.ITEMS.getValue(new ResourceLocation(SpartanCompatibility.MODID, itemName));
    if (weapon != null){
        AnvilRecipes.addSteelShapedRecipe(new ResourceLocation(SpartanCompatibility.MODID, itemName),
                new ItemStack(weapon), crafting,
                'X', "ingotSoulforgedSteel",
                'H', ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.HAFT),
                'S', ItemMaterial.getMaterial(ItemMaterial.EnumMaterial.HEMP_FIBERS),
                'W', "logWood",
                'B', Items.BOW);
    }
}
	
private static void registerBWMRecipes() {
    registerRecipeBWM("dagger_soulforged_steel","X   ", "H   " );
    registerRecipeBWM("longsword_soulforged_steel", " X  ", " X  ", " X  ", "XHX ");
    registerRecipeBWM("halberd_soulforged_steel", "  XX", " XH ", " H  ", "X   ");
    registerRecipeBWM("saber_soulforged_steel", " X  ", " X  ", " X  ", "XH  ");
    registerRecipeBWM("rapier_soulforged_steel", "   X", "  X ", "XX  ", "HX  ");
    registerRecipeBWM("greatsword_soulforged_steel", " X  ", "XXX ", "XXX ", "XHX ");
    registerRecipeBWM("hammer_soulforged_steel", "XXXX", "XXXX", " HH ", " HH ");
    registerRecipeBWM("warhammer_soulforged_steel", " XXX", " XXX", "  H ", "  H ");
    registerRecipeBWM("spear_soulforged_steel", "X   ", "X   ", "H   ");
    registerRecipeBWM("pike_soulforged_steel", " X  ", " X  ", " H  ", " H  ");
    registerRecipeBWM("katana_soulforged_steel", "   X", "  X ", " X  ", "H   ");
    registerRecipeBWM("lance_soulforged_steel","   X", "  H ", "XH  ", "HX  ");
    registerLongbowRecipeBWM("longbow_soulforged_steel", "HWXX", "W  S", "X  S", "XSS ");
    registerCrossbowRecipeBWM("crossbow_soulforged_steel", "BSXX", "SWWS", "XWHS", "XSSH");
    registerRecipeBWM("throwing_knife_soulforged_steel", "HX");
    registerRecipeBWM("throwing_axe_soulforged_steel", "HX  ", " X  ");
    registerRecipeBWM("javelin_soulforged_steel", "HXX");
    registerRecipeBWM("boomerang_soulforged_steel", "XHHH", "H   ", "H   ", "H   ");
    registerRecipeBWM("battleaxe_soulforged_steel","X  X", "XXXX", "XH X", " H  ");
    registerRecipeBWM("mace_soulforged_steel"," XXX", " XHX", " HXX", "H   ");
    registerRecipeBWM("glaive_soulforged_steel", "  XX", "X HX", " H  ", "H   ");
    registerRecipeBWM("quarterstaff_soulforged_steel", "   X", "  H ", " H  ", "X   ");
}
	
	
protected static void addWeaponsToRegister(Item... weaponsToAdd) {
	for(Item weapon : weaponsToAdd) {
		if(weapon != null)
			weapons.add(weapon);
	}
}

/*
 * Handles registering Aether enchantment repairs for items.
 */
protected static void addWeaponsToRegisterAether(Item... weaponsToAdd) {
	int[] enchantCosts = new int[] {225, 550, 2250, 5500};
	int idx = 0;
	IForgeRegistry<AetherEnchantment> registry = GameRegistry.findRegistry(AetherEnchantment.class);
	
	for(Item weapon : weaponsToAdd) {
		if(weapon != null) {
			registry.register(new AetherEnchantment(weapon, enchantCosts[idx]));
			weapons.add(weapon);
		}
		idx++;
	}
}

}
