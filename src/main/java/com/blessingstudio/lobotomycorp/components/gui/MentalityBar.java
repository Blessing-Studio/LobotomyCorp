package com.blessingstudio.lobotomycorp.components.gui;

import com.blessingstudio.lobotomycorp.classes.interfaces.IMentality;
import com.blessingstudio.lobotomycorp.components.handler.CapabilityHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

import static com.blessingstudio.lobotomycorp.LobotomyCorp.*;

@Mod.EventBusSubscriber
public class MentalityBar extends Gui {
    private static int updateCounter;
    private static final Random RANDOM = new Random();
    private static final Minecraft MC = Minecraft.getMinecraft();
    private static final ResourceLocation OVERLAY = new ResourceLocation(MODID,"textures/gui/overlay.png");

    public static MentalityBar Default;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !MC.isGamePaused()) {
            updateCounter++;
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void hendleRender(RenderGameOverlayEvent.Post event) {
        if (Default == null) {
            Default = new MentalityBar();
        }

        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            ScaledResolution resolution = event.getResolution();
            int width = resolution.getScaledWidth();
            int height = resolution.getScaledHeight();
            EntityPlayer player = (EntityPlayer) MC.getRenderViewEntity();
            RANDOM.setSeed(updateCounter * 312871);

            IMentality mentality = player.getCapability(CapabilityHandler.mentalityCapability, null);
            if (MC.playerController.gameIsSurvivalOrAdventure()) {
                int value = (int) mentality.getMentalityValue();
                MC.getTextureManager().bindTexture(OVERLAY);
                Default.render(width, height, value);

                GuiIngameForge.right_height += 10;
                MC.getTextureManager().bindTexture(Gui.ICONS);
            }
        }
    }

    public void render(int width, int height, int mentalityLevel) {
        int left = width / 2 + 91;
        int top = height - GuiIngameForge.right_height;

        for (int i = 0; i < 10; i++) {
            int dropletHalf = i * 2 + 1;
            int startX = left - i * 8 - 9;
            int startY = top;

            if (updateCounter % (mentalityLevel * 3 + 1) == 0) {
                startY = top + (RANDOM.nextInt(3) - 1);
            }

            drawTexturedModalRect(startX, startY, 22, 0, 12, 9);

            if (mentalityLevel > dropletHalf) {
                drawTexturedModalRect(startX, startY, 0, 0, 12, 9);
            }
            if (mentalityLevel == dropletHalf) {
                drawTexturedModalRect(startX, startY, 11, 0, 12, 9);
            }
        }
    }
}