package com.xilu.lobotomycorp.handler;

import com.xilu.lobotomycorp.LobotomyCorp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

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
            renderThirstBar(mc);
        }
    }

    public static void renderThirstBar(Minecraft minecraft) {
        // 获取当前的Minecraft实例和玩家对象
        EntityPlayer player = minecraft.player;

        // 获取屏幕宽度和高度
        int screenWidth = minecraft.displayWidth;
        int screenHeight = minecraft.displayHeight;

        // 计算饮水条的位置和尺寸
        int barWidth = 100; // 饮水条的宽度
        int barHeight = 10; // 饮水条的高度
        int barX = (screenWidth - barWidth) / 2; // 饮水条的X坐标
        int barY = screenHeight - barHeight - 10; // 饮水条的Y坐标

        float thirst = player.getFoodStats().getFoodLevel() / 20.0F;
        int filledWidth = (int) (barWidth * thirst);

        // 绘制饮水条的背景
// 在渲染方法中使用以下代码
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.5F);

// 绘制背景
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(barX, barY);
        GL11.glVertex2f(barX, barY + barHeight);
        GL11.glVertex2f(barX + barWidth, barY + barHeight);
        GL11.glVertex2f(barX + barWidth, barY);
        GL11.glEnd();

// 绘制前景
        GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(barX, barY);
        GL11.glVertex2f(barX, barY + barHeight);
        GL11.glVertex2f(barX + filledWidth, barY + barHeight);
        GL11.glVertex2f(barX + filledWidth, barY);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();    }
}