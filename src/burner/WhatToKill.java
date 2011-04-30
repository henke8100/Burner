package burner;

import java.util.List;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class WhatToKill {
	private static Entity targetEntity;

	/*
	 * Types: 0 = Yourself 1 = No permission 2 = Other player 3 = Not found 4 =
	 * Creature
	 */
	public static Integer whatToKill(CommandSender sender, String[] split,
			Burner plugin) {
		Player player = (Player) sender;
		if (split.length == 0) {
			targetEntity = (Entity) sender;
			return 0; // Yourself
		}
		if (!player.isOp())
			return 1; // No permission
		Player targetPlayer = plugin.getServer().getPlayer(split[0]);
		int id = 0;
		if (targetPlayer != null) {
			targetEntity = targetPlayer;
			return 2; // Other player
		}
		try {
			id = new Integer(split[0]);
		} catch (NumberFormatException nxe) {
			return 3; // Not found
		}
		World w = player.getWorld();
		List<LivingEntity> mobs = w.getLivingEntities();
		Entity mtarget = null;
		Boolean foundmob = false;
		foundmob = false;
		for (LivingEntity i : mobs) {
			if (i.getEntityId() == id) {
				mtarget = i;
				foundmob = true;
				break;
			}
		}
		if (foundmob) {
			targetEntity = mtarget;
			return 4; // Creature
		}
		return 3; // Not found
	}

	public static Entity whatToKillEntity() {
		return targetEntity;
	}
}
