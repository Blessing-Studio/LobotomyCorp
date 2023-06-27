package com.xilu.lobotomycorp.init;

import com.xilu.lobotomycorp.handler.CapabilityHandler;
import com.xilu.lobotomycorp.handler.MentalityHandler;
import com.xilu.lobotomycorp.handler.MentalityOverlayHandler;
import com.xilu.lobotomycorp.handler.PlayerEventHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ModInits {
    public static void init(){
        MinecraftForge.EVENT_BUS.register(new MentalityHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        FMLCommonHandler.instance().bus().register(new MentalityOverlayHandler());
    }
}
