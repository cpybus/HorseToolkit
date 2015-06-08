package me.chris.HorseToolkit;

import java.util.logging.Level;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class HorseToolkitMain extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new Variables(this);

		if (!setupPermissions())
		{
			Variables.log.log(Level.SEVERE, "[HorseToolkit] No Permission found! Disabling plugin!");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		getServer().getPluginManager().registerEvents(new HorseToolkitListener(), this);
		
		CommandWelcome commandWelcome = new CommandWelcome();
		CommandHBack commandHBack = new CommandHBack();
		CommandHTP commandHTP = new CommandHTP();
		CommandHAccept commandHAccept = new CommandHAccept();
		CommandHStats commandHStats = new CommandHStats();
		CommandHSetOwner commandHSetOwner = new CommandHSetOwner();
		
		getCommand("horsetoolkit").setExecutor(commandWelcome);
		getCommand("htp").setExecutor(commandHTP);
		getCommand("hback").setExecutor(commandHBack);
		getCommand("haccept").setExecutor(commandHAccept);
		getCommand("hstats").setExecutor(commandHStats);
		getCommand("hsetowner").setExecutor(commandHSetOwner);

		Variables.log.log(Level.INFO, "[HorseToolkit] Version 1.0");
		Variables.log.log(Level.INFO, "[HorseToolkit] Started successfully.");

	}

	@Override
	public void onDisable()
	{
		Variables.log.log(Level.INFO, "[HorseToolkit] Stopped.");
	}

	private Boolean setupPermissions()
	{
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(
		        net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null)
		{
			Variables.perms = permissionProvider.getProvider();
		}
		return (Variables.perms != null);
	}
}
