package com.cheezedfish.spartancompat;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SpartanCompatability.MODID, name = SpartanCompatability.NAME, version = SpartanCompatability.VERSION, dependencies = SpartanCompatability.DEPENDENCIES)
public class SpartanCompatability
{
    public static final String MODID = "spartancompat";
    public static final String NAME = "Spartan Compatability";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:spartanweaponry@[beta-1.3.0,);after:simpleores";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
