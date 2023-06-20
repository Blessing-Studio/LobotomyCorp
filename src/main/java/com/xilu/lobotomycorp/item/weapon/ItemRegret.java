package com.xilu.lobotomycorp.item.weapon;

import com.xilu.lobotomycorp.LobotomyCorp;
import com.xilu.lobotomycorp.item.ItemSwordBase;
import com.xilu.lobotomycorp.util.CommonFunctions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemRegret extends ItemSwordBase {
    public ItemRegret(String name, ToolMaterial material) {
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
                if(hurter instanceof EntityMob){
                    event.setAmount(event.getAmount() * 1.5f);
                    LobotomyCorp.Log("Monster");
                } else {
                    event.setAmount(event.getAmount() * 2.0f);
                }
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false; // 不显示耐久条
    }
}
