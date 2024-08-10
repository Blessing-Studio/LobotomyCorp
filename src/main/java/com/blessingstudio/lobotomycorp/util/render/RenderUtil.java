package com.blessingstudio.lobotomycorp.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderUtil {
    private static final ShaderUtil roundedShader = new ShaderUtil();
    public static void drawRoundedRect(int x, int y, int x2, int y2, int radius, int c) {
        int width = x2 - x;
        int height = y2 - y;
        Color color = new Color(c, true);
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        roundedShader.init();

        setupRoundedRectUniforms(x, y, width, height, radius);
        roundedShader.setUniform2i("blur", 0);
        roundedShader.setUniform("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);

        ShaderUtil.drawQuads(x - 1, y - 1, width + 2, height + 2);
        roundedShader.unload();
        GlStateManager.disableBlend();
    }

    public static int rainbow(int delay) {
        double rainbow = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbow %= 360;
        return Color.getHSBColor((float) (rainbow / 360.0f), 0.8f, 0.7f).getRGB();
    }

    private static void setupRoundedRectUniforms(float x, float y, float width, float height, float radius) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        roundedShader.setUniform("location", x * sr.getScaleFactor(), (Minecraft.getMinecraft().displayHeight - (height * sr.getScaleFactor())) - (y * sr.getScaleFactor()));
        roundedShader.setUniform("rectSize", width * sr.getScaleFactor(), height * sr.getScaleFactor());
        roundedShader.setUniform("radius", radius * sr.getScaleFactor());
    }
}
