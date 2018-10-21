package de.heisluft.ui.event;

import de.heisluft.ui.UncreativeItems;
import de.heisluft.ui.util.Utils;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * The Listener listening on changes to the {@link UncreativeItems#EXTRA_ITEMS EXTRA_ITEMS} inventory
 */
public class InventoryListener implements Listener {

	private static final Map<Inventory, InventoryCallbackFunction> FUNCTIONS = new HashMap<>();

	/**
	 * creates the InventoryListener
	 */
	public InventoryListener() {
		addCallback(UncreativeItems.EXTRA_ITEMS, (whoClicked, inventory, slot) -> {
			ItemStack is = inventory.getItem(slot);
			if(is != null) whoClicked.getInventory().addItem(is);
			return Event.Result.DENY;
		});
	}

	/**
	 * Assigns a callback to the specified Inventory
	 *
	 * @param inventory
	 * 		the inventory to assign to
	 * @param function
	 * 		the function to assign
	 */
	public static void addCallback(Inventory inventory, InventoryCallbackFunction function) {
		FUNCTIONS.put(inventory, function);
	}

	@EventHandler
	public void onInvEvent(InventoryClickEvent event) {
		Inventory i = event.getInventory();
		if(i == null) return;
		if(FUNCTIONS.containsKey(i))
			event.setResult(FUNCTIONS.get(i).callback(event.getWhoClicked(), i, event.getSlot()));
		if(i.equals(UncreativeItems.EXTRA_ITEMS) && event.getClick().isShiftClick()) event.setResult(Event.Result.DENY);
	}

	@EventHandler
	public void onInv2(InventoryDragEvent event) {
		event.setCancelled(UncreativeItems.EXTRA_ITEMS.equals(event.getInventory()));
	}
}
