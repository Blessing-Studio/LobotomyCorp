package com.xilu.lobotomycorp.init;

import com.xilu.lobotomycorp.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTab {
	public static final CreativeTabs LC_EGO_WEAPON = new CreativeTabs(CreativeTabs.getNextID(), "lc_ego_weapon_Tab")
	{
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.LC_PEBOX);
        }
    };

    public static final CreativeTabs LC_ITEM = new CreativeTabs(CreativeTabs.getNextID(), "lc_item_Tab")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.LC_COGITO);
        }
    };
}
