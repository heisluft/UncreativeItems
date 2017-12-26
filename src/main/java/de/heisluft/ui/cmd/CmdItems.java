package de.heisluft.ui.cmd;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.heisluft.ui.UncreativeItems;

/**
 * Deals with the /items command
 */
public class CmdItems extends CmdBase {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player) if (((Player) sender).getGameMode().equals(GameMode.CREATIVE))
				((Player) sender).openInventory(UncreativeItems.instance.extraItems);
			else System.out.println("Usage: /items <player>");
		} else if (args.length == 1) {
			Player player = Bukkit.getPlayer(args[0]);
			if (player != null) player.openInventory(UncreativeItems.instance.extraItems);
			else sendPlayerNotFoundMessage(sender, args[0]);
		} else if (sender instanceof Player) sender.sendMessage("\u00A7cUsage: " + cmd.getUsage() + "\u00A7r");
		else System.out.println("Usage: /items <player>");
		return true;
	}
	
}
