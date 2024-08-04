package com.blessingstudio.lobotomycorp.components.gui.widget;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import com.lambdainnovation.lambdalib2.cgui.CGui;
import org.lwjgl.opengl.GL11;

public class ProgressBar extends Gui {
    private static int _color;

    private static double _progress = 0d;
    private static double _maxProgress = 20d;

    private static double _width;
    private static double _height;

    public int getColor() {
        return _color;
    }

    public double getProgress() {
        return _progress;
    }

    public double getMaxProgress() {
        return _maxProgress;
    }

    public void setColor(int color) {
        _color = color;
    }

    public void setMaxProgress(double maxProgress) {
        _maxProgress = maxProgress;
    }

    public void setProgress(double progress) {
        _progress = progress;
    }

    public void setWidth(double width) {
        _width = width;
    }

    public void setHeight(double height) {
        _height = height;
    }

    public ProgressBar(double maxProgress, int color) {
        _color = color;
        _maxProgress = maxProgress;

        _height = 5;
        _width = 182;
    }

    public void render() {
        Minecraft minecraft = Minecraft.getMinecraft();
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        _width = scaledResolution.getScaledWidth() - 20;

        int x = 10;
        int y = (int)(scaledResolution.getScaledHeight() - _height - 10);


        drawRect(x, y, (int)(x + _width), (int)(y + _height), 0xFF000000);
        drawGradientRect(x, y, (int)(x + _width), (int)(y + _height), 0xFFFFFFFF, 0x00FFFFFF);
        drawRect(x, y, x + (int) (_progress / _maxProgress * _width), (int) (y + _height), 0xFFFF0000);
    }
}