package me.chris.HorseToolkit;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class CommandHTP implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
	{
		if (!(sender instanceof Player))
		{
			return true;
		}

		Player p = (Player) sender;
		int n = args.length;

		if (n != 1)
		{
			p.sendMessage("§a[HorseToolkit] §4That command was invalid.");
			return true;
		}

		if (!Variables.perms.has(sender, "HorseToolkit.teleport"))
		{
			p.sendMessage("§a[HorseToolkit] §4You do not have permissions to teleport with horses.");
			return true;
		}

		Entity e = p.getVehicle();
		Horse h;
		String otherP = args[0];

		if (e == null)
		{
			p.sendMessage("§a[HorseToolkit] §4You are not on a horse.");
			return true;
		}

		if (!(e instanceof Horse))
		{
			p.sendMessage("§a[HorseToolkit] §4You are not on a horse.");
			return true;
		}

		h = (Horse) e;

		List<Player> matchedPlayers = Variables.plugin.getServer().matchPlayer(otherP);

		if (matchedPlayers == null || matchedPlayers.size() < 1)
		{
			p.sendMessage("§a[HorseToolkit] §4The name §c" + otherP + " §4did not match any players.");
			return true;
		}
		else if (matchedPlayers.size() > 1)
		{
			p.sendMessage("§a[HorseToolkit] §4The name §c" + otherP + " §4matched more than one player. Please enter a different/more-specific name.");
			return true;
		}

		Player matchedPlayer = matchedPlayers.get(0);
		
		
		if (!p.isOp())
		{
			
			p.sendMessage("§a[HorseToolkit] §6Request sent");
			matchedPlayer.sendMessage("§a[HorseToolkit] §c" + p.getName() + " §6wishes to teleport to you with their horse. Type §c/haccept §6to accept.");
			Variables.awaitingApproval.put(matchedPlayer, p);
		}
		else
		{
			Variables.previousLocations.put(p, p.getLocation());
			
			p.sendMessage("§7§oTeleporting...");
			h.eject();
			p.eject();
			h.teleport(matchedPlayer);
			p.teleport(matchedPlayer);
			h.setPassenger(p);
			
			
		}
		return true;
	}

	// private static final String NOPERMS =
	// "§a[SimpleChat] §4You do not have perms to access the following command:";
	// private static final String INVALIDCOMMAND =
	// "§a[SimpleChat] §4The following command is invalid:";

}
