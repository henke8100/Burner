package burner;

import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import burner.WhatToKill;

public class BurnerSuffocate implements CommandExecutor {
	private Burner plugin;

	public BurnerSuffocate(Burner plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] split) {

		Integer wtk = WhatToKill.whatToKill(sender, split, this.plugin);
		if (wtk == 0) {
			Player target = (Player) WhatToKill.whatToKillEntity();
			target.sendMessage("Suffocating yourself!");
			suffocate(target);
		} else if (wtk == 1)
			sender.sendMessage("Non-OPs may only kill themselves.");
		else if (wtk == 2) {
			Player target = (Player) WhatToKill.whatToKillEntity();
			Player senderAsPlayer = (Player) sender;
			sender.sendMessage("Suffocating " + target.getDisplayName() + "!");
			target.sendMessage(senderAsPlayer.getDisplayName()
					+ " is suffocating you!");
		} else if (wtk == 3) {
			sender.sendMessage("Target not found.");
		} else if (wtk == 4) {
			sender.sendMessage("Burning the mob!");
		}
		return true;
	}

	public void suffocate(Entity ent) {
		ent.getLocation().getBlock().setType(Material.SAND);
		ent.getLocation().getBlock().getFace(BlockFace.UP)
				.setType(Material.SAND);
	}
}