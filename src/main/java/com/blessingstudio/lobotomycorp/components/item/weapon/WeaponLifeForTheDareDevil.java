package com.blessingstudio.lobotomycorp.components.item.weapon;

import com.blessingstudio.lobotomycorp.components.item.ItemSwordBase;
import com.blessingstudio.lobotomycorp.service.CommonService;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.blessingstudio.lobotomycorp.service.MathService.getActualBlackDamage;
import static com.blessingstudio.lobotomycorp.service.MathService.getActualBlueDamage;

public class WeaponLifeForTheDareDevil extends ItemSwordBase {
    public WeaponLifeForTheDareDevil(String name, Item.ToolMaterial material) {
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
                event.setAmount(getActualBlueDamage(2.0f, event.getAmount(), hurter.getMaxHealth()));
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false; // 不显示耐久条
    }
}
