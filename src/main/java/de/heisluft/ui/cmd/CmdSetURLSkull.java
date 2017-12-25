package de.heisluft.ui.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.heisluft.ui.RDP;
import de.heisluft.ui.Utils;

public class CmdSetURLSkull implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String var3, String[] args) {
		if (args.length >= 4) {
			String url = args[3];
			World world = sender instanceof Player ? ((Player) sender).getWorld() : Bukkit.getWorld(args[4]);
			Block b = world.getBlockAt(Utils.resolveLocation(args[0], args[1], args[2],
					sender instanceof Player ? (Player) sender : null, world));
			b.setType(Material.SKULL);
			((Skull) b.getState()).setSkullType(SkullType.PLAYER);
			RDP.setSkullWithNonPlayerProfile(url, true, b);
		}
		return false;
	}
}
