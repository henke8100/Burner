package burner;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
public class BurnerCommand implements CommandExecutor {

	private final Burner plugin;

	public BurnerCommand(Burner plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] split) {
		if (!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player) sender;
		String prefix = "§9[§4B§cu§4r§cn§4e§cr§9] §f";
		if (split.length == 0) {
			if (player.getFireTicks() < 0) {
				player.setFireTicks(1000);
				player.sendMessage(prefix + "Setting you on fire!");
			} else
				player.sendMessage(prefix
						+ "Already burning, extended your burning time!");
			return true;
		} else {
			String notfound = prefix
					+ "Could not find target player or animal/monster ("
					+ split[0] + ").";
			if (!player.isOp()) {
				player.sendMessage(prefix + "Non-OPs may only burn themselves.");
				return true;
			}
			// Player is OP, try setting the target on fire!
			Player targetp = plugin.getServer().getPlayer(split[0]);

			if (targetp != null) {
				targetp.setFireTicks(1000);
				player.sendMessage(prefix + "Setting "
						+ targetp.getDisplayName() + " on fire!");
				targetp.sendMessage(prefix + player.getDisplayName()
						+ " set you on fire.");
				return true;
			}
			// The target is not a player.
			int id = 0;
			try {
				id = new Integer(split[0]);
			} catch (NumberFormatException nxe) {
				player.sendMessage(notfound);
				return true;
			}
			World w = player.getWorld();
			List<LivingEntity> mobs = w.getLivingEntities();
			Entity mtarget = null;
			Boolean foundmob = false;
			for (LivingEntity i : mobs) {
				if (i.getEntityId() == id) {
					mtarget = i;
					foundmob = true;
					break;
				}
			}
			if (foundmob) {
				mtarget.setFireTicks(1000);
				player.sendMessage(prefix + "Setting Entity with id "
						+ mtarget.getEntityId() + " on fire!");
				return true;
			}
			player.sendMessage(notfound);
			return true;
		}
	}
}
