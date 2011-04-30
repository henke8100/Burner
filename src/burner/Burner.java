package burner;

import java.io.File;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class Burner extends JavaPlugin {
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is disabled.");
	}

	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		File dir = new File("plugins"+File.separator+pdfFile.getName()+File.separator);
		dir.mkdir();
		File file=new File("plugins"+File.separator+pdfFile.getName()+File.separator,"config.yml");
		Configuration config=new Configuration(file);
		if(config.getProperty("Burn.seconds") != null){
			System.out.println("Found property 'test");
			System.out.println(config.getString("Burn.seconds"));
		}
		else{
			System.out.println("Did not find property 'test");
			config.setProperty("Burn.seconds", 1000);
		}
		config.save();
		getCommand("burn").setExecutor(new BurnerCommand(this));
		getCommand("suffocate").setExecutor(new BurnerSuffocate(this));
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is enabled!");
	}
}