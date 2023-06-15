package com.xilu.lobotomycorp.handler;

import com.xilu.lobotomycorp.interfaces.IMentality;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class MentalityOverlayHandler {
    private static IMentality mentality = null;
    private static Minecraft mc = Minecraft.getMinecraft();
    public static double mentalityValue = 0D;
    public static final ResourceLocation OVERLAY = new ResourceLocation("lobotomycorp:textures/gui/overlay.png");
    private int updateCounter;
    private final Random random = new Random();

    public static void setMentalityValue(double value){
        mentality.setMentalityValue(value);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END && !mc.isGamePaused())
        {
            updateCounter++;
        }
    }

    @SubscribeEvent
    public void onPreRenderOverlay(RenderGameOverlayEvent.Pre event){
        if(mc.player.hasCapability(CapabilityHandler.capConsciousness, null)) {
            ScaledResolution resolution = event.getResolution();
            int width = resolution.getScaledWidth();
            int height = resolution.getScaledHeight();

            mentality = (IMentality) mc.player.getCapability(CapabilityHandler.capConsciousness, null);
            mentalityValue = mentality.getMentalityValue();
            //TextUtil.drawText("精神值为："+mentalityValue,1);
        }
    }
}
