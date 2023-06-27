package com.xilu.lobotomycorp.handler;

import com.xilu.lobotomycorp.capability.MentalityCapability;
import com.xilu.lobotomycorp.interfaces.IMentality;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHandler {
    @CapabilityInject(IMentality.class)
    public static Capability<IMentality> capMentality;

    public static void setupCapabilities(){
        CapabilityManager.INSTANCE.register(IMentality.class, new MentalityCapability.Storage(), MentalityCapability.MentalityImplementation.class);
    }
}
