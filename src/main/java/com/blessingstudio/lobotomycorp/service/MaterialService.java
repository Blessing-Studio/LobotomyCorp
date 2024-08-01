package com.blessingstudio.lobotomycorp.service;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class MaterialService {
    public static final float LOW = 16.0F;
    public static final float FAST = 10.0F;
    public static final float MEDIUM = 13.0F;
    public static final Item.ToolMaterial LC_EGO_REGRET = EnumHelper.addToolMaterial("lc_item_regret", 1, Integer.MAX_VALUE, LOW, 14.0F, 0);
    public static final Item.ToolMaterial LC_EGO_PENITENCE = EnumHelper.addToolMaterial("lc_item_penitence", 1, Integer.MAX_VALUE, FAST, 8.0F, 0);
    public static final Item.ToolMaterial LC_EGO_WINGBEAT = EnumHelper.addToolMaterial("lc_item_wingbeat", 3, Integer.MAX_VALUE, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial LC_EGO_CHERRYBLOSSON = EnumHelper.addToolMaterial("lc_item_cherryblossom", 3, Integer.MAX_VALUE, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial LC_EGO_SOMEWHERESPEAR = EnumHelper.addToolMaterial("lc_item_somewherespear", 3, Integer.MAX_VALUE, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial LC_EGO_LIFEFORTHEDARE = EnumHelper.addToolMaterial("lc_item_lifeforthedaredevil", 3, Integer.MAX_VALUE, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial LC_EGO_CHRISTMAS = EnumHelper.addToolMaterial("lc_item_christmas", 3, Integer.MAX_VALUE, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial LC_EGO_REDEYES = EnumHelper.addToolMaterial("lc_item_redeyes", 3, Integer.MAX_VALUE, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial LC_EGO_LOGGING = EnumHelper.addToolMaterial("lc_item_horn", 3, Integer.MAX_VALUE, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial LC_EGO_HORN = EnumHelper.addToolMaterial("lc_item_logging", 3, Integer.MAX_VALUE, 8.0F, 3.0F, 10);
}
