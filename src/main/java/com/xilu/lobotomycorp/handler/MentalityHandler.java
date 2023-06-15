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
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MentalityHandler {
    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof EntityPlayer){
            ICapabilitySerializable<NBTTagCompound> providerConsciousness = new MentalityCapability.ProvidePlayer();
            event.addCapability(new ResourceLocation(LobotomyCorp.MODID + ":" + "consciousness"), providerConsciousness);
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
        //创建一个新的玩家清醒度Capability变量
        Capability<IMentality> capabilityConsciousness = CapabilityHandler.capConsciousness;
        //获取新创建的Capability变量中的Storage空间
        Capability.IStorage<IMentality> storageConsciousness = capabilityConsciousness.getStorage();
        //如果原玩家实体带有对应的Capability
        if(event.getOriginal().hasCapability(capabilityConsciousness, null) && event.getEntityPlayer().hasCapability(capabilityConsciousness, null)){
            //将原玩家实体对应Capability中的NBT数据取出
            NBTBase nbt = storageConsciousness.writeNBT(capabilityConsciousness, event.getOriginal().getCapability(capabilityConsciousness, null), null);
            //将NBT数据放入克隆出的新玩家实体的Capability中
            storageConsciousness.readNBT(capabilityConsciousness, event.getEntityPlayer().getCapability(capabilityConsciousness, null), null, nbt);
        }
    }
}
