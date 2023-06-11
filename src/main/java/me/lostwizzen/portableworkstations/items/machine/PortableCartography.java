package me.lostwizzen.portableworkstations.items.machine;

import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.lostwizzen.portableworkstations.PortableWorkstations;
import me.lostwizzen.portableworkstations.constants.PortableWorkstationsConstants;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.CartographyTableMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;

public class PortableCartography extends SlimefunItem implements NotPlaceable {
	
	public PortableCartography(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
	}

	@Override
	public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);
	}
	
	private void onItemRightClick(PlayerRightClickEvent e) {
		e.cancel();
		ServerPlayer ePlayer = ((CraftPlayer) e.getPlayer()).getHandle();
		int containerID = ePlayer.nextContainerCounter();
		FakeCartography fakeCartography = new FakeCartography(containerID, e.getPlayer());
		ePlayer.connection.send(new ClientboundOpenScreenPacket(containerID, MenuType.CARTOGRAPHY_TABLE, fakeCartography.getTitle()));
		ePlayer.containerMenu = fakeCartography;
		e.getPlayer().openInventory(fakeCartography.getBukkitView());
		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1, 1);
	}
	
	private static class FakeCartography extends CartographyTableMenu {
        public FakeCartography(final int containerId, final Player player) {
        	super(containerId, ((CraftPlayer) player).getHandle().getInventory(), ContainerLevelAccess.create(((CraftWorld) player.getWorld()).getHandle(), new BlockPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ())));
            String name = ChatColor.stripColor(PortableWorkstations.getInstance().messages.get(PortableWorkstationsConstants.CARTOGRAPHY_NAME));
            this.checkReachable = false;
            this.setTitle(Component.literal("§6"+name));
        }
    }

}
