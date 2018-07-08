package de.heisluft.ui;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import de.heisluft.lang.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import de.heisluft.ui.cmd.CmdGiveURLSkull;
import de.heisluft.ui.cmd.CmdItems;
import de.heisluft.ui.cmd.CmdSetURLSkull;
import de.heisluft.ui.event.InventoryListener;
import de.heisluft.ui.util.Utils;

/**
 * The main class for the UncreativeItems plugin
 */
@SuppressWarnings("unused")
public class UncreativeItems extends JavaPlugin {
	/**
	 * The singleton plugin instance
	 */
	public static UncreativeItems instance;
	/**
	 * The Inventory it's all about
	 */
	public static Inventory EXTRA_ITEMS;
	public final ItemStack spawnerFactory = new ItemStack(Material.MOB_SPAWNER);
	
	@Override
	public void onLoad() {
		instance = this;
	}
	
	private void initCmds() {
		Bukkit.getPluginCommand("items").setExecutor(new CmdItems());
		CmdSetURLSkull s = new CmdSetURLSkull();
		CmdGiveURLSkull g = new CmdGiveURLSkull();
		PluginCommand sc = Bukkit.getPluginCommand("seturlskull");
		PluginCommand gc = Bukkit.getPluginCommand("giveurlskull");
		sc.setExecutor(s);
		sc.setTabCompleter(s);
		gc.setExecutor(g);
		gc.setTabCompleter(g);
	}
	
	@Override
	public void onEnable() {
		LanguageManager.INSTANCE.registerPlugin(this);
		EXTRA_ITEMS = Bukkit.getServer().createInventory(null, 27, "\u00a71UncreativeItems\u00a7r");
		EXTRA_ITEMS.setItem(3, new ItemStack(Material.COMMAND));
		EXTRA_ITEMS.setItem(4, new ItemStack(Material.COMMAND_CHAIN));
		EXTRA_ITEMS.setItem(5, new ItemStack(Material.COMMAND_REPEATING));
		ItemMeta im = spawnerFactory.getItemMeta();
		im.setDisplayName("\u00A7cSpawner Config\u00A7r");
		List<String> lore = new ArrayList<>();
		lore.add("\u00A77Click here to get to the SpawnerCustomisationFactory\u00A7r");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		spawnerFactory.setItemMeta(im);
		EXTRA_ITEMS.setItem(11, spawnerFactory);
		EXTRA_ITEMS.setItem(13, new ItemStack(Material.COMMAND_MINECART));
		EXTRA_ITEMS.setItem(15, new ItemStack(Material.BARRIER));
		EXTRA_ITEMS.setItem(22, new ItemStack(Material.DRAGON_EGG));
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		initCmds();
		PluginDescriptionFile d = getDescription();
		getLogger().info("Enabled " + d.getName() + " v" + d.getVersion() + " by " + Utils.fromList(d.getAuthors())
				+ " successfully");
	}
	
	@Override
	public void onDisable() {
		try {
			for (HumanEntity p : EXTRA_ITEMS.getViewers())
				p.closeInventory();
		} catch (ConcurrentModificationException e) {
			// ignore
		}
		PluginDescriptionFile d = getDescription();
		getLogger().info("Disabled " + d.getName() + " v" + d.getVersion() + " by " + Utils.fromList(d.getAuthors())
				+ " successfully");
	}
	
}
