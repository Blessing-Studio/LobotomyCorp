package com.blessingstudio.lobotomycorp.components.item.weapon;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import com.blessingstudio.lobotomycorp.components.item.ItemPickaxeBase;
import com.blessingstudio.lobotomycorp.service.CommonService;
import com.blessingstudio.lobotomycorp.service.MathService;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.UUID;

import static com.blessingstudio.lobotomycorp.service.MathService.*;

public class WeaponRegret extends ItemPickaxeBase {
    private static final UUID IRUUID = UUID.fromString("D2D68FFF-6225-52A7-ABC5-9DD33E2C1210");

    public WeaponRegret(String name, Item.ToolMaterial material) {
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
                float amount = event.getAmount();
                if(hurter instanceof EntityMob){
                    event.setAmount(getActualRedDamage(1.5f, amount));
                    LobotomyCorp.Log("Monster");
                } else {
                    event.setAmount(getActualRedDamage(2.0f, amount));
                }
            }
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false; // 不显示耐久条
    }

    @Override
    @Nonnull
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            AttributeModifier speedModifier = new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.2, 0);

            // remove the entries added by superclass (to allow 'overwriting')
            multimap.remove(SharedMonsterAttributes.ATTACK_SPEED.getName(), speedModifier);
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), speedModifier);
        }

        return multimap;
    }
}
