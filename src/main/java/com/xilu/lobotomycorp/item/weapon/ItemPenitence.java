package com.xilu.lobotomycorp.item.weapon;

import com.xilu.lobotomycorp.LobotomyCorp;
import com.xilu.lobotomycorp.handler.CapabilityHandler;
import com.xilu.lobotomycorp.interfaces.IMentality;
import com.xilu.lobotomycorp.item.ItemSwordBase;
import com.xilu.lobotomycorp.util.CommonFunctions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemPenitence extends ItemSwordBase {

    public ItemPenitence(String name, ToolMaterial material) {
        super(name, material);
        CommonFunctions.addToEventBus(this);
        this.setMaxDamage(-1); // 设置最大耐久为一个非常大的数值
    }

    @SubscribeEvent
    public void onAttack(LivingHurtEvent event) {
        World world = event.getEntity().world;

        if (!world.isRemote) {
            EntityLivingBase hurter = event.getEntityLiving();
            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();

            if (attacker != null && attacker.getHeldItemMainhand().getItem() == this) {
                if(attacker instanceof EntityPlayer && attacker.getRNG().nextFloat() < 0.1f) {
                    IMentality mentality = attacker.getCapability(CapabilityHandler.capMentality, null);
                    mentality.setMentalityValue(mentality.getMentalityValue() + 2 >= 20 ? 20 : mentality.getMentalityValue() + 2);
                }

                try {
                    event.setCanceled(true);
                    IMentality mentality = hurter.getCapability(CapabilityHandler.capMentality, null);
                    mentality.setMentalityValue(event.getAmount() * 1.5f);
                } catch (Exception ex) {
                    LobotomyCorp.Log(ex.getMessage());
                    return;
                }
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false; // 不显示耐久条
    }
}
