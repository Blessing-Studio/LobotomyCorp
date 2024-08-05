package com.blessingstudio.lobotomycorp.components.handler;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import com.blessingstudio.lobotomycorp.classes.enums.AlarmLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class AlarmHandler {
    private static final int ALARM_RADIUS = 16;
    private static final int TICK_INTERVAL = 50;

    private static final int ONE_LEVEL = 10;
    private static final int SECOND_LEVEL = 50;
    private static final int THREE_LEVEL = 80;

    private static int _tickCounter = 0;
    private static int _alarmCount = 0;
    private static long _lastUpdateTime = 0;
    private static ISound _currentAlarmSound;
    private static boolean _isAlarmPlaying = false;
    private static AlarmLevel _alarmLevel = AlarmLevel.NONE;
    private static Set<Entity> _processedEntities = new HashSet<>();

    public static void setAlarmCount(int count) {
        _alarmCount = count > 100 ? 100 : count;
    }

    public static int getAlarmCount() {
        return _alarmCount;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            _tickCounter++;
            if (_tickCounter >= TICK_INTERVAL) {
                _tickCounter = 0;
                LobotomyCorp.Log("当前警报点数:" + getAlarmCount());
                for (EntityPlayer player : event.world.playerEntities) {
                    updateAlarm(player);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        setAlarmCount(0);

        _tickCounter = 0;
        _lastUpdateTime = 0;

        _isAlarmPlaying = false;
        _currentAlarmSound = null;

        _alarmLevel = AlarmLevel.NONE;
        _processedEntities.clear();
    }

        @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        if (_processedEntities.contains(entity)) {
            EntityPlayer player = entity.world.getClosestPlayerToEntity(entity, ALARM_RADIUS);
            if (player != null) {
                reduceAlarmCount(player, entity);
            }

            _processedEntities.remove(entity);
        }
    }

    private static void updateAlarm(EntityPlayer player) {
        Set<Entity> currentEntities = new HashSet<>();

        for (Entity entity : player.world.getEntitiesWithinAABB(Entity.class, player.getEntityBoundingBox().grow(ALARM_RADIUS))) {
            if (entity != player && !entity.isDead) {
                currentEntities.add(entity);
                if (!_processedEntities.contains(entity)) {
                    // 根据条件触发警报
                    triggerAlarm(player, entity);
                    _processedEntities.add(entity);
                }
            }
        }

        if (player.world.getTotalWorldTime() % 300 == 0) {
            setAlarmCount(Math.max(0, getAlarmCount() - 1));
        }

        // 更新警报等级
        updateAlarmLevel(player);
    }

    private static void reduceAlarmCount(EntityPlayer player, Entity entity) {
        if (entity instanceof EntityPig) {
            setAlarmCount(Math.max(0, getAlarmCount() - 20));
            player.sendMessage(new TextComponentString("警报点数减少，击杀异想体：" + entity.getName()));
        }
    }

    private static void triggerAlarm(EntityPlayer player, Entity entity) {
        if (entity instanceof EntityPig) {
            _alarmCount += 20;
            player.sendMessage(new TextComponentString("检测到异想体，增加的警报点：20，名字：" + entity.getName()));
        }
    }

    private static void updateAlarmLevel(EntityPlayer player) {
        boolean isChangeAlarmSound = false;
        AlarmLevel newAlarmLevel = AlarmLevel.NONE;
        SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();

        if (getAlarmCount() >= THREE_LEVEL) {
            newAlarmLevel = AlarmLevel.THREE;
        } else if (getAlarmCount() >= SECOND_LEVEL) {
            newAlarmLevel = AlarmLevel.SECOND;
        } else if (getAlarmCount() >= ONE_LEVEL) {
            newAlarmLevel = AlarmLevel.ONE;
        }

        if (newAlarmLevel != _alarmLevel) {
            isChangeAlarmSound = true;
            if (_currentAlarmSound != null && soundHandler.isSoundPlaying(_currentAlarmSound)) {
                soundHandler.stopSound(_currentAlarmSound);
            }

            switch (newAlarmLevel) {
                case ONE:
                    player.sendMessage(new TextComponentString("一级警报"));
                    _currentAlarmSound = RegisterHandler.ALARM_ONE_LEVEL_RECORD;
                    break;
                case SECOND:
                    player.sendMessage(new TextComponentString("二级警报"));
                    _currentAlarmSound = RegisterHandler.ALARM_SECOND_LEVEL_RECORD;
                    break;
                case THREE:
                    player.sendMessage(new TextComponentString("三级警报"));
                    _currentAlarmSound = RegisterHandler.ALARM_THREE_LEVEL_RECORD;
                case NONE:
//                    player.sendMessage(new TextComponentString("未播放警报"));
                    if (_currentAlarmSound != null && soundHandler.isSoundPlaying(_currentAlarmSound)) {
                        soundHandler.stopSound(_currentAlarmSound);
                    }
                    break;
            }

            _alarmLevel = newAlarmLevel;
        }

        if (isChangeAlarmSound && _currentAlarmSound != null && !soundHandler.isSoundPlaying(_currentAlarmSound)) {
            soundHandler.playSound(_currentAlarmSound);
        }
    }
}

