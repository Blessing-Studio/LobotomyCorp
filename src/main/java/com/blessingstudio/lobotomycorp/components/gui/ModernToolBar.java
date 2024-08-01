package com.blessingstudio.lobotomycorp.components.gui;

import com.blessingstudio.lobotomycorp.service.DrawService;
import com.blessingstudio.lobotomycorp.service.EntityPlayerService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class ModernToolBar {
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        if (mc.playerController.getCurrentGameType() != GameType.SURVIVAL) {
            return; // 如果玩家不在生存模式，就直接返回
        }

        switch (event.getType()) {
            case HEALTH:
            case ARMOR:
            case AIR:
            case FOOD:
            case HOTBAR:
            case EXPERIENCE:
                event.setCanceled(true);
                break;
        }
    }

    @SubscribeEvent
    public static void handleRender(RenderGameOverlayEvent.Post event) {
        draw(mc.getRenderViewEntity());
    }

    protected static void draw(Entity player) {
        if (player instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) player;
            ScaledResolution scaledResolution = new ScaledResolution(mc);

            int screenWidth = scaledResolution.getScaledWidth();
            int screenHeight = scaledResolution.getScaledHeight();

            int x = screenWidth - 172;
            int y = screenHeight - 40;

            //ToolBar
            DrawService.drawTetragon(x, x, y, y + 4, 162,
                    162, 18, 18, 0x10FFFFFF);

            //current select
            double sx = x + entityPlayer.inventory.currentItem * 18;
            DrawService.drawTetragon(sx, sx, y + 3.55 - getOffset(entityPlayer.inventory.currentItem), y + 4 - getOffset(entityPlayer.inventory.currentItem), 18,18,18,18,
                    new Color(127,255,212).getRGB());

            handleHotBarItems(entityPlayer);

            //HEALTH
            double hy = screenHeight - 43.5;

            if (entityPlayer.getMaxHealth() == entityPlayer.getHealth()) {
                DrawService.drawProgressBar(x, hy + 1,162, 2,entityPlayer.getHealth() / entityPlayer.getMaxHealth(),
                        new Color(34,139,34).getRGB(), new Color(124,252,0).getRGB());
            } else {
                DrawService.drawProgressBar(x, hy,162, 3,entityPlayer.getHealth() / entityPlayer.getMaxHealth(),
                        new Color(34,139,34).getRGB(), new Color(124,252,0).getRGB());
            }

            //ARMOR
            double ay = entityPlayer.getMaxHealth() == entityPlayer.getHealth() ? screenHeight - 44 : screenHeight - 45;
            DrawService.drawProgressBar(x, ay,162, 1, (float) entityPlayer.getTotalArmorValue() / EntityPlayerService.getMaxArmorValue(),
                    new Color(0, 110, 110).getRGB(), new Color(95,158,160).getRGB());

            //FOOD
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
            String text = "你想要显示的文字";
            int color = 0xFFFFFF;  // 文字的颜色，这里是白色
            fontRenderer.drawString(text, x, y, color);

        }
    }

    private static void handleHotBarItems(EntityPlayer player){
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderHelper.enableGUIStandardItemLighting();

        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int screenWidth = scaledResolution.getScaledWidth();
        int screenHeight = scaledResolution.getScaledHeight();

        for (int l = 0; l < 9; ++l) {
            int i1 = screenWidth - 190 + l * 20 + 2;
            int j1 = screenHeight - 40;
            renderHotbarItem(i1 - getRenderItemXOffset(l), j1 - getRenderItemYOffset(l), -1, player, player.inventory.mainInventory.get(l));
        }
    }

    private static void renderHotbarItem(float p_184044_1_, double p_184044_2_, float p_184044_3_, EntityPlayer player, ItemStack stack) {
        if (!stack.isEmpty()) {
            float f = (float)stack.getAnimationsToGo() - p_184044_3_;

            if (f > 0.0F) {
                GlStateManager.pushMatrix();
                float f1 = 1.0F + f / 5.0F;
                GlStateManager.translate(p_184044_1_ + 8, (float)(p_184044_2_ + 12), 0.0F);
                GlStateManager.scale(0.9f,0.9f, 1F);
                GlStateManager.rotate(1.5f, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate(-(p_184044_1_ + 8), (float)(-(p_184044_2_ + 12)), 0.0F);
            }

            mc.getRenderItem().renderItemAndEffectIntoGUI(player, stack, (int) p_184044_1_, (int)p_184044_2_);

            if (f > 0.0F) {
                GlStateManager.popMatrix();
                mc.getRenderItem().renderItemOverlays(mc.fontRenderer, stack, (int) p_184044_1_, (int)p_184044_2_);
            } else {
                mc.getRenderItem().renderItemOverlays(mc.fontRenderer, stack, (int) p_184044_1_, (int)p_184044_2_);
            }
        }
    }

    private static double getOffset(int select) {
        double result;
        switch (select) {
            case 0:
                result = 3.6;
                break;
            case 1:
                result = 3.11;
                break;
            case 2:
                result = 2.665;
                break;
            case 3:
                result = 2.222;
                break;
            case 4:
                result = 1.77;
                break;
            case 5:
                result = 1.33;
                break;
            case 6:
                result = 0.88;
                break;
            case 7:
                result = 0.45;
                break;
            case 8:
                result = 0;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + select);
        }

        return result;
   }

   private static float getRenderItemYOffset(int select) {
       float result;
       switch (select) {
           case 0:
               result = -0.5f;
               break;
           case 1:
               result = -1f;
               break;
           case 2:
               result = -1.5f;
               break;
           case 3:
               result = -2.222f;
               break;
           case 4:
               result = -2.5f;
               break;
           case 5:
               result = -3f;
               break;
           case 6:
               result = -3.5f;
               break;
           case 7:
           case 8:
               result = -4f;
               break;
           default:
               throw new IllegalStateException("Unexpected value: " + select);
       }

       return result;
   }

    private static float getRenderItemXOffset(int select) {
        float result;
        switch (select) {
            case 0:
                result = -16.53f;
                break;
            case 1:
                result = -13.8f;
                break;
            case 2:
                result = -12f;
                break;
            case 3:
                result = -10.5f;
                break;
            case 4:
                result = -8.5f;
                break;
            case 5:
                result = -6f;
                break;
            case 6:
                result = -5f;
                break;
            case 7:
                result = -3f;
                break;
            case 8:
                result = -1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + select);
        }

        return result;
    }
}
