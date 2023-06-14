package com.xilu.lobotomycorp.entity;

import com.xilu.lobotomycorp.IdlFramework;
import com.xilu.lobotomycorp.entity.creatures.moroon.EntityMoroonUnitBase;
import com.xilu.lobotomycorp.entity.creatures.render.RenderBullet;
import com.xilu.lobotomycorp.entity.creatures.render.RenderMoroonHumanoid;
import com.xilu.lobotomycorp.entity.projectiles.EntityIdlProjectile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMoroonUnitBase.class, RenderMoroonHumanoid::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityIdlProjectile.class, renderManager -> new RenderBullet<>(renderManager, new ResourceLocation(IdlFramework.MODID,
                "textures/entity/projectiles/bullet_norm.png")));
    }
}
