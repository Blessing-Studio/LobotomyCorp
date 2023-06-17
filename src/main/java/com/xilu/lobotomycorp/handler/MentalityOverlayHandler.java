package com.xilu.lobotomycorp.handler;

import com.xilu.lobotomycorp.LobotomyCorp;
import com.xilu.lobotomycorp.interfaces.IMentality;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;
import java.util.logging.Logger;

@SideOnly(Side.CLIENT)
public class MentalityOverlayHandler {
    private static IMentality mentality = null;
    public static double mentalityValue = 0D;

    public static void setMentalityValue(double value){
        mentality.setMentalityValue(value);
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Pre event){

        }
}
