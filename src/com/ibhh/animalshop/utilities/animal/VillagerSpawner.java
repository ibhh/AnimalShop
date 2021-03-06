package com.ibhh.animalshop.utilities.animal;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import com.ibhh.animalshop.AnimalShop;
import com.ibhh.animalshop.utilities.logger.LoggerLevel;

public class VillagerSpawner implements AnimalSpawner
{

	@Override
	public boolean spawn(String args, Player p)
	{
		String[] aargs = args.split(" ");
		Villager villager = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
		villager.setRemoveWhenFarAway(false);
		for(Profession prof : Profession.values())
		{
			for(String string : aargs)
			{
				if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.villager.type." + prof.name()).toLowerCase()))
				{
					villager.setProfession(prof);
				}
			}
		}
		for(String string : aargs)
		{
			if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.villager.type.BABY").toLowerCase()))
			{
				villager.setBaby();
			}
			if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.villager.type.AGE_LOCK").toLowerCase()))
			{
				villager.setAgeLock(true);
			}

			if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.villager.type.CUSTOM_NAME").toLowerCase()))
			{
				String[] z = string.split(":");
				if(z.length == 2)
				{
					villager.setCustomName(z[1]);
				}
			}
			if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.villager.type.CUSTOM_NAME_INVISIBLE").toLowerCase()))
			{
				villager.setCustomNameVisible(false);
			}
		}
		AnimalShop.getLoggerUtility().log("Player " + p.getName() + "(" + p.getUniqueId() +") spawned a " + getIdetifier() + " with args: " + args, LoggerLevel.DEBUG);

		return true;
	}

	@Override
	public String getIdetifier()
	{
		return AnimalShop.getConfigHandler().getLanguageString("system", "animal.villager.name");
	}

	@Override
	public String getSystemIdentifier()
	{
		return "villager";
	}

}
