package me.lostwizzen.portableworkstations;

import org.bukkit.NamespacedKey;

import day.dean.skullcreator.SkullCreator;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.lostwizzen.portableworkstations.constants.PortableWorkstationsConstants;
import net.md_5.bungee.api.ChatColor;

public class PortableWorkstationsItem {
	
	private PortableWorkstationsItem() {
	}

	public static ItemGroup defaultItemGroup;
	public static SlimefunItemStack portableCartography;
	public static SlimefunItemStack portableStonecutter;

	public static ItemGroup getDefaultItemGroup() {
		if (defaultItemGroup == null) {
			String menuName = PortableWorkstations.getInstance().messages.get(PortableWorkstationsConstants.MENU_NAME);
			defaultItemGroup = new ItemGroup(new NamespacedKey(PortableWorkstations.getInstance(), "PW_KEY_GENERAL"),
					new CustomItemStack(SkullCreator.itemFromBase64(PortableWorkstationsConstants.HEAD_WORKBENCH_BASE64),
							"§b"+menuName));
		}
		return defaultItemGroup;
	}

	public static SlimefunItemStack getPortableStonecutter() {
		if (portableStonecutter == null) {
			String itemName = ChatColor.stripColor(PortableWorkstations.getInstance().messages.get(PortableWorkstationsConstants.STONECUTTER_NAME));
			String itemLore = ChatColor.stripColor(PortableWorkstations.getInstance().messages.get(PortableWorkstationsConstants.STONECUTTER_LORE));
			String actionHint = PortableWorkstations.getInstance().messages.get(PortableWorkstationsConstants.ACTION_HINT);
			portableStonecutter = new SlimefunItemStack("PW_STONECUTTER",
					SkullCreator.itemFromBase64(PortableWorkstationsConstants.HEAD_STONECUTTER_BASE64),
					"§6"+itemName, "§f"+itemLore, "",
					"§7by §3lostwizzen", "", actionHint);
		}
		return portableStonecutter;
	}

	public static SlimefunItemStack getPortableCartography() {
		if (portableCartography == null) {
			String itemName = ChatColor.stripColor(PortableWorkstations.getInstance().messages.get(PortableWorkstationsConstants.CARTOGRAPHY_NAME));
			String itemLore = ChatColor.stripColor(PortableWorkstations.getInstance().messages.get(PortableWorkstationsConstants.CARTOGRAPHY_LORE));
			String actionHint = PortableWorkstations.getInstance().messages.get(PortableWorkstationsConstants.ACTION_HINT);
			portableCartography = new SlimefunItemStack("PW_CARTOGRAPHY",
					SkullCreator.itemFromBase64(PortableWorkstationsConstants.HEAD_CARTOGRAPHY_BASE64),
					"§6"+itemName, "§f"+itemLore, "",
					"§7by §3lostwizzen", "", actionHint);
		}
		return portableCartography;
	}

}
