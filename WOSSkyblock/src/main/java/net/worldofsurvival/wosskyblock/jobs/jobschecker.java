package net.worldofsurvival.wosskyblock.items;

import java.util.ArrayList;


public final class MainItems {
	private jobChecker(Player player, String jobName) {
		File Job_playerJobDataBase = new File(Bukkit.getUpdateFolderFile().getParentFile().getParentFile(), "DataBase/Players/" + player.getPlayer().getName());
	    File Job_playerStatsFile;
	    File Job_playerJobsFile;
	    FileConfiguration StatsString;
	    FileConfiguration JobsString;
	    Job_playerStatsFile = new File(Job_playerJobDataBase, "/playerStats.yml");
	    Job_playerJobsFile = new File(Job_playerJobDataBase, "/playerJobs.yml");
	    StatsString = YamlConfiguration.loadConfiguration(Job_playerStatsFile);
	    JobsString = YamlConfiguration.loadConfiguration(Job_playerJobsFile);
	    if(JobName == "Woodcutter") {
	    	Integer WoodcutterLevel = JobsString.getInt("WoodcutterLevel");
	    	Integer WoodcutterNextLevel = WoodcutterLevel+1;
	    	Integer WoodcutterExp = JobsString.getInt("WoodcutterExp");
	    	Integer WoodcutterExpNeed = JobsString.getInt("WoodcutterExpNeed");
	    	Integer WoodcutterExpReward = JobsString.getInt("WoodcutterRewExp");
	    	Integer WoodcutterMoneyReward = JobsString.getInt("WoodcutterRewMoney");
	    	Integer PlayerMoney = StatsString.getInt("Money");
	    	Integer WoodcutterExpLeft = WoodcutterExpNeed - WoodcutterExp;
	    	Integer WoodcutterExpFinal = WoodcutterExpReward - WoodcutterExpLeft;
		    if(WoodcutterLevel == 0) {
		    	if(WoodcutterExp < WoodcutterExpNeed) {
		    		if(WoodcutterExpLeft > WoodcutterExpReward) {
		    			JobsString.set("WoodcutterExp", WoodcutterExp+1);
			    		StatsString.set("Money", PlayerMoney+1);
			    		HotbarMessagerClass.sendHotBarMessage(player.getPlayer(), "You got " + WoodcutterExpReward + " experience and " + WoodcutterMoneyReward + " money from " + JobName + " job!");
		    		} else if(WoodcutterExpLeft <= WoodcutterExpReward) {
			    		JobsString.set("WoodcutterLevel", WoodcutterNextLevel);
			    		JobsString.set("WoodcutterExp", WoodcutterExpFinal);
			    		JobsString.set("WoodcutterExpNeed", WoodcutterNextLevel*1000);
			    		JobsString.set("WoodcutterRewExp", WoodcutterLevel);
			    		JobsString.set("WoodcutterRewMoney", WoodcutterLevel);
				    	StatsString.set("Money", PlayerMoney+WoodcutterMoneyReward);
					    HotbarMessagerClass.sendHotBarMessage(player.getPlayer(), "Congratulations! You leveled up to level " + WoodcutterNextLevel + ".");
		    		}
				    JobsString.save(Job_playerJobsFile);
				    StatsString.save(Job_playerStatsFile);
		    	}
		    } else {
		    	if(WoodcutterExp < WoodcutterExpNeed) {
		    		if(WoodcutterExpLeft > WoodcutterExpReward) {
		    			JobsString.set("FarmerExp", WoodcutterExp+WoodcutterExpReward);
		    			StatsString.set("Money", PlayerMoney+WoodcutterMoneyReward);
				    	HotbarMessagerClass.sendHotBarMessage(player.getPlayer(), "You got " + WoodcutterExpReward + " experience and " + WoodcutterMoneyReward + " money from " + JobName + " job!");
		    		}
		    		else if(WoodcutterExpLeft <= WoodcutterExpReward) {
			    		JobsString.set("FarmerLevel", WoodcutterNextLevel);
				    	JobsString.set("FarmerExp", WoodcutterExpFinal);
			    		JobsString.set("FarmerExpNeed", (WoodcutterNextLevel+1)*1000);
			    		JobsString.set("FarmerRewExp", WoodcutterNextLevel);
			    		JobsString.set("FarmerRewMoney", WoodcutterNextLevel);
				    	StatsString.set("Money", PlayerMoney+WoodcutterMoneyReward);
					    HotbarMessagerClass.sendHotBarMessage(player.getPlayer(), "Congratulations! You leveled up to level " + WoodcutterNextLevel + ".");
		    		}
				    JobsString.save(Job_playerJobsFile);
				    StatsString.save(Job_playerStatsFile);
		    	}
		    }
	    }
	}
}