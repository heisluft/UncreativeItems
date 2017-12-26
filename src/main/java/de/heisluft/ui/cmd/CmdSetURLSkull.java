package de.heisluft.ui.cmd;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import de.heisluft.ui.util.OBCNMSUtils;
import de.heisluft.ui.util.Utils;

/**
 * Deals with the /seturlskull command
 */
public class CmdSetURLSkull implements CommandExecutor, TabCompleter {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length >= 4) {
			String url = args[3];
			World world = sender instanceof Player ? ((Player) sender).getWorld() : Bukkit.getWorld(args[4]);
			Block b = world.getBlockAt(Utils.parseLocation(args[0], args[1], args[2],
					sender instanceof Player ? (Player) sender : null, world));
			b.setType(Material.SKULL);
			((Skull) b.getState()).setSkullType(SkullType.PLAYER);
			OBCNMSUtils.setSkullWithNonPlayerProfile(url, true, b);
		}
		return false;
	}
}
