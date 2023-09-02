package com.xilu.lobotomycorp;

import com.xilu.lobotomycorp.gui.ModGuiElementLoader;
import com.xilu.lobotomycorp.handler.CapabilityHandler;
import com.xilu.lobotomycorp.handler.MentalityOverlayHandler;
import com.xilu.lobotomycorp.init.*;
import com.xilu.lobotomycorp.interfaces.IMentality;
import com.xilu.lobotomycorp.keys.KeyboardManager;
import com.xilu.lobotomycorp.meta.MetaUtil;
import com.xilu.lobotomycorp.network.NetworkHandler;
import com.xilu.lobotomycorp.proxy.ProxyBase;
import com.xilu.lobotomycorp.util.CommonDef;
import com.xilu.lobotomycorp.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

//To let the player be a traveling god who plays yin-yang magic.

@Mod(modid = LobotomyCorp.MODID, name = LobotomyCorp.NAME, version = LobotomyCorp.VERSION)//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class LobotomyCorp {
    public static final String MODID = "lobotomycorp";
    public static final String NAME = "Lobotomy Corp";
    public static final String VERSION = "0.0.3";

    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    @Mod.Instance
    public static LobotomyCorp instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ProxyBase proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        CapabilityHandler.setupCapabilities();
        RegistryHandler.preInitRegistries(event);
    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {
        ModRecipes.Init();
        RegisterTileEntity();
        RegistryHandler.initRegistries(event);
        new ModGuiElementLoader();
        if (!proxy.isServer())
        {
            KeyboardManager.init();
        }
        ModInits.init();
        NetworkHandler.init();

		Log("%s has finished its initializations", MODID);
	}

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Moved Spawning registry to last since forge doesn't auto-generate sub
        // "M' biomes until late
        if (ModConfig.SPAWN_CONF.SPAWN) {
            ModSpawn.registerSpawnList();
        }

        MetaUtil.isIDLLoaded = Loader.isModLoaded("idealland");
        MetaUtil.isIRRLoaded = Loader.isModLoaded("itemrender");
        MetaUtil.isLoaded_TiC = Loader.isModLoaded("tconstruct");
        MetaUtil.isLoaded_Slashblade = Loader.isModLoaded("flammpfeil.slashblade");
        MetaUtil.isLoaded_Botania = Loader.isModLoaded("botania");
        MetaUtil.isLoaded_DWeapon = Loader.isModLoaded("dweapon");
        MetaUtil.isLoaded_AOA3 = Loader.isModLoaded(CommonDef.MOD_NAME_AOA3);
        MetaUtil.isLoaded_GC = Loader.isModLoaded("galacticraftcore");
        MetaUtil.isLoaded_Taoism = Loader.isModLoaded("taoism");
        MetaUtil.isLoaded_GOG = Loader.isModLoaded(CommonDef.MOD_NAME_GOG);

        TrashTalking();

        RegistryHandler.postInitReg();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    private void TrashTalking() {
        if (MetaUtil.isIDLLoaded)
        {
            LobotomyCorp.Log("[Idealland Framework] Bow to Idealland.");
        }
        else {
            LobotomyCorp.Log("[Idealland Framework] Made with Idealland Framework.");
        }
    }

    private static void RegisterTileEntity() {
//        GameRegistry.registerTileEntity(TileEntityDeBoomOrb.class, new ResourceLocation(MODID, "deboom_orb_basic"));

        //GameRegistry.registerTileEntity(TileEntityBuilderFarm.class, new ResourceLocation(MODID, "builder_farm_basic"));
        //GameRegistry.registerTileEntity(TileEntityBuilderOne.class, new ResourceLocation(MODID, "builder.builder_one"));
    }

    public static void LogWarning(String str, Object... args) {
        if (SHOW_WARN) {
            logger.warn(String.format(str, args));
        }
    }

    public static void LogWarning(String str) {
        if (SHOW_WARN) {
            logger.warn(str);
        }
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