package com.xilu.lobotomycorp.handler;

import com.xilu.lobotomycorp.interfaces.IMentality;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

import static com.xilu.lobotomycorp.LobotomyCorp.MODID;

public class MentalityOverlayHandler extends Gui {
    private int updateCounter;

    private final Random random = new Random();

    public static int mentalityLevel = 1;

    public static float maxMentalityLevel = 10f;

    public static final ResourceLocation OVERLAY = new ResourceLocation(MODID,"textures/gui/overlay.png");

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (event.phase == TickEvent.Phase.END && !mc.isGamePaused()) {
            updateCounter++;
        }
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            ScaledResolution resolution = event.getResolution();
            int width = resolution.getScaledWidth();
            int height = resolution.getScaledHeight();
            EntityPlayer player = Minecraft.getMinecraft().player;
            random.setSeed((long)(updateCounter * 312871));

//            mentalityLevel = (int)player.getHealth();
//            maxMentalityLevel = player.getMaxHealth();
            IMentality mentality = player.getCapability(CapabilityHandler.capMentality, null);

            if (mc.playerController.gameIsSurvivalOrAdventure())
            {
                int value = (int) mentality.getMentalityValue();
                mc.getTextureManager().bindTexture(OVERLAY);
                drawMentality(width, height, value, maxMentalityLevel);

                GuiIngameForge.right_height += 10;
                mc.getTextureManager().bindTexture(Gui.ICONS);
            }
        }
    }

    private void drawMentality(int width, int height, int mentalityLevel, float maxMentalityLevel) {
        int left = width / 2 + 91;
        int top = height - GuiIngameForge.right_height;

        for (int i = 0; i < 10; i++) {
            int dropletHalf = i * 2 + 1;
            int startX = left - i * 8 - 9;
            int startY = top;

            if (updateCounter % (mentalityLevel * 3 + 1) == 0) {
                startY = top + (random.nextInt(3) - 1);
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