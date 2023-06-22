package com.xilu.lobotomycorp.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerEventHandler {
    @SubscribeEvent // 玩家被攻击时的事件
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
//            if (event.getSource().getTrueSource() instanceof EntityPlayer && event.getSource().getTrueSource() != player) {
//                MentalityOverlayHandler.setMentalityValue(MentalityOverlayHandler.mentalityValue - 2);
//                if (MentalityOverlayHandler.mentalityValue <= 0) {
//                    player.setHealth(0.0f); // 先将玩家设置为不可被伤害的状态
//                    player.setDead(); // 再调用 setDead() 方法
//                    MentalityOverlayHandler.setMentalityValue(20);
//                }
//            }
        }
    }}
