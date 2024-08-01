package com.blessingstudio.lobotomycorp.components.gui.tab;

import com.blessingstudio.lobotomycorp.service.RegisterService;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTab {
    public static final CreativeTabs LC_EGO_WEAPON = new CreativeTabs(CreativeTabs.getNextID(), "lc_ego_weapon_Tab") {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(RegisterService.LC_EGO_PENITENCE);
        }
    };
}
