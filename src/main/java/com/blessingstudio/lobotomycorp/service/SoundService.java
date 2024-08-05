package com.blessingstudio.lobotomycorp.service;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SoundService {
    public static void playSound(EntityPlayer player, SoundEvent sound) {
        World world = player.world;
        world.playSound(player, player.getPosition(), sound, SoundCategory.MUSIC, 1.0F, 1.0F);
    }
}