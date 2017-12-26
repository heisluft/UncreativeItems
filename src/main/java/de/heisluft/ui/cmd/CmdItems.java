package de.heisluft.ui.cmd;

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
		if (sender instanceof Player && ((Player) sender).getGameMode().equals(GameMode.CREATIVE))
			((Player) sender).openInventory(UncreativeItems.instance.extraItems);
		return true;
	}
}
