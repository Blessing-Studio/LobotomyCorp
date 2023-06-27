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

    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){

    }
}
