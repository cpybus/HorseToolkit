package me.chris.HorseToolkit;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class CommandHSetOwner implements CommandExecutor
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
			p.sendMessage("§a[HorseToolkit] §4Invalid command.");
			return true;
		}

		String otherP = args[0];

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

		if (Variables.perms.has(p, "HorseToolkit.setowner"))
		{
			Entity e = p.getVehicle();
			if (e != null)
			{
				if (e instanceof Horse)
				{
					Horse h = (Horse) e;
					HumanEntity he = (HumanEntity) matchedPlayer;
					AnimalTamer at = (AnimalTamer) he;

					h.setOwner(at);
					p.sendMessage("§a[HorseToolkit] §6The owner of this horse was set to §c" + matchedPlayer.getName());
					p.leaveVehicle();
				}
				else
				{
					p.sendMessage("You are not on a horse.");
				}
			}
		}
		else if (Variables.perms.has(p, "HorseToolkit.setowner.own"))
		{
			Entity e = p.getVehicle();
			if (e != null)
			{
				if (e instanceof Horse)
				{

					Horse h = (Horse) e;
					if (h.getOwner().getName().equalsIgnoreCase(p.getName()))
					{
						HumanEntity he = (HumanEntity) matchedPlayer;
						AnimalTamer at = (AnimalTamer) he;

						h.setOwner(at);
						p.sendMessage("§a[HorseToolkit] §6The owner of this horse was set to §c" + matchedPlayer.getName());
						p.leaveVehicle();
					}
					else
					{
						p.sendMessage("§a[HorseToolkit] §4This horse down not belong to you. You cannoy change the owner.");
					}
				}
				else
				{
					p.sendMessage("You are not on a horse.");
				}
			}
		}
		else
		{
			p.sendMessage("§a[HorseToolkit] §4You do not have permission.");
			return true;
		}

		return true;
	}

}
