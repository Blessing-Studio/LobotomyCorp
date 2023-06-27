package com.xilu.lobotomycorp.handler;

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

    public static final ResourceLocation HUD = new ResourceLocation(MODID,"textures/gui/overlay.png");

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

            mentalityLevel = (int)player.getHealth();
            maxMentalityLevel = player.getMaxHealth();

            if (mc.playerController.gameIsSurvivalOrAdventure())
            {
                mc.getTextureManager().bindTexture(HUD);
                drawMentality(width, height, mentalityLevel, maxMentalityLevel);
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
            int iconIndex = 0;
            int backgroundOffset = 0;
            int startX = left - i * 8 - 9;
            int startY = top;

            if (maxMentalityLevel <= 0.0F && updateCounter % (mentalityLevel * 3 + 1) == 0) {
                startY = top + (random.nextInt(3) - 1);
            }

            drawTexturedModalRect(startX, startY, backgroundOffset, 16, 9, 9);
            if (mentalityLevel > dropletHalf) {
                drawTexturedModalRect(startX, startY, (iconIndex + 4) * 9, 16, 9, 9);
            }
            if (mentalityLevel == dropletHalf) {
                drawTexturedModalRect(startX, startY, (iconIndex + 5) * 9, 16, 9, 9);
            }
        }
    }
}