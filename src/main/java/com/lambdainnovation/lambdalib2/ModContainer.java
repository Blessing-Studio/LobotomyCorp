/**
 * Copyright (c) Lambda Innovation, 2013-2016
 * This file is part of LambdaLib modding library.
 * https://github.com/LambdaInnovation/LambdaLib
 * Licensed under MIT, see project root for more information.
 */
package com.lambdainnovation.lambdalib2;

import com.lambdainnovation.lambdalib2.registry.RegistryMod;
import com.lambdainnovation.lambdalib2.registry.impl.RegistryTransformer;
import com.lambdainnovation.lambdalib2.s11n.network.NetworkS11n;
import com.lambdainnovation.lambdalib2.util.ReflectionUtils;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;
import java.util.stream.Collectors;

public class ModContainer extends DummyModContainer {

    public static Logger log = LogManager.getLogger("LambdaLib|Core");
    public static final String MODID = "LambdaLib|Core";

    private static ModMetadata getModMetadata() {
        ModMetadata metadata = new ModMetadata();
        metadata.modId = MODID;
        metadata.name = "LambdaLib2|Core";
        metadata.version = LambdaLib2.VERSION;

        return metadata;
    }

    public ModContainer() {
        super(getModMetadata());
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

    @Subscribe
    public void constructMod(FMLConstructionEvent event) {
        log.info("LambdaLib2|Core is loading.");

        ASMDataTable data = event.getASMHarvestedData();
        ReflectionUtils._init(data);

        RegistryTransformer.setRegistryMods(
            ReflectionUtils.getRawObjects(RegistryMod.class.getCanonicalName())
                .stream()
                .map(ASMData::getClassName)
                .distinct()
                .collect(Collectors.toList())
        );
    }

}
