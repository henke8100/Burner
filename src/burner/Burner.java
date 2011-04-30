package burner;

import java.io.File;
import java.util.HashMap;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class Burner extends JavaPlugin {
	public HashMap<String, String> messages = new HashMap<String, String>();

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is disabled.");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		File configFile = new File("plugins/Burner/config.yml");
		Configuration config = new Configuration(configFile);
		config.load();
		int conf = config.getInt("Burner.Seconds", 1000);
		messages.put("Burner.Messages.Self", config.getString(
				"Burner.Messages.Self", "Setting you on fire.."));
		System.out.println("secs:" + conf + "self:"
				+ messages.get("Burner.Messages.Self"));
		config.save();
		getCommand("burn").setExecutor(new BurnerCommand(this));
		getCommand("suffocate").setExecutor(new BurnerSuffocate(this));
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is enabled!");
	}
}