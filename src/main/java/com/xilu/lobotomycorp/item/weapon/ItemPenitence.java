package com.xilu.lobotomycorp.item.weapon;

import com.xilu.lobotomycorp.item.ItemSwordBase;
import com.xilu.lobotomycorp.util.CommonFunctions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemPenitence extends ItemSwordBase {
    private int continuousAttackCount = 0;

    public ItemPenitence(String name, ToolMaterial material) {
        super(name, material);
        CommonFunctions.addToEventBus(this);
    }

    @SubscribeEvent
    public void OnAttack(LivingHurtEvent event) {
        World world = event.getEntity().world;

        if (!world.isRemote) {
            EntityLivingBase hurter = event.getEntityLiving();
            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();

            if (attacker != null && attacker.getHeldItemMainhand().getItem() == this) {
                continuousAttackCount++;

                if (continuousAttackCount == 3) {
                    attacker.heal(20);
                    continuousAttackCount = 0;
                }
            }
        }
    }
}
