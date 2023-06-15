package com.xilu.lobotomycorp.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;

public class TextUtil {
    public static void drawText(String text, int color) {
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fontRenderer = mc.fontRenderer;
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int screenWidth = scaledResolution.getScaledWidth();
        int screenHeight = scaledResolution.getScaledHeight();
        int x = screenWidth / 2 - fontRenderer.getStringWidth(text) / 2;
        int y = screenHeight / 2 - fontRenderer.FONT_HEIGHT / 2;

        // 保存当前使用的材质
        mc.getTextureManager().bindTexture(Gui.ICONS);

        fontRenderer.drawString(text, x, y, color,true);

        // 恢复当前使用的材质
        mc.getTextureManager().bindTexture(Gui.ICONS);
    }
}
