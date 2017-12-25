package de.heisluft.ui;

import java.util.ConcurrentModificationException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class UncreativeItems extends JavaPlugin implements Listener {
	
	private final Inventory extraItems = Bukkit.getServer().createInventory(null, 27,
			"\u00a7l\u00a76ExtraItems\u00a7r");
	
	private static String fromList(List<String> list) {
		StringBuilder result = new StringBuilder();
		int s = list.size();
		for (int i = 0; i < s; i++) {
			result.append(list.get(i)).append(s - i == 1 ? "" : s - i == 2 ? " and " : ", ");
		}
		return result.toString();
	}
	
	@Override
	public void onEnable() {
		extraItems.setItem(3, new ItemStack(Material.COMMAND));
		extraItems.setItem(4, new ItemStack(Material.COMMAND_CHAIN));
		extraItems.setItem(5, new ItemStack(Material.COMMAND_REPEATING));
		extraItems.setItem(11, new ItemStack(Material.MOB_SPAWNER));
		extraItems.setItem(13, new ItemStack(Material.COMMAND_MINECART));
		extraItems.setItem(15, new ItemStack(Material.BARRIER));
		extraItems.setItem(22, new ItemStack(Material.DRAGON_EGG));
		Bukkit.getPluginManager().registerEvents(this, this);
		PluginDescriptionFile d = getDescription();
		getLogger().info(
				"Enabled " + d.getName() + " v" + d.getVersion() + " by " + fromList(d.getAuthors()) + " successfully");
	}
	
	@Override
	public void onDisable() {
		try {
			for (HumanEntity p : extraItems.getViewers())
				p.closeInventory();
		} catch (ConcurrentModificationException e) {
			// ignore
		}
		PluginDescriptionFile d = getDescription();
		getLogger().info("Disabled " + d.getName() + " v" + d.getVersion() + " by " + fromList(d.getAuthors())
				+ " successfully");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("items"))
			if (sender instanceof Player) ((Player) sender).openInventory(extraItems);
		return true;
	}
	
	@EventHandler
	public void onInvEvent(InventoryClickEvent event) {
		Inventory i = event.getClickedInventory();
		if (i != null && i.equals(extraItems)) {
			event.setResult(Event.Result.DENY);
			event.setCancelled(true);
			if (i.getItem(event.getSlot()) != null) {
				event.getWhoClicked().getInventory().addItem(i.getItem(event.getSlot()));
			}
		}
	}
	
	@EventHandler
	public void onInv2(InventoryDragEvent event) {
		Inventory i = event.getInventory();
		if (i != null && i.equals(extraItems)) event.setCancelled(true);
	}
	
}
