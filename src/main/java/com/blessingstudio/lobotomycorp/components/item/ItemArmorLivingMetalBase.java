package com.blessingstudio.lobotomycorp.components.item;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import com.blessingstudio.lobotomycorp.classes.interfaces.IHasModel;
import com.blessingstudio.lobotomycorp.components.data.CommonData;
import com.blessingstudio.lobotomycorp.components.data.NbtData;
import com.blessingstudio.lobotomycorp.components.gui.tab.CreativeTab;
import com.blessingstudio.lobotomycorp.service.CommonService;
import com.blessingstudio.lobotomycorp.service.CustomNbtDataService;
import com.blessingstudio.lobotomycorp.service.RegisterService;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//try to sync with ItemBase
@Mod.EventBusSubscriber(modid = LobotomyCorp.MODID)
public class ItemArmorLivingMetalBase extends ItemArmorBase implements IHasModel {
	public ItemArmorLivingMetalBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
        ignoreVanillaSystem = false;
        setCreativeTab(CreativeTab.LC_EGO_WEAPON);
        RegisterService.handleItem(this, name);
	}

	public int getRepairAmount(ItemStack stack, Entity entityIn)
	{
		return 1;
	}

	public double getHealthPlus(ItemStack stack)
	{
		return 1;
	}

	@Override
	public void onUpdateWearing(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdateWearing(stack, worldIn, entityIn, itemSlot, isSelected);
		EntityPlayer player = (EntityPlayer) entityIn;

		if (worldIn.getWorldTime() % CommonData.TICK_PER_SECOND == 0) {
            boolean flagRule = player.world.getGameRules().getBoolean("naturalRegeneration");
            FoodStats stats = player.getFoodStats();
            if (flagRule && stats.getFoodLevel() >= 20) {
                // && stats.getSaturationLevel() > 0.0F &&
                if (player.getHealth() >= player.getMaxHealth()) {
                    CommonService.RepairItem(stack, getRepairAmount(stack, entityIn));
                }
            }
		}

		if (!worldIn.isRemote) {
            if (((float)stack.getItemDamage() / stack.getMaxDamage()) >= 0.9f) {
                if (worldIn.getWorldTime() % CommonData.TICK_PER_SECOND == 0 && !CustomNbtDataService.GetBoolean(stack, NbtData.BIOMETAL_WARNED, false)) {
                    CustomNbtDataService.SetBoolean(stack, NbtData.BIOMETAL_WARNED, true);
                    CommonService.SendMsgToPlayer((EntityPlayerMP) player, "lobotomycorp.msg.low_dura", stack.getDisplayName());
                }
            }
            else {
                CustomNbtDataService.SetBoolean(stack, NbtData.BIOMETAL_WARNED, false);
            }
        }
	}

	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == this.armorType) {
			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(ARMOR_MODIFIERS_OVERRIDE[equipmentSlot.getIndex()],"Health modifier", getHealthPlus(stack), 0));
		}

		return multimap;
	}

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void OnWearerHeal(LivingHealEvent event) {
        if (event.isCanceled()) {
            return;
        }

        EntityLivingBase livingBase = event.getEntityLiving();
        for (EntityEquipmentSlot slot:
                EntityEquipmentSlot.values()){
            ItemStack stack = livingBase.getItemStackFromSlot(slot);
            if (stack.getItem() instanceof ItemArmorLivingMetalBase) {
                CommonService.RepairItem(stack,Math.round(event.getAmount()));
            }
        }
    }
}