package com.blessingstudio.lobotomycorp.components.item;

import java.util.List;

import com.blessingstudio.lobotomycorp.LobotomyCorp;
import com.blessingstudio.lobotomycorp.classes.interfaces.IHasModel;
import com.blessingstudio.lobotomycorp.components.data.NbtData;
import com.blessingstudio.lobotomycorp.components.gui.tab.CreativeTab;
import com.blessingstudio.lobotomycorp.service.CommonService;
import com.blessingstudio.lobotomycorp.service.CustomNbtDataService;
import com.blessingstudio.lobotomycorp.service.RegisterService;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPickaxeBase extends ItemPickaxe implements IHasModel {
	private boolean overrideRarity = false;
	private EnumRarity enumRarity = EnumRarity.COMMON;
	protected boolean showGuaSocketDesc = false;
	protected boolean shiftToShowDesc = false;
	protected boolean use_flavor = false;
	protected boolean logNBT = false;
	protected boolean glitters = false;
	//for accessing the private value
	protected ToolMaterial toolMaterial;

	public ItemPickaxeBase(String name, ToolMaterial material) {
		super(material);
		setCreativeTab(CreativeTab.LC_EGO_WEAPON);
		RegisterService.handleItem(this, name);
		toolMaterial = material;
	}

	public ItemPickaxeBase setRarity(EnumRarity enumRarity) {
		overrideRarity = true;
		this.enumRarity = enumRarity;
		return this;
	}

	public EnumRarity getRarity(ItemStack stack) {
		if (overrideRarity) {
			return enumRarity;
		}
		else {
			return super.getRarity(stack);
		}
	}

	public String GetStringForThisByKey(String key) {
		return CommonService.GetStringLocalTranslated(getUnlocalizedName() + key);
	}

	public String GetBasicDesc() {
		return CommonService.GetStringLocalTranslated(getUnlocalizedName() + NbtData.DESC_COMMON);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase living, int count) {
		//Particle;
		super.onUsingTick(stack, living, count);
		//Idealland.LogWarning(String.format("base onUsingTick %s",count));

		if (living.world.isRemote) {
			clientUseTick(stack, living, getMaxItemUseDuration(stack) - count);
		}
		else {
			serverUseTick(stack, living, getMaxItemUseDuration(stack) - count);
		}
	}

	public void clientUseTick(ItemStack stack, EntityLivingBase living, int count) {

	}

	public void serverUseTick(ItemStack stack, EntityLivingBase living, int count) {

	}

	@Override
	public void registerModels() {
		LobotomyCorp.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		//IDLSkillNBT.addInformation(stack,world,tooltip,flag,shiftToShowDesc, showGuaSocketDesc, use_flavor, getMainDesc(stack,world,tooltip,flag));

		if (logNBT) {
			tooltip.add(CustomNbtDataService.getNBT(stack).toString());
		}
	}

	@SideOnly(Side.CLIENT)
	public String descGetKey(ItemStack stack, World world, boolean showFlavor) {
		return showFlavor ? (stack.getUnlocalizedName() + NbtData.FLAVOR_KEY)
				: (stack.getUnlocalizedName() + NbtData.DESC_COMMON);
	}

	@SideOnly(Side.CLIENT)
	public String getMainDesc(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (CommonService.isShiftPressed() || !shiftToShowDesc) {
			String key = descGetKey(stack,world,false);
			if (I18n.hasKey(key)) {
				return I18n.format(key);
			}
			else {
				return "";
			}
		}

		if (!CommonService.isShiftPressed() && use_flavor) {
			String key = descGetKey(stack,world,true);
			if (I18n.hasKey(key)) {
				return I18n.format(key);
			}
			else {
				return "";
			}
		}

		return "";
	}

	//for accessing private values
	protected float getBaseAttackDamage() {
		return 3.0F + toolMaterial.getAttackDamage();
	}
}