package me.lostwizzen.portableworkstations;

import org.bukkit.NamespacedKey;

import day.dean.skullcreator.SkullCreator;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.lostwizzen.portableworkstations.constants.PortableWorkstationsConstants;

public class PortableWorkstationsItem {
    private PortableWorkstationsItem() {}

    //groups
    public static final ItemGroup PW_GENERAL = new ItemGroup(new NamespacedKey(PortableWorkstations.getInstance(), "PW_KEY_GENERAL"), new CustomItemStack(SkullCreator.itemFromBase64(PortableWorkstationsConstants.HEAD_WORKBENCH_BASE64), "&bPortable Workstations"));

    //workstations
    public static final SlimefunItemStack PW_STONECUTTER = new SlimefunItemStack("PW_STONECUTTER", SkullCreator.itemFromBase64(PortableWorkstationsConstants.HEAD_STONECUTTER_BASE64), PortableWorkstationsConstants.TITLE_STONECUTTER, "&fA portable stone cutter", "", "&7by &3lostwizzen", "", "&eRight Click &7to open");
    public static final SlimefunItemStack PW_CARTOGRAPHY = new SlimefunItemStack("PW_CARTOGRAPHY", SkullCreator.itemFromBase64(PortableWorkstationsConstants.HEAD_CARTOGRAPHY_BASE64), PortableWorkstationsConstants.TITLE_CARTOGRAPHY, "&fA portable cartography table", "", "&7by &3lostwizzen", "", "&eRight Click &7to open");

}

