package com.cheezedfish.spartancompat.weaponproperty;

import com.cheezedfish.spartancompat.SpartanCompatibility;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;

public class WeaponPropertySC {
	
	public static final String TYPE_SKYROOT = "skyroot_double_drops";
	public static final String TYPE_HOLYSTONE = "holystone_ambrosium";
	public static final String TYPE_ZANITE = "zanite_scaling";
	public static final String TYPE_GRAVITITE = "gravitite_launch";
	
	public static final WeaponProperty SkyrootDoubleDrops = new WeaponPropertySkyroot(TYPE_SKYROOT, SpartanCompatibility.MODID);
	public static final WeaponProperty HolystoneAmbrosium = new WeaponPropertyHolystone(TYPE_HOLYSTONE, SpartanCompatibility.MODID);
	public static final WeaponProperty ZaniteScaling = new WeaponPropertyZanite(TYPE_ZANITE, SpartanCompatibility.MODID);
	public static final WeaponProperty GravititeLaunching = new WeaponPropertyGravitite(TYPE_GRAVITITE, SpartanCompatibility.MODID);

}
