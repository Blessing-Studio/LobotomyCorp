package com.xilu.lobotomycorp.item.weapon;

import com.google.common.collect.Multimap;
import com.xilu.lobotomycorp.LobotomyCorp;
import com.xilu.lobotomycorp.handler.CapabilityHandler;
import com.xilu.lobotomycorp.interfaces.IMentality;
import com.xilu.lobotomycorp.item.ItemSwordBase;
import com.xilu.lobotomycorp.util.CommonFunctions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemLifeForTheDaredvil extends ItemSwordBase {
    public ItemLifeForTheDaredvil(String name, ToolMaterial material) {
        super(name, material);
        CommonFunctions.addToEventBus(this);
        this.setMaxDamage(Integer.MAX_VALUE); // 设置最大耐久为一个非常大的数值
    }

    @SubscribeEvent
    public void onAttack(LivingHurtEvent event) {
        World world = event.getEntity().world;

        if (!world.isRemote) {
            EntityLivingBase hurter = event.getEntityLiving();
            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();

            if (attacker != null && attacker.getHeldItemMainhand().getItem() == this) {
                float percentage = (float) (event.getAmount() * 2.0);
                float result = hurter.getMaxHealth() * (percentage / 100);
                event.setAmount(result);
                IMentality mentality = Minecraft.getMinecraft().player.getCapability(CapabilityHandler.capMentality, null);
                LobotomyCorp.Log(mentality.toString());
                LobotomyCorp.Log(String.valueOf(mentality.getMentalityValue()));
                mentality.setMentalityValue(mentality.getMentalityValue() - 2);
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false; // 不显示耐久条
    }
}
