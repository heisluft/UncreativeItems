package de.heisluft.ui;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class RDP implements Listener {
	
	private static final Random RANDOM = new Random();
	private static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Method getWorldHandle;
	private static Method getWorldTileEntity;
	private static Method setGameProfile;
	private static Field gameProfileField;
	private static Constructor<?> blockPositionConstructor;
	
	public static ItemStack getSkull(String skinURL, int amount) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		try {
			gameProfileField.setAccessible(true);
			gameProfileField.set(meta, getNonPlayerProfile(skinURL, false));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		skull.setItemMeta(meta);
		return skull;
	}
	
	// Method
	@SuppressWarnings("deprecation")
	public static void setSkullWithNonPlayerProfile(String skinURL, boolean randomName, Block skull) {
		if (skull.getType() != Material.SKULL) throw new IllegalArgumentException("Block must be a skull.");
		Skull s = (Skull) skull.getState();
		try {
			setSkullProfile(s, getNonPlayerProfile(skinURL, randomName));
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		skull.getWorld().refreshChunk(skull.getChunk().getX(), skull.getChunk().getZ());
	}
	
	// Method
	private static void setSkullProfile(Skull skull, GameProfile someGameProfile) throws ReflectiveOperationException {
		Object world = getWorldHandle.invoke(skull.getWorld());
		Object bp = blockPositionConstructor.newInstance(skull.getX(), skull.getY(), skull.getZ());
		Object tileSkull = getWorldTileEntity.invoke(world, bp);
		setGameProfile.invoke(tileSkull, someGameProfile);
	}
	
	// Method
	private static GameProfile getNonPlayerProfile(String skinURL, boolean randomName) {
		GameProfile newSkinProfile = new GameProfile(UUID.randomUUID(),
				randomName ? getRandomString(RANDOM.nextInt(9)) : null);
		newSkinProfile.getProperties().put("textures",
				new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + skinURL + "\"}}}")));
		return newSkinProfile;
	}
	
	// Example
	private static String getRandomString(int length) {
		length += 8;
		StringBuilder b = new StringBuilder(length);
		for (int j = 0; j < length; j++)
			b.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
		return b.toString();
	}
	
	// Refletion
	private static Class<?> getMCClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
		String className = "net.minecraft.server." + version + name;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
	// Refletion
	private static Class<?> getCraftClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
		String className = "org.bukkit.craftbukkit." + version + name;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
	public void onEnable() {
		if (getWorldHandle == null || getWorldTileEntity == null || setGameProfile == null) {
			try {
				gameProfileField = getCraftClass("inventory.CraftMetaSkull").getDeclaredField("profile");
				Class<?> bpc = getMCClass("BlockPosition");
				getWorldHandle = getCraftClass("CraftWorld").getMethod("getHandle");
				getWorldTileEntity = getMCClass("WorldServer").getMethod("getTileEntity", bpc);
				setGameProfile = getMCClass("TileEntitySkull").getMethod("setGameProfile", GameProfile.class);
				blockPositionConstructor = bpc.getConstructor(int.class, int.class, int.class);
			} catch (ReflectiveOperationException e) {
				e.printStackTrace();
			}
		}
	}
}
