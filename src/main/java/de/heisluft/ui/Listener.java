package de.heisluft.ui;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Listener implements org.bukkit.event.Listener {
	
	@EventHandler
	public void onInvEvent(InventoryClickEvent event) {
		Inventory i = event.getClickedInventory();
		if (i == null) return;
		if (i.equals(UncreativeItems.instance.extraItems)) {
			event.setResult(Event.Result.DENY);
			event.setCancelled(true);
			ItemStack is = i.getItem(event.getSlot());
			if (is != null) {
				if (is.equals(UncreativeItems.instance.spawnerFactory))
					event.getWhoClicked().openInventory(Utils.customSpawner());
				else event.getWhoClicked().getInventory().addItem(is);
			}
		} else if (event.getWhoClicked().getOpenInventory().getTopInventory()
				.equals(UncreativeItems.instance.extraItems) && event.getClick().isShiftClick()) {
			event.setResult(Event.Result.DENY);
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInv2(InventoryDragEvent event) {
		Inventory i = event.getInventory();
		if (i != null && i.equals(UncreativeItems.instance.extraItems)) event.setCancelled(true);
	}
}
