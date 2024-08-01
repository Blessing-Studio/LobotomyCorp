package com.blessingstudio.lobotomycorp.components.item.weapon;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import com.blessingstudio.lobotomycorp.classes.interfaces.IMentality;
import com.blessingstudio.lobotomycorp.components.handler.CapabilityHandler;
import com.blessingstudio.lobotomycorp.components.item.ItemSwordBase;
import com.blessingstudio.lobotomycorp.service.CommonService;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WeaponPenitence extends ItemSwordBase {

    public WeaponPenitence(String name, ToolMaterial material) {
        super(name, material);
        CommonService.addToEventBus(this);
    }

    @SubscribeEvent
    public void onAttack(LivingHurtEvent event) {
        World world = event.getEntity().world;

        if (!world.isRemote) {
            EntityLivingBase hurter = event.getEntityLiving();
            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();

            if (attacker != null && attacker.getHeldItemMainhand().getItem() == this) {
                IMentality mentality = hurter.getCapability(CapabilityHandler.mentalityCapability, null);

                if (hurter.hasCapability(CapabilityHandler.mentalityCapability, null)) {
                    try {
                        event.setCanceled(true);
                        mentality.setMentalityValue(event.getAmount() * 1.5f);
                    } catch (Exception ex) {
                        LobotomyCorp.Log(ex.getMessage());
                    }
                } else if (!(hurter instanceof EntityPlayer)) {

                }
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false; // 不显示耐久条
    }
}