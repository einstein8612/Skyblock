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
	
	
	
	private ArrayList<String> colorizeList (List<String> list) {
		ArrayList<String> finalLore = new ArrayList<String>();
		for (String str : list) {
			finalLore.add(ChatColor.translateAlternateColorCodes('&', str));
		}
		return finalLore;
	}
	
}