package de.heisluft.ui.cmd;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.heisluft.ui.UncreativeItems;

/**
 * Deals with the /items command
 */
public class CmdItems implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player && ((Player) sender).getGameMode().equals(GameMode.CREATIVE))
				((Player) sender).openInventory(UncreativeItems.instance.extraItems);
		} else {
			Player player = Bukkit.getPlayer(args[0]);
			if (player != null) player.openInventory(UncreativeItems.instance.extraItems);
		}
		return true;
	}
	
}
