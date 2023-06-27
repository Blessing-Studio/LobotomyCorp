package com.xilu.lobotomycorp.handler;

import com.xilu.lobotomycorp.LobotomyCorp;
import com.xilu.lobotomycorp.capability.MentalityCapability;
import com.xilu.lobotomycorp.interfaces.IMentality;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
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
    public void onPlayerClone(PlayerEvent.Clone event){
        //创建一个新的玩家清醒度Capability变量
        Capability<IMentality> capabilityMentality = CapabilityHandler.capMentality;
        //获取新创建的Capability变量中的Storage空间
        Capability.IStorage<IMentality> storageConsciousness = capabilityMentality.getStorage();

        //如果原玩家实体带有对应的Capability
        if(event.getOriginal().hasCapability(capabilityMentality, null) && event.getEntityPlayer().hasCapability(capabilityMentality, null)){
            //将原玩家实体对应Capability中的NBT数据取出
            NBTBase nbt = storageConsciousness.writeNBT(capabilityMentality, event.getOriginal().getCapability(capabilityMentality, null), null);
            //将NBT数据放入克隆出的新玩家实体的Capability中
            storageConsciousness.readNBT(capabilityMentality, event.getEntityPlayer().getCapability(capabilityMentality, null), null, nbt);
        }
    }
}

