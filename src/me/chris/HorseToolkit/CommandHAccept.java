package me.chris.HorseToolkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class CommandHAccept implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
	{
		if (!(sender instanceof Player))
		{
			return true;
		}

		Player matchedPlayer = (Player) sender;
		
		if(!Variables.perms.has(matchedPlayer, "HorseToolkit.accept"))
		{
			matchedPlayer.sendMessage("§a[HorseToolkit] §4You do not have permission.");
			return true;
		}
		
		if(!Variables.awaitingApproval.containsKey(matchedPlayer))
		{
			matchedPlayer.sendMessage("§a[HorseToolkit] §4No one sent you a request.");
			return true;
		}
		
		Player p = Variables.awaitingApproval.get(matchedPlayer);
		
		Entity e = p.getVehicle();
		Horse h;

		if (e == null)
		{
			matchedPlayer.sendMessage("§a[HorseToolkit] §c" + p.getDisplayName() + " §4is no longer on his/her horse. Request cancelled.");
			p.sendMessage("§a[HorseToolkit] §c" + matchedPlayer.getDisplayName() + " §4accepted TP, but you were no longer on your horse.");
			return true;
		}

		if (!(e instanceof Horse))
		{
			matchedPlayer.sendMessage("§a[HorseToolkit] §c" + p.getDisplayName() + " §4is no longer on his/her horse. Request cancelled.");
			p.sendMessage("§a[HorseToolkit] §c" + matchedPlayer.getDisplayName() + " §4accepted TP, but you were no longer on your horse.");
			return true;
		}

		h = (Horse) e;
		
		Variables.previousLocations.put(p, p.getLocation());
		
		p.sendMessage("§7§oTeleporting...");
		h.eject();
		p.eject();
		h.teleport(matchedPlayer);
		p.teleport(matchedPlayer);
		h.setPassenger(p);
		
		
		
		return true;
	}

}
