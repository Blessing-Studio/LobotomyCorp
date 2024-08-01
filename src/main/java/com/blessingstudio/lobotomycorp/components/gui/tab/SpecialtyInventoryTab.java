package com.blessingstudio.lobotomycorp.components.gui.tab;

import com.blessingstudio.lobotomycorp.service.RegisterService;
import net.minecraft.item.ItemStack;

public class SpecialtyInventoryTab extends TabBase {
    public SpecialtyInventoryTab() {
        super(0, 0, 0, new ItemStack(RegisterService.LC_EGO_HORN));
    }

    @Override
    public void onTabClicked() {
        //GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_OPEN_EXTENDED_INVENTORY, GCCoreUtil.getDimensionID(FMLClientHandler.instance().getClient().world), new Object[]
        //        {}));
        //ClientProxyCore.playerClientHandler.onBuild(0, FMLClientHandler.instance().getClientPlayerEntity());
    }

    @Override
    public boolean shouldAddToList()
    {
        return true;
    }
}

