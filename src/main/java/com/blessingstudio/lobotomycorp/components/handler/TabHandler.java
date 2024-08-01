package com.blessingstudio.lobotomycorp.components.handler;

import com.blessingstudio.lobotomycorp.components.gui.tab.VanillaInventoryTab;
import com.blessingstudio.lobotomycorp.service.TabService;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.blessingstudio.lobotomycorp.service.TabService.recipeBookOffset;
import static com.blessingstudio.lobotomycorp.service.TabService.getRecipeBookOffset;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class TabHandler {
    @SubscribeEvent
    public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof GuiInventory)
        {
            int guiLeft = (event.getGui().width - 176) / 2;
            int guiTop = (event.getGui().height - 166) / 2;
            recipeBookOffset = getRecipeBookOffset((GuiInventory) event.getGui());
            guiLeft += recipeBookOffset;

            TabService.updateTabValues(guiLeft, guiTop, VanillaInventoryTab.class);
            TabService.addTabsToList(event.getButtonList());
        }
    }
}
