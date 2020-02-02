package com.cheezedfish.spartancompat;

import org.apache.logging.log4j.Logger;

import com.cheezedfish.spartancompat.event.SpartanCompatibilityEventHandler;
import com.cheezedfish.spartancompat.proxy.CommonProxy;
import com.legacy.aether.blocks.BlocksAether;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = SpartanCompatibility.MODID, name = SpartanCompatibility.NAME, version = SpartanCompatibility.VERSION, dependencies = SpartanCompatibility.DEPENDENCIES)
public class SpartanCompatibility
{
    public static final String MODID = "spartancompat";
    public static final String NAME = "Spartan Compatability";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:spartanweaponry@[beta-1.3.0,);after:simpleores";
    public static Logger logger;
    
	@SidedProxy(modId = SpartanCompatibility.MODID, clientSide = "com.cheezedfish.spartancompat.proxy.ClientProxy", serverSide = "com.cheezedfish.spartancompat.proxy.CommonProxy")
	public static CommonProxy proxy;

    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	logger = event.getModLog();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	CommonProxy.registerEvent(new SpartanCompatibilityEventHandler());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	if(Loader.isModLoaded("aether_legacy")) {
    		
			// Register ore dict values for some Aether items for repairing
			OreDictionary.registerOre("plankSkyroot", BlocksAether.skyroot_plank);
			OreDictionary.registerOre("blockHolystone", BlocksAether.holystone);
			OreDictionary.registerOre("blockEnchantedGravitite", BlocksAether.enchanted_gravitite);

    	}
    }
}
