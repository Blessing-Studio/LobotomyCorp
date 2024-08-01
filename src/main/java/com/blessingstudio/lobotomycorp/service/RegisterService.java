package com.blessingstudio.lobotomycorp.service;

import com.blessingstudio.lobotomycorp.components.gui.tab.CreativeTab;
import com.blessingstudio.lobotomycorp.components.item.ItemSwordBase;
import com.blessingstudio.lobotomycorp.components.item.weapon.WeaponLifeForTheDareDevil;
import com.blessingstudio.lobotomycorp.components.item.weapon.WeaponPenitence;
import com.blessingstudio.lobotomycorp.components.item.weapon.WeaponRegret;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class RegisterService {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item LC_EGO_PENITENCE = new WeaponPenitence("lc_item_penitence", MaterialService.LC_EGO_PENITENCE).setCreativeTab(CreativeTab.LC_EGO_WEAPON);
    public static final Item LC_EGO_WINGBEAT = new ItemSwordBase("lc_item_wingbeat", MaterialService.LC_EGO_WINGBEAT).setCreativeTab(CreativeTab.LC_EGO_WEAPON);
    public static final Item LC_EGO_CHERRYBLOSSON = new ItemSwordBase("lc_item_cherryblossom", MaterialService.LC_EGO_CHERRYBLOSSON).setCreativeTab(CreativeTab.LC_EGO_WEAPON);
    public static final Item LC_EGO_REGRET = new WeaponRegret("lc_item_regret", MaterialService.LC_EGO_REGRET).setCreativeTab(CreativeTab.LC_EGO_WEAPON);
    public static final Item LC_EGO_SOMEWHERESPEAR = new ItemSwordBase("lc_item_somewherespear", MaterialService.LC_EGO_SOMEWHERESPEAR).setCreativeTab(CreativeTab.LC_EGO_WEAPON);
    public static final Item LC_EGO_LIFEFORTHEDARE = new WeaponLifeForTheDareDevil("lc_item_lifeforthedaredevil", MaterialService.LC_EGO_LIFEFORTHEDARE).setCreativeTab(CreativeTab.LC_EGO_WEAPON);
    public static final Item LC_EGO_CHRISTMAS = new ItemSwordBase("lc_item_christmas", MaterialService.LC_EGO_CHRISTMAS).setCreativeTab(CreativeTab.LC_EGO_WEAPON);
    public static final Item LC_EGO_REDEYES = new ItemSwordBase("lc_item_redeyes", MaterialService.LC_EGO_REDEYES).setCreativeTab(CreativeTab.LC_EGO_WEAPON);
    public static final Item LC_EGO_HORN = new ItemSwordBase("lc_item_horn", MaterialService.LC_EGO_HORN).setCreativeTab(CreativeTab.LC_EGO_WEAPON);
    public static final Item LC_EGO_LOGGING = new ItemSwordBase("lc_item_logging", MaterialService.LC_EGO_LOGGING).setCreativeTab(CreativeTab.LC_EGO_WEAPON);

    public static void handleItem(Item item, String name) {
        item.setRegistryName(name);
        item.setUnlocalizedName(name);
        ITEMS.add(item);
    }
}