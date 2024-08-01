package com.blessingstudio.lobotomycorp.components.gui.tab;

import com.blessingstudio.lobotomycorp.service.TabService;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VanillaInventoryTab extends TabBase {
    public VanillaInventoryTab() {
        super(0, 0, 0, new ItemStack(Blocks.CRAFTING_TABLE));
    }

    @Override
    public void onTabClicked() {
        TabService.openInventoryGui();
    }

    @Override
    public boolean shouldAddToList() {
        return true;
    }
}
