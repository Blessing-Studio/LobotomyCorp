package com.blessingstudio.lobotomycorp.components.handler;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
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
    public static ISound ALARM_ONE_LEVEL_RECORD;
    public static ISound ALARM_SECOND_LEVEL_RECORD;
    public static ISound ALARM_THREE_LEVEL_RECORD;

    public static final SoundEvent ALARM_ONE_LEVEL =
            new SoundEvent(new ResourceLocation("lobotomycorp", "alarm.one_level")).setRegistryName("alarm.one_level");

    public static final SoundEvent ALARM_SECOND_LEVEL =
            new SoundEvent(new ResourceLocation("lobotomycorp", "alarm.second_level")).setRegistryName("alarm.second_level");

    public static final SoundEvent ALARM_THREE_LEVEL =
            new SoundEvent(new ResourceLocation("lobotomycorp", "alarm.three_level")).setRegistryName("alarm.three_level");
    @SubscribeEvent
    public static void handleItemEvent(RegistryEvent.Register<Item> container) {
        container.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void handleSoundEvent(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(
                ALARM_ONE_LEVEL,
                ALARM_SECOND_LEVEL,
                ALARM_THREE_LEVEL
        );

        ALARM_ONE_LEVEL_RECORD = PositionedSoundRecord.getMasterRecord(ALARM_ONE_LEVEL,1.0f);
        ALARM_SECOND_LEVEL_RECORD = PositionedSoundRecord.getMasterRecord(ALARM_SECOND_LEVEL,1.0f);
        ALARM_THREE_LEVEL_RECORD = PositionedSoundRecord.getMasterRecord(ALARM_THREE_LEVEL,1.0f);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void handleItemModelEvent(ModelRegistryEvent event) {
        for (Item item : ITEMS) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
