package com.xilu.lobotomycorp.init;

import com.xilu.lobotomycorp.LobotomyCorp;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = LobotomyCorp.MODID)
public class ModAttributes {
    public static final IAttribute RED =
            new RangedAttribute(null,LobotomyCorp.MODID + ".red",
                    1,0,999);

    public static final IAttribute WHITE =
            new RangedAttribute(null,LobotomyCorp.MODID + ".white",
                    1,0,999);

    public static final IAttribute BLACK =
            new RangedAttribute(null,LobotomyCorp.MODID + ".black",
                    1,0,999);

    public static final IAttribute BLUE =
            new RangedAttribute(null,LobotomyCorp.MODID + ".blue",
                    1,0,999);

    @SubscribeEvent
    public static void onConstruct(EntityEvent.EntityConstructing event){
        if (event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase livingBase = (EntityLivingBase) event.getEntity();
            livingBase.getAttributeMap().registerAttribute(RED);
            livingBase.getAttributeMap().registerAttribute(WHITE);
            livingBase.getAttributeMap().registerAttribute(BLACK);
            livingBase.getAttributeMap().registerAttribute(BLUE);
        }
    }
}
