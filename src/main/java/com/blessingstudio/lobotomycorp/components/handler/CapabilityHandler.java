package com.blessingstudio.lobotomycorp.components.handler;

import com.blessingstudio.lobotomycorp.classes.interfaces.IMentality;
import com.blessingstudio.lobotomycorp.components.capability.MentalityCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHandler {
    @CapabilityInject(IMentality.class)
    public static Capability<IMentality> mentalityCapability;

    public static void setupCapabilities() {
        CapabilityManager.INSTANCE.register(IMentality.class, new MentalityCapability.Storage(), MentalityCapability.Implementation.class);
    }
}
