package de.heisluft.ui.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.Integer.parseInt;

/**
 * This class contains all Non-NMS utility methods
 */
public final class Utils {

	private Utils() {}

	/**
	 * Parse a Location from the given input strings. If a Player instance is passed, this Method will be able to
	 * resolve relative (~) coordinates.
	 *
	 * @param xSource
	 * 		The x-coordinate source string
	 * @param ySource
	 * 		The y-coordinate source string
	 * @param zSource
	 * 		The z-coordinate source string
	 * @param player
	 * 		<i>Optional:</i> If given, you will be able to parse relative (~)
	 * 		coordinates
	 * @param world
	 * 		<i>Optional:</i> Not needed, if player parameter is set.
	 *
	 * @return the parsed Location
	 */
	public static Location parseLocation(String xSource, String ySource, String zSource, Player player, World world) {
		return player == null ? new Location(world, parseInt(xSource), parseInt(ySource),
				parseInt(zSource)) : ((Function<Location, Location>) l -> {
			BiFunction<String, Integer, Integer> parse = ((s, a) -> {
				if(s.startsWith("~")) {
					if(!s.substring(1).isEmpty()) return a + parseInt(s.substring(1));
					else return parseInt(s);
				} else return 0;
			});
			return new Location(l.getWorld(), parse.apply(xSource, l.getBlockX()), parse.apply(ySource, l.getBlockY()),
					parse.apply(zSource, l.getBlockZ()));
		}).apply(player.getLocation());
	}

	/**
	 * Joins all entries from a given list together in a human readable string
	 *
	 * @param list
	 * 		The {@link List} to join
	 *
	 * @return a human readable string of all entries, joined together
	 */
	public static String fromList(List<String> list) {
		StringBuilder result = new StringBuilder();
		int s = list.size();
		for(int i = 0; i < s; i++) {
			if(list.get(i) != null) result.append(list.get(i)).append(s - i == 1 ? "" : s - i == 2 ? " and " : ", ");
		}
		return result.toString();
	}
}
