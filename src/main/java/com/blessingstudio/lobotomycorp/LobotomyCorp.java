package com.blessingstudio.lobotomycorp;

import com.blessingstudio.lobotomycorp.components.handler.CapabilityHandler;
import com.blessingstudio.lobotomycorp.components.proxy.ProxyBase;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;

@Mod(modid = LobotomyCorp.MODID, name = LobotomyCorp.NAME, version = LobotomyCorp.VERSION)
public class LobotomyCorp {
    private static Logger logger;

    public static final String MODID = "lobotomycorp";
    public static final String NAME = "LobotomyCorp";
    public static final String VERSION = "0.0.4";

    @SidedProxy(clientSide = "com.blessingstudio.lobotomycorp.components.proxy.ClientProxy", serverSide = "com.blessingstudio.lobotomycorp.components.proxy.ServerProxy")
    public static ProxyBase proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        CapabilityHandler.setupCapabilities();
    }

    public static void Log(String str) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(str);
//        }
    }

    public static void Log(String str, Object... args) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(String.format(str, args));
//        }
    }
}