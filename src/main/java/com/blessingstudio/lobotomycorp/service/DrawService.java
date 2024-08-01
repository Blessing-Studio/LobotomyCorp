package com.blessingstudio.lobotomycorp.service;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class DrawService {
    public static void drawProgressBar(double x, double y, int width, int height, float p, int color1, int color2) {
        double progress = MathHelper.clamp(p, 0.0f, 1.0f);
        double length = width * progress;

        double progress1 = 4 * progress;
        double hy = y;
        drawTetragon(x, x, hy, hy + 4, 162, 162, height, height, color1);
        drawTetragon(x, x, hy, hy + progress1, length, length, height, height, color2);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
    }

    public static void drawString(String text, int x, int y, int color){
        GlStateManager.pushMatrix();
        GlStateManager.scale(1.5, 1.5, 1.0F);
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        fontRenderer.drawString(text, x, y, color);
        GlStateManager.popMatrix();
    }

    public static void drawTetragon(double posX1, double posX2, double posY1, double posY2, double width1, double width2, double height1, double height2, int color) {
        if(color == -1)
            return;

        if(width1 < 0) width1 = 0;
        if(width2 < 0) width2 = 0;

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        tryBlendFuncSeparate();
        GlStateManager.color(f, f1, f2, f3);

        GlStateManager.disableDepth();
        beginVertex(7, DefaultVertexFormats.POSITION);
        addVertexPos(posX1, posY1 + height1, 0.0D);
        addVertexPos(posX2 + width2, posY2 + height2, 0.0D);
        addVertexPos(posX1 + width1, posY2, 0.0D);
        addVertexPos(posX2, posY1, 0.0D);
        drawVertex();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
        GlStateManager.color(1f, 1f, 1f);
    }

    private static void drawVertex() {
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.draw();
    }

    private static void beginVertex(int i, VertexFormat format) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(i, format);
    }

    private static void addVertexPos(double x, double y, double z) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.pos(x, y, z).endVertex();
    }

    private static void tryBlendFuncSeparate() {
        GlStateManager.tryBlendFuncSeparate(getSrcAlpha(), getOneMinusSrcAlpha(), getGlOne(), getGlZero());
    }

    private static int getSrcAlpha() {
        return GL11.GL_SRC_ALPHA;
    }

    private static int getOneMinusSrcAlpha() {
        return GL11.GL_ONE_MINUS_SRC_ALPHA;
    }

    private static int getGlOne() {
        return GL11.GL_ONE;
    }

    private static int getGlZero() {
        return GL11.GL_ZERO;
    }
}
