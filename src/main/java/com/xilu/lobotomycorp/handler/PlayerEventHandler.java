package com.xilu.lobotomycorp.handler;

import com.xilu.lobotomycorp.LobotomyCorp;
import com.xilu.lobotomycorp.capability.MentalityCapability;
import com.xilu.lobotomycorp.interfaces.IMentality;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.xilu.lobotomycorp.LobotomyCorp.*;

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
    }

    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof EntityPlayer){
            ICapabilitySerializable<NBTTagCompound> providerMentality = new MentalityCapability.ProvidePlayer();
            event.addCapability(new ResourceLocation(MODID + ":" + "mentality"), providerMentality);
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
        EntityPlayer player = event.player;
        IMentality capability = player.getCapability(CapabilityHandler.capMentality, null);

        if (capability != null) {
            // 将水分值重置为初始值
            capability.setMentalityValue(20F);
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
        event.player.sendMessage(new TextComponentString("感谢主管游玩 脑叶公司 " + VERSION + "此版本为早期开发版，不代表最终质量！"));
    }
}

