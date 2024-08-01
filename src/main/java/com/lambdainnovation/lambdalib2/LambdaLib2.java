package com.lambdainnovation.lambdalib2;

import com.lambdainnovation.lambdalib2.multiblock.MsgBlockMulti;
import com.lambdainnovation.lambdalib2.registry.RegistryMod;
import com.lambdainnovation.lambdalib2.registry.impl.RegistryManager;
import com.lambdainnovation.lambdalib2.registry.impl.RegistryTransformer;
import com.lambdainnovation.lambdalib2.s11n.network.NetworkEvent;
import com.lambdainnovation.lambdalib2.s11n.network.NetworkMessage;
import com.lambdainnovation.lambdalib2.util.DebugDraw;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@RegistryMod(resourceDomain = "lambdalib2")
@Mod(modid = LambdaLib2.MODID, version = LambdaLib2.VERSION)
public class LambdaLib2
{
    public static final String MODID = "lambdalib2";
    public static final String VERSION = "0.2.0";

    /**
     * Whether we are in development (debug) mode.
     */
    public static final boolean DEBUG = VERSION.startsWith("@");

    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    public static Configuration config;

    private static Logger log;

    public static Logger getLogger() {
        return log;
    }
}
