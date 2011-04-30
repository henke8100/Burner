package burner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import burner.WhatToKill;
public class BurnerSuffocate implements CommandExecutor {
	private Burner plugin;

	public BurnerSuffocate(Burner plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] split) {
		
		Integer wtk=WhatToKill.whatToKill(sender, split, this.plugin);
		System.out.println("WTK: "+wtk);
		return true;
	}
	}