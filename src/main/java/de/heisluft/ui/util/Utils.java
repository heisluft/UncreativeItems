package de.heisluft.ui.util;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.heisluft.ui.event.InventoryListener;
import de.heisluft.ui.event.SimplePair;

/**
 * This class contains all Non-NMS utility methods
 */
public final class Utils {
	
	private Utils() {}
	
	/**
	 * Parses a Location from the given input strings. If a Player instance is
	 * passed, it will be able to resolve relative (~) coordinates.
	 *
	 * @param xSource
	 *            The x-coordinate source string
	 * @param ySource
	 *            The y-coordinate source string
	 * @param zSource
	 *            The z-coordinate source string
	 * @param player
	 *            <i>Optional:</i> If given, you will be able to parse relative (~)
	 *            coordinates
	 * @param world
	 *            <i>Optional:</i> Not needed, if player parameter is set.
	 * @return the parsed Location
	 */
	public static Location parseLocation(String xSource, String ySource, String zSource, Player player, World world) {
		if (player != null) {
			Location l = player.getLocation();
			int xo = 0;
			int yo = 0;
			int zo = 0;
			if (xSource.startsWith("~")) {
				if (!xSource.substring(1).isEmpty()) xo = Integer.parseInt(xSource.substring(1));
			} else l.setX(Integer.parseInt(xSource));
			if (ySource.startsWith("~")) {
				if (!ySource.substring(1).isEmpty()) xo = Integer.parseInt(ySource.substring(1));
			} else l.setY(Integer.parseInt(ySource));
			if (zSource.startsWith("~")) {
				if (!zSource.substring(1).isEmpty()) xo = Integer.parseInt(zSource.substring(1));
			} else l.setZ(Integer.parseInt(zSource));
			l.add(xo, yo, zo);
			return l;
		}
		return new Location(world, Integer.parseInt(xSource), Integer.parseInt(ySource), Integer.parseInt(zSource));
	}
	
	/**
	 * Creates the spawner Inventory and adds its callbacks
	 * 
	 * @return the Inventory
	 */
	public static Inventory customSpawner() {
		Inventory result = Bukkit.createInventory(null, 27, "\u00A7cThe Spawner Factory\u00A7r");
		InventoryListener.addCallback(result,
				(whoClicked, inventory, slot) -> new SimplePair<>(Event.Result.DEFAULT, true));
		return result;
	}
	
	/**
	 * Joins all entries from a given list together in a human readable string
	 * 
	 * @param list
	 *            The {@link List} to join
	 * @return a human readable string of all entries, joined together
	 */
	public static String fromList(List<String> list) {
		StringBuilder result = new StringBuilder();
		int s = list.size();
		for (int i = 0; i < s; i++) {
			if (list.get(i) != null) result.append(list.get(i)).append(s - i == 1 ? "" : s - i == 2 ? " and " : ", ");
		}
		return result.toString();
	}
}
