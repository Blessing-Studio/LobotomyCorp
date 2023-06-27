package com.xilu.lobotomycorp.gui.Mentality;

import com.xilu.lobotomycorp.events.SPChangeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

import static com.xilu.lobotomycorp.LobotomyCorp.MODID;

public class MentalityHUD extends Gui
{
    // 材质图
    public static final ResourceLocation hud = new ResourceLocation(MODID,"textures/gui/overlay.png");
    private Minecraft mc = Minecraft.getMinecraft();

    private static MentalityHUD test = new MentalityHUD();

    private final Random random = new Random();

    public static int thirstLevel = 20;

    public static float thirstHydrationLevel = 1f;

    private int updateCounter;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END && !mc.isGamePaused())
        {
            updateCounter++;
        }
    }
    public static void Init() {
        FMLCommonHandler.instance().bus().register(test);
    }
    
    public void render() {
        ScaledResolution r = new ScaledResolution(this.mc);
        this.mc.getTextureManager().bindTexture(hud);
        int win_w = r.getScaledWidth();
        int win_h = r.getScaledHeight();
        // 分别画左右两边的图
        drawModalRectWithCustomSizedTexture(win_w/3-32, win_h/2-32, 0, 0, 64, 64, 128, 64);
        drawModalRectWithCustomSizedTexture(win_w/3*2-32, win_h/2-32, 64, 0, 64, 64, 128, 64);
    }

    @SubscribeEvent
    public void onRenderHUD(RenderGameOverlayEvent.Post event)
    {
        if (event.getType() == RenderGameOverlayEvent.ElementType.AIR) {
            ScaledResolution resolution = event.getResolution();
            int width = resolution.getScaledWidth();
            int height = resolution.getScaledHeight();
            EntityPlayer player = Minecraft.getMinecraft().player;

            //ThirstHandler thirstStats = (ThirstHandler)player.getCapability(TANCapabilities.THIRST, null);
            random.setSeed((long)(updateCounter * 312871));

            thirstLevel = (int)player.getHealth();
            thirstHydrationLevel = player.getMaxHealth();

            if (mc.playerController.gameIsSurvivalOrAdventure())
            {
                mc.getTextureManager().bindTexture(hud);
                drawThirst(width, height, thirstLevel, thirstHydrationLevel);
                GuiIngameForge.right_height += 10;
                mc.getTextureManager().bindTexture(Gui.ICONS);
            }
        }
    }

    private void drawThirst(int width, int height, int thirstLevel, float thirstHydrationLevel)
    {
        int left = width / 2 + 91;
        int top = height - GuiIngameForge.right_height;

        for (int i = 0; i < 10; i++)
        {
            int dropletHalf = i * 2 + 1;
            int iconIndex = 0;
            int backgroundOffset = 0;
            int startX = left - i * 8 - 9;
            int startY = top;

//            if (mc.player.isPotionActive(TANPotions.thirst))
//            {
//                iconIndex += 4;
//                backgroundOffset += 117;
//            }

            if (thirstHydrationLevel <= 0.0F && updateCounter % (thirstLevel * 3 + 1) == 0)
            {
                startY = top + (random.nextInt(3) - 1);
            }

            drawTexturedModalRect(startX, startY, backgroundOffset, 16, 9, 9);

            if (thirstLevel > dropletHalf)
            {
                drawTexturedModalRect(startX, startY, (iconIndex + 4) * 9, 16, 9, 9);
            }
            if (thirstLevel == dropletHalf)
            {
                drawTexturedModalRect(startX, startY, (iconIndex + 5) * 9, 16, 9, 9);
            }
        }
    }
}
