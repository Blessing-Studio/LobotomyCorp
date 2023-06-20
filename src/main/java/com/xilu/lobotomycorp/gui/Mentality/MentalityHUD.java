package com.xilu.lobotomycorp.gui.Mentality;

import com.xilu.lobotomycorp.events.SPChangeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class MentalityHUD extends Gui
{
    public static MentalityHUD hud = new MentalityHUD();
    private Minecraft mc = Minecraft.getMinecraft();

    public static void Init() {
        FMLCommonHandler.instance().bus().register(hud);
    }

    @SubscribeEvent
    public void onRenderHUD(RenderGameOverlayEvent.Post event)
    {
        drawString(mc.fontRenderer, ""+SPChangeEvent.foo, 10, 10, 0xFFFFFF);
    }
}
