package com.xilu.lobotomycorp.handler;

import com.xilu.lobotomycorp.LobotomyCorp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Set;

import static net.minecraftforge.fml.client.config.GuiUtils.drawTexturedModalRect;

public class MentalityOverlayHandler extends Gui {
    private final ResourceLocation gui = new ResourceLocation(LobotomyCorp.MODID, "textures/gui/overlay.png");
    public static DataParameter<Float> EnduranceData = new DataParameter<>(99, DataSerializers.FLOAT);

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            Set<String> tags = player.getTags();
            if (tags.contains("float")) {
                mc.renderEngine.bindTexture(gui);
                EntityDataManager dataManager = player.getDataManager();
                float max = 20;
                float maxHealth = player.getMaxHealth();
                if (maxHealth >= 20) {
                    max = maxHealth;
                }
                float value = dataManager.get(EnduranceData);
                float bar = 82;
                float percent = 1 - (value / max);
                int current = (int) (percent * bar);
                int offY = 0;
                if (Loader.isModLoaded("dualhotbar")) {
                    offY = offY -20;
                }
                if (Loader.isModLoaded("toughasnails")) {
                    offY = offY -10;
                }
                int posX = event.getResolution().getScaledWidth() / 2 + 10;
                int posY = event.getResolution().getScaledHeight() - 49 + offY;
                int posWater = posY - 10;
                if (player.getAir() < 300) {
                    drawTexturedModalRect(posX, posWater, 0, 9, 82, 9);
                    drawTexturedModalRect(posX, posWater, 0, 0, current, 9);
                } else {
                    drawTexturedModalRect(posX, posY, 0, 9, 82, 9);
                    drawTexturedModalRect(posX, posY, 0, 0, current, 9);
                }
            }
        }
    }
}