package com.blessingstudio.lobotomycorp.service;

import com.blessingstudio.lobotomycorp.components.gui.tab.TabBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraft.inventory.ContainerPlayer;

import java.util.ArrayList;
import java.util.List;

public class TabService {
    private static ArrayList<TabBase> tabList = new ArrayList<TabBase>();
    private static Minecraft mc = FMLClientHandler.instance().getClient();
    private static boolean initWithPotion;
    public static int recipeBookOffset;

    public static void openInventoryGui() {
        TabService.mc.player.connection.sendPacket(new CPacketCloseWindow(mc.player.openContainer.windowId));
        GuiInventory inventory = new GuiInventory(TabService.mc.player);
        TabService.mc.displayGuiScreen(inventory);
    }

    public static void updateTabValues(int cornerX, int cornerY, Class<?> selectedButton) {
        int count = 2;
        for (int i = 0; i < TabService.tabList.size(); i++) {
            TabBase t = TabService.tabList.get(i);

            if (t.shouldAddToList()) {
                t.id = count;
                t.x = cornerX + (count - 2) * 28;
                t.y = cornerY - 28;
                t.enabled = !t.getClass().equals(selectedButton);
                //t.potionOffsetLast = getPotionOffsetNEI();
                count++;
            }
        }
    }

    public static int getRecipeBookOffset(GuiInventory gui) {
        boolean widthTooNarrow = gui.width < 379;
        gui.func_194310_f().func_194303_a(gui.width, gui.height, mc, widthTooNarrow, ((ContainerPlayer) gui.inventorySlots).craftMatrix);
        return gui.func_194310_f().updateScreenPosition(widthTooNarrow, gui.width, gui.getXSize()) - (gui.width - 176) / 2;
    }

    public static void addTabsToList(List<GuiButton> buttonList) {
        for (TabBase tab : TabService.tabList) {
            if (tab.shouldAddToList()) {
                buttonList.add(tab);
            }
        }
    }
}