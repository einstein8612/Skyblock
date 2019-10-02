package net.worldofsurvival.wosskyblock.items;

import java.util.ArrayList;

import net.md_5.bungee.api.ChatColor;

public final class WarpItems {
	
	private ArrayList<String> clickToUse(){
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&lStatistics:"));
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&lLevel: " ));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&lExperience"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&lMoney"));
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&lRewards:"));
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&lMoney:"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7&lExperience"));
		return lore;
	}
	
	public ItemStack fighterjob() {
		ItemStack itemstack = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lFighter Job"));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack crafterjob() {
		ItemStack itemstack = new ItemStack(Material.CRAFTING_TABLE);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lCrafterJob"));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack farmerjob() {
		ItemStack itemstack = new ItemStack(Material.WOODEN_HOE);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lFarmer Job"));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack minerjob() {
		ItemStack itemstack = new ItemStack(Material.WOODEN_PICKAXE);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lMiner Job"));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack fisherjob() {
		ItemStack itemstack = new ItemStack(Material.SPRUCE_SAPLING);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&d&lFisher Job"));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack woodcutterjob() {
		ItemStack itemstack = new ItemStack(Material.JUNGLE_SAPLING);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lWoodCutter Job"));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public ItemStack farmerjob2() {
		ItemStack itemstack = new ItemStack(Material.ACACIA_SAPLING);
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lAnimal Farmer Job"));
		meta.setLore(clickToUse());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
}
