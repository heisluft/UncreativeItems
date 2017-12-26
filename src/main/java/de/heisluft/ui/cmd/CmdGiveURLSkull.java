package de.heisluft.ui.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.heisluft.ui.util.OBCNMSUtils;

/**
 * Deals with the /giveurlskull command
 */
public class CmdGiveURLSkull implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		switch (args.length) {
			case 1:
				if (sender instanceof Player)
					((Player) sender).getInventory().addItem(OBCNMSUtils.getSkull(args[0], 1));
				return true;
			case 2:
				if (sender instanceof Player)
					((Player) sender).getInventory().addItem(OBCNMSUtils.getSkull(args[0], Integer.parseInt(args[1])));
				else {
					Player p = Bukkit.getPlayer(args[0]);
					if (p != null) p.getInventory().addItem(OBCNMSUtils.getSkull(args[1], 1));
				}
				return true;
			case 3:
				Player p = Bukkit.getPlayer(args[0]);
				if (p != null) p.getInventory().addItem(OBCNMSUtils.getSkull(args[1], Integer.parseInt(args[2])));
				return true;
			default:
				return true;
		}
	}
}
