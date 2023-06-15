package com.xilu.lobotomycorp.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerEventHandler {
    @SubscribeEvent // 玩家被攻击时的事件
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if(MentalityOverlayHandler.mentalityValue <= 0 || MentalityOverlayHandler.mentalityValue - 2 <= 0){
                player.setDead();
                MentalityOverlayHandler.setMentalityValue(20);
            } else if (MentalityOverlayHandler.mentalityValue - 2 > 0) {
                MentalityOverlayHandler.setMentalityValue(MentalityOverlayHandler.mentalityValue - 2);
            }
        }
    }
}
