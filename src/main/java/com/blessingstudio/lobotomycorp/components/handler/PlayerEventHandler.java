package com.blessingstudio.lobotomycorp.components.handler;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import com.blessingstudio.lobotomycorp.classes.interfaces.IMentality;
import com.blessingstudio.lobotomycorp.components.capability.MentalityCapability;
import com.blessingstudio.lobotomycorp.service.SoundService;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import static com.blessingstudio.lobotomycorp.LobotomyCorp.*;

@Mod.EventBusSubscriber
public class PlayerEventHandler {
    @SubscribeEvent
    public static void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof EntityPlayer){
            ICapabilitySerializable<NBTTagCompound> providerMentality = new MentalityCapability.ProvidePlayer();
            event.addCapability(new ResourceLocation(MODID + ":" + "mentality"), providerMentality);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        Capability<IMentality> capabilityConsciousness = CapabilityHandler.mentalityCapability;
        Capability.IStorage<IMentality> storageMentality = capabilityConsciousness.getStorage();
        if(event.getOriginal().hasCapability(capabilityConsciousness, null) && event.getEntityPlayer().hasCapability(capabilityConsciousness, null)){
            NBTBase nbt = storageMentality.writeNBT(capabilityConsciousness, event.getOriginal().getCapability(capabilityConsciousness, null), null);
            storageMentality.readNBT(capabilityConsciousness, event.getEntityPlayer().getCapability(capabilityConsciousness, null), null, nbt);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        event.player.sendMessage(new TextComponentString("感谢主管游玩 脑叶公司 " + VERSION + " 此版本为早期开发版，不代表最终质量！"));

        if (event.player.hasCapability(CapabilityHandler.mentalityCapability, null)) {
            IMentality mentality = event.player.getCapability(CapabilityHandler.mentalityCapability, null);
            LobotomyCorp.Log(""+mentality.getMentalityValue());
            if (mentality != null && mentality.getMentalityValue() == 100) {
                mentality.setMentalityValue(10d);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingAttackEvent(LivingAttackEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer ep = (EntityPlayer) event.getEntity();
            if (ep.hasCapability(CapabilityHandler.mentalityCapability, null)) {
                IMentality mentality = ep.getCapability(CapabilityHandler.mentalityCapability, null);
                //LobotomyCorp.Log("伤害量：" + event.getAmount());
                LobotomyCorp.Log("当前精神值：" + mentality.getMentalityValue());

                if (mentality.getMentalityValue() > 0) {
                    mentality.setMentalityValue(mentality.getMentalityValue() - event.getAmount());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerRespawnEvent event) {
        EntityPlayer player = event.player;
        IMentality capability = player.getCapability(CapabilityHandler.mentalityCapability, null);

        if (capability != null) {
            LobotomyCorp.Log("重生后的精神值为：" + capability.getMentalityValue());
            capability.setMentalityValue(20F);
        }
    }

    public static void onPlayerTick(PlayerTickEvent event) {
        EntityPlayer player = event.player;
        World world = player.world;

        if(!world.isRemote) {

        }
    }
}