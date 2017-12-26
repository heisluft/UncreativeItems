package de.heisluft.ui.event;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

/**
 * Defines a callback function executed whenever the Inventory changes.
 * 
 * @see InventoryListener#addCallback(Inventory, InventoryCallbackFunction)
 */
@FunctionalInterface
public interface InventoryCallbackFunction {
	
	/**
	 * Executed whenever the Inventory is clicked
	 * 
	 * @param whoClicked
	 *            the clicking humanEntity
	 * @param inventory
	 *            the Inventory
	 * @param slot
	 *            the clicked slot
	 * @return the event result and whether the event should continue
	 */
	SimplePair<Event.Result, Boolean> callback(HumanEntity whoClicked, Inventory inventory, int slot);
}
