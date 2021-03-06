package de.heisluft.ui.cmd;

import java.util.ArrayList;
import java.util.List;

import de.heisluft.ui.UncreativeItems;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

/**
 * A CommandExecutor implementing class containing some commonly used methods
 */
public abstract class CmdBase implements CommandExecutor, TabCompleter {

	public CmdBase(String cmdName) {
		Bukkit.getPluginCommand(cmdName).setTabCompleter(this);
		Bukkit.getPluginCommand(cmdName).setExecutor(this);
	}

	/**
	 * Sends the minecraft-style player not found message <br>
	 * Example:<br>
	 * <code>Player player = Bukkit.getPlayer(yourNameFromArgs);<br>
	 * if(player == null) sendPlayerNotFoundMessage(sender, yourNameFromArgs);<br>
	 * else doYourStuff();
	 * </code>
	 * 
	 * @param sender
	 *            the sender to send the message to
	 * @param name
	 *            the name of the missing player
	 */
	protected void sendPlayerNotFoundMessage(CommandSender sender, String name) {
		if (sender instanceof Player) sender.sendMessage("\u00A7cPlayer '" + name + "' cannot be found\u00A7r");
		else System.out.println("Player '" + name + "' cannot be found");
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		return new ArrayList<>();
	}
}
