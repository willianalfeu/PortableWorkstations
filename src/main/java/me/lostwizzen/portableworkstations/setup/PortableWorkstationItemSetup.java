package me.lostwizzen.portableworkstations.setup;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.lostwizzen.portableworkstations.PortableWorkstations;
import me.lostwizzen.portableworkstations.PortableWorkstationsItem;
import me.lostwizzen.portableworkstations.implemention.items.machine.PortableCartography;
import me.lostwizzen.portableworkstations.implemention.items.machine.PortableStonecutter;

public class PortableWorkstationItemSetup {
	private PortableWorkstationItemSetup() {
	}

	public static void setup(@Nonnull PortableWorkstations plugin) {
		new PortableStonecutter(PortableWorkstationsItem.PW_GENERAL, PortableWorkstationsItem.PW_STONECUTTER, RecipeType.ENHANCED_CRAFTING_TABLE, 
			new ItemStack[]{
			    new ItemStack(Material.BOOK), new ItemStack(Material.STONECUTTER), null,
			    null, null, null,
			    null, null, null
		}).register(plugin);
		
		new PortableCartography(PortableWorkstationsItem.PW_GENERAL, PortableWorkstationsItem.PW_CARTOGRAPHY, RecipeType.ENHANCED_CRAFTING_TABLE, 
				new ItemStack[]{
				    new ItemStack(Material.BOOK), new ItemStack(Material.CARTOGRAPHY_TABLE), null,
				    null, null, null,
				    null, null, null
			}).register(plugin);
	}
}
