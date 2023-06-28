package com.xilu.lobotomycorp.item.weapon;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.xilu.lobotomycorp.LobotomyCorp;
import com.xilu.lobotomycorp.item.ItemHoeBase;
import com.xilu.lobotomycorp.item.ItemPickaxeBase;
import com.xilu.lobotomycorp.item.ItemSwordBase;
import com.xilu.lobotomycorp.item.ModItems;
import com.xilu.lobotomycorp.util.CommonFunctions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ItemRegret extends ItemPickaxeBase {
    private static final UUID IRUUID = UUID.fromString("D2D68FFF-6225-52A7-ABC5-9DD33E2C1210");

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

//    @Override
//    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack){
//        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
//
//        if (slot == EntityEquipmentSlot.MAINHAND) {
//            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
//                    new AttributeModifier(IRUUID,"regret",1,0));
//        }
//
//        return multimap;
//    }

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
