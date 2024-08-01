package com.blessingstudio.lobotomycorp.service;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketService {
    public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(LobotomyCorp.MODID);

    public static void init() {
    }
}
