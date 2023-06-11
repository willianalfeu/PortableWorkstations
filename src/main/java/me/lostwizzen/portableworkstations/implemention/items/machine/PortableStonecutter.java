package me.lostwizzen.portableworkstations.implemention.items.machine;

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
import me.lostwizzen.portableworkstations.constants.PortableWorkstationsConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.StonecutterMenu;

public class PortableStonecutter extends SlimefunItem implements NotPlaceable {
	
	public PortableStonecutter(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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
		FakeStoneCutter fakeStoneCutter = new FakeStoneCutter(containerID, e.getPlayer());
		ePlayer.connection.send(new ClientboundOpenScreenPacket(containerID, MenuType.STONECUTTER, fakeStoneCutter.getTitle()));
		ePlayer.containerMenu = fakeStoneCutter;
		e.getPlayer().openInventory(fakeStoneCutter.getBukkitView());
		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.UI_STONECUTTER_SELECT_RECIPE, 1, 1);
	}
	
	private static class FakeStoneCutter extends StonecutterMenu {
        public FakeStoneCutter(final int containerId, final Player player) {
            super(containerId, ((CraftPlayer) player).getHandle().getInventory(), ContainerLevelAccess.create(((CraftWorld) player.getWorld()).getHandle(), new BlockPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ())));
            this.checkReachable = false;
            this.setTitle(Component.literal(PortableWorkstationsConstants.TITLE_STONECUTTER));
        }
    }

}
