package net.worldofsurvival.wosskyblock.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public final class MainItems {
	
	private ArrayList<String> clickToUse(){
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&l(Click to use me)"));
		return lore;
	}
	
	private ArrayList<String> clickToTeleport(){
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&l(Click to teleport)"));
		return lore;
	}
	
	private ArrayList<String> clickToEmpty(String string){
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&l(Click to " + string + ")"));
		return lore;
	}
	
	public ItemStack makeSkullItem(String name, Player player) {
		
		
		ItemMeta meta = null;
		meta.setLore(this.clickToEmpty("invite"));
		return null;
	}
	
	public ItemStack makeClickMeItem(Material mat, String name) {
		ItemStack itemstack = new ItemStack(mat);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack makeTeleportItem(Material mat, String name) {
		ItemStack itemstack = new ItemStack(mat);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		meta.setLore(clickToTeleport());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
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
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	//Menu items
	public ItemStack home() {
		ItemStack itemstack = new ItemStack(Material.RED_BED);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lHome"));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack warps() {
		ItemStack itemstack = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3&lWarps"));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	//Menu items end
	
	public ItemStack menu() {
		ItemStack itemstack = new ItemStack(Material.EMERALD);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&l&f&3&5&2&r&5&lSkyblock Menu"));
		meta.setLore(this.clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	/*
	private ArrayList<String> colorizeList (List<String> list) {
		ArrayList<String> finalLore = new ArrayList<String>();
		for (String str : list) {
			finalLore.add(ChatColor.translateAlternateColorCodes('&', str));
		}
		return finalLore;
	}*/
	
}