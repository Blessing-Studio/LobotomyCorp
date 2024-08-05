package com.blessingstudio.lobotomycorp.components.gui;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import com.blessingstudio.lobotomycorp.components.gui.widget.ProgressBar;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class TestBar {
    private static ProgressBar _progressBar = new ProgressBar(20, 0x00FFFFFF);

    @SubscribeEvent
    public static void onRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            if (Minecraft.getMinecraft().playerController.gameIsSurvivalOrAdventure()) {
//                _progressBar.render();
            }
        }
    }

    @SubscribeEvent
    public static void DebugLivingAttack(LivingAttackEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            _progressBar.setProgress(_progressBar.getProgress() - event.getAmount());
            LobotomyCorp.Log("当前进度" + _progressBar.getProgress());
        }
    }
}