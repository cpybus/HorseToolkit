package me.chris.HorseToolkit;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class CommandHBack implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
    {
		if (!(sender instanceof Player))
		{
			return true;
		}

		Player p = (Player) sender;
		
		if(!Variables.perms.has(p, "HorseToolkit.back"))
		{
			p.sendMessage("§a[HorseToolkit] §4You do not have permission.");
			return true;
		}
		
		if(!Variables.previousLocations.containsKey(p))
		{
			p.sendMessage("§a[HorseToolkit] §4You did not hTP anywhere.");
			return true;
		}
		
		Location previousLocation = Variables.previousLocations.get(p);
		Entity e = p.getVehicle();
		Horse h;

		if (e == null)
		{
			p.sendMessage("§a[HorseToolkit] §4You are no longer on your horse");
			return true;
		}

		if (!(e instanceof Horse))
		{
			p.sendMessage("§a[HorseToolkit] §4You are no longer on your horse");
			return true;
		}

		h = (Horse) e;
		
		Variables.previousLocations.put(p, p.getLocation());
		
		p.sendMessage("§7§oTeleporting...");
		h.eject();
		p.eject();
		h.teleport(previousLocation);
		p.teleport(previousLocation);
		h.setPassenger(p);
		
		
		
		return true;
		
    }
	
	// private static final String NOPERMS =
	// "§a[SimpleChat] §4You do not have perms to access the following command:";
	// private static final String INVALIDCOMMAND =
	// "§a[SimpleChat] §4The following command is invalid:";

	
}
