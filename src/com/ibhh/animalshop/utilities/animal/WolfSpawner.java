package com.ibhh.animalshop.utilities.animal;

import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

import com.ibhh.animalshop.AnimalShop;
import com.ibhh.animalshop.utilities.logger.LoggerLevel;

public class WolfSpawner implements AnimalSpawner
{

	@Override
	public boolean spawn(String args, Player p)
	{
		String[] aargs = args.split(" ");
		Wolf wolf = (Wolf) p.getWorld().spawnEntity(p.getLocation(), EntityType.WOLF);

		for(DyeColor color : DyeColor.values())
		{
			for(String string : aargs)
			{
				if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.wolf.color." + color.name()).toLowerCase()))
				{
					wolf.setCollarColor(color);
				}
			}
		}
		for(String string : aargs)
		{
			if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.wolf.type.TAMED").toLowerCase()))
			{
				wolf.setTamed(true);
				wolf.setOwner(p);
			}
			if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.wolf.type.ANGRY").toLowerCase()))
			{
				AnimalShop.getLoggerUtility().log("Set angry", LoggerLevel.DEBUG);
				wolf.setAngry(true);
			}
			if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.wolf.type.BABY").toLowerCase()))
			{
				wolf.setBaby();
			}
			if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.wolf.type.AGE_LOCK").toLowerCase()))
			{
				wolf.setAgeLock(true);
			}
			if(string.toLowerCase().contains(AnimalShop.getConfigHandler().getLanguageString("system", "animal.wolf.type.CUSTOM_NAME").toLowerCase()))
			{
				String[] z = string.split(":");
				if(z.length == 2)
				{
					wolf.setCustomName(z[1]);
				}
			}
			if(string.toLowerCase().equalsIgnoreCase(AnimalShop.getConfigHandler().getLanguageString("system", "animal.wolf.type.CUSTOM_NAME_INVISIBLE").toLowerCase()))
			{
				wolf.setCustomNameVisible(false);
			}
		}
		wolf.setHealth(8);
		AnimalShop.getLoggerUtility().log("Player " + p.getName() + "(" + p.getUniqueId() +") spawned a " + getIdetifier() + " with args: " + args, LoggerLevel.DEBUG);

		return true;
	}

	@Override
	public String getIdetifier()
	{
		return AnimalShop.getConfigHandler().getLanguageString("system", "animal.wolf.name");
	}

	@Override
	public String getSystemIdentifier()
	{
		return "wolf";
	}

}
