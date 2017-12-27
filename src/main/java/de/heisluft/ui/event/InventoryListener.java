package de.heisluft.ui.event;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.heisluft.ui.UncreativeItems;
import de.heisluft.ui.util.Utils;

/**
 * The Listener listening on changes to {@link UncreativeItems#EXTRA_ITEMS EXTRA_ITEMS} inventory
 */
public class InventoryListener implements Listener {
	
	private static final Map<Inventory, InventoryCallbackFunction> FUNCTIONS = new HashMap<>();
	
	/**
	 * creates the InventoryListener
	 */
	public InventoryListener() {
		addCallback(UncreativeItems.EXTRA_ITEMS, (whoClicked, inventory, slot) -> {
			ItemStack is = inventory.getItem(slot);
			if (is != null) {
				if (is.equals(UncreativeItems.instance.spawnerFactory)) whoClicked.openInventory(Utils.customSpawner());
				else whoClicked.getInventory().addItem(is);
			}
			return new SimplePair<>(Event.Result.DENY, false);
		});
	}
	
	/**
	 * Assigns a callback to the specified Inventory
	 * @param inventory the inventory to assign to
	 * @param function the function to assign
	 */
	public static void addCallback(Inventory inventory, InventoryCallbackFunction function) {
		FUNCTIONS.put(inventory, function);
	}
	
	@EventHandler
	public void onInvEvent(InventoryClickEvent event) {
		Inventory i = event.getClickedInventory();
		if (i == null) return;
		if (FUNCTIONS.containsKey(i)) {
			SimplePair<Event.Result, Boolean> result = FUNCTIONS.get(i).callback(event.getWhoClicked(), i,
					event.getSlot());
			event.setResult(result.getP1());
			event.setCancelled(!result.getP2());
		}
		if (event.getWhoClicked().getOpenInventory().getTopInventory().equals(UncreativeItems.EXTRA_ITEMS)
				&& event.getClick().isShiftClick()) {
			event.setResult(Event.Result.DENY);
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInv2(InventoryDragEvent event) {
		Inventory i = event.getInventory();
		if (i != null && i.equals(UncreativeItems.EXTRA_ITEMS)) event.setCancelled(true);
	}
}
