package net.worldofsurvival.wosskyblock.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public final class MainItems {
	
	public ItemStack blackPane() {
		ItemStack itemstack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(" ");
		itemstack.setItemMeta(meta);
		return itemstack;
	}

	public ItemStack back() {
		ItemStack itemstack = new ItemStack(Material.ARROW);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lBack"));
		meta.setLore(this.colorizeList(Arrays.asList("test")));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack home() {
		ItemStack itemstack = new ItemStack(Material.RED_BED);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lHome"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack close() {
		ItemStack itemstack = new ItemStack(Material.BARRIER);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lClose Menu"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_oak() {
		ItemStack itemstack = new ItemStack(Material.OAK_SAPLING);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Oak Forest"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_birch() {
		ItemStack itemstack = new ItemStack(Material.BIRCH_SAPLING);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Oak Forest"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_darkoak() {
		ItemStack itemstack = new ItemStack(Material.DARK_OAK_SAPLING);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Dark Oak Forest"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_acacia() {
		ItemStack itemstack = new ItemStack(Material.ACACIA_SAPLING);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Acacia Forest"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_jungle() {
		ItemStack itemstack = new ItemStack(Material.JUNGLE_SAPLING);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Jungle Forest"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_spruce() {
		ItemStack itemstack = new ItemStack(Material.SPRUCE_SAPLING);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Spruce Forest"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	private ArrayList<String> colorizeList (List<String> list) {
		ArrayList<String> finalLore = new ArrayList<String>();
		for (String str : list) {
			finalLore.add(ChatColor.translateAlternateColorCodes('&', str));
		}
		return finalLore;
	}
	
	public ItemStack tp_wheat() {
		ItemStack itemstack = new ItemStack(Material.WHEAT);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Wheat Farm"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_carrot() {
		ItemStack itemstack = new ItemStack(Material.CARROT);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Carrot Farm"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_potato() {
		ItemStack itemstack = new ItemStack(Material.POTATO);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Potato Farm"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_melon() {
		ItemStack itemstack = new ItemStack(Material.MELON);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Melon Farm"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_pumpkin() {
		ItemStack itemstack = new ItemStack(Material.PUMPKIN);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Pumpkin Farm"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_sugarcane() {
		ItemStack itemstack = new ItemStack(Material.SUGAR_CANE);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to SugarCane Farm"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack tp_fishing() {
		ItemStack itemstack = new ItemStack(Material.WATER);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Fishing Area"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}

	public ItemStack tp_mobarena() {
		ItemStack itemstack = new ItemStack(Material.ZOMBIE_HEAD);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lWarp to Mob Arena"));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
}