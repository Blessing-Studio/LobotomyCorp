package com.blessingstudio.lobotomycorp.components.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class AudioHandler {
    private static long _lastPlayTime = 0;
    private static long _playInterval = 0;
    private static boolean _isPlaying = false;

    private static Thread _muiscCheckThread;

    public static void playSound(EntityPlayer player, SoundEvent soundEvent, long playInterval) {
        if (!_isPlaying) {
            _isPlaying = true;
            _lastPlayTime = System.currentTimeMillis();
            _playInterval = playInterval;
            player.world.playSound(null, player.posX, player.posY, player.posZ, soundEvent, SoundCategory.MUSIC, 1.0F, 1.0F);

            // 使用新的线程来监控音频播放状态
            _muiscCheckThread = new Thread(() -> {
                try {
                    Thread.sleep(playInterval); // 假设音频持续时间为15秒
                } catch (InterruptedException e) {
                    Minecraft.getMinecraft().getSoundHandler().stopSounds();
                }

                _isPlaying = false;
            });

            _muiscCheckThread.start();
        }
    }

    public static void stopSound() {
        if (_muiscCheckThread != null && _muiscCheckThread.isAlive()) {
            _muiscCheckThread.interrupt();
        }

        _isPlaying = false;
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent event) {
        if (System.currentTimeMillis() - _lastPlayTime >= _playInterval) {
            _isPlaying = false;
        }
    }
}