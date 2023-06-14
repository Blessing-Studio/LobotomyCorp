package com.xilu.lobotomycorp.util.sound;

import com.xilu.lobotomycorp.util.ModSoundHandler;
import com.xilu.lobotomycorp.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSoundEvent extends SoundEvent {
    public ModSoundEvent(String path) {
        super(new ResourceLocation(Reference.MOD_ID, path));
        ModSoundHandler.SOUNDS.add(this);
        setRegistryName(path);
    }
}
