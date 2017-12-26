package de.heisluft.ui.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.heisluft.ui.util.OBCNMSUtils;
import de.heisluft.ui.util.Utils;

/**
 * Deals with the /seturlskull command
 */
public class CmdSetURLSkull extends CmdBase {
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> result = new ArrayList<>();
		if (args.length <= 3) if (sender instanceof Player) result.add("~");
		else result.add("0");
		if (args.length == 4) {
			result.add("https://");
			result.add("http://");
		}
		if (args.length == 5) for (World w : Bukkit.getServer().getWorlds())
			if (w.getName().startsWith(args[args.length - 1])) result.add(w.getName());
		return result;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 4 && sender instanceof Player) {
			Block b = ((Player) sender).getWorld()
					.getBlockAt(Utils.parseLocation(args[0], args[1], args[2], (Player) sender, null));
			b.setType(Material.SKULL);
			((Skull) b.getState()).setSkullType(SkullType.PLAYER);
			OBCNMSUtils.setSkullWithNonPlayerProfile(args[3], true, b);
		}
		if (args.length == 5) {
			World world = Bukkit.getWorld(args[4]);
			Block b = world.getBlockAt(Utils.parseLocation(args[0], args[1], args[2],
					sender instanceof Player ? (Player) sender : null, world));
			b.setType(Material.SKULL);
			((Skull) b.getState()).setSkullType(SkullType.PLAYER);
			OBCNMSUtils.setSkullWithNonPlayerProfile(args[3], true, b);
		} else if (sender instanceof Player) sender.sendMessage("\u00A7cUsage: " + cmd.getUsage() + "\u00A7r");
		else System.out.println("Usage: " + cmd.getUsage().replace("[world]", "<world>"));
		return true;
	}
}
