package de.heisluft.ui.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.heisluft.ui.RDP;

public class CmdGiveURLSkull implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) return true;
		int amount = args.length >= 2 ? Integer.parseInt(args[1]) : 1;
		if (sender instanceof Player) ((Player) sender).getInventory().addItem(RDP.getSkull(args[0], amount));
		return true;
	}
}
