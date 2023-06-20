package com.xilu.lobotomycorp.init;

import com.xilu.lobotomycorp.handler.CapabilityHandler;
import com.xilu.lobotomycorp.handler.MentalityHandler;
import com.xilu.lobotomycorp.handler.MentalityOverlayHandler;
import com.xilu.lobotomycorp.handler.PlayerEventHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

public class ModInits {
    public static void init(){
        CapabilityHandler.setupCapabilities();
        MinecraftForge.EVENT_BUS.register(new MentalityHandler());
        MinecraftForge.EVENT_BUS.register(new MentalityOverlayHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
    }
}
