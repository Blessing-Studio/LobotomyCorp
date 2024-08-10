package com.blessingstudio.lobotomycorp.components.handler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.blessingstudio.lobotomycorp.service.RegisterService.*;

@Mod.EventBusSubscriber
public class RegisterHandler {
    @SubscribeEvent
    public static void handleItemEvent(RegistryEvent.Register<Item> container) {
        container.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void handleItemModelEvent(ModelRegistryEvent event) {
        for (Item item : ITEMS) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
