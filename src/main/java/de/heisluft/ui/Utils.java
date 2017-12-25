package de.heisluft.ui;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class Utils {
	
	public static Location resolveLocation(String xs, String ys, String zs, @Nullable Player player, World world) {
		if (player != null) {
			Location l = player.getLocation();
			int xo = 0;
			int yo = 0;
			int zo = 0;
			if (xs.startsWith("~") && !xs.substring(1).isEmpty()) xo = Integer.parseInt(xs.substring(1));
			if (ys.startsWith("~") && !ys.substring(1).isEmpty()) xo = Integer.parseInt(ys.substring(1));
			if (zs.startsWith("~") && !zs.substring(1).isEmpty()) xo = Integer.parseInt(zs.substring(1));
			l.add(xo, yo, zo);
			return l;
		}
		return new Location(world, Integer.parseInt(xs), Integer.parseInt(ys), Integer.parseInt(zs));
	}
	
	static Inventory customSpawner() {
		Inventory result = Bukkit.createInventory(null, 27, "\u00A7cThe Spawner Factory\u00A7r");
		ItemStack skullBack = RDP.getSkull(
				"http://textures.minecraft.net/texture/86fd37aaf22730e0dcae98dfca8b8e77771c6a18bb9efbf73387e4e2f1bdc",
				1);
		ItemMeta m = skullBack.getItemMeta();
		m.setDisplayName("Back");
		skullBack.setItemMeta(m);
		result.setItem(18, skullBack);
		return result;
	}
	
	static String fromList(List<String> list) {
		StringBuilder result = new StringBuilder();
		int s = list.size();
		for (int i = 0; i < s; i++) {
			result.append(list.get(i)).append(s - i == 1 ? "" : s - i == 2 ? " and " : ", ");
		}
		return result.toString();
	}
}
