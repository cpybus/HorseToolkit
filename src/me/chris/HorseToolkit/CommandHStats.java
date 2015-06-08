package me.chris.HorseToolkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class CommandHStats implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
    {
		if (!(sender instanceof Player))
		{
			return true;
		}

		Player p = (Player) sender;
		
		if(!Variables.perms.has(p, "HorseToolkit.stats"))
		{
			p.sendMessage("§a[HorseToolkit] §4You do not have permission.");
			return true;
		}
				
		Entity e = p.getVehicle();
		if(e != null)
		{
			if(e instanceof Horse)
			{
				Horse h = (Horse) e;
				int j = (int) (h.getJumpStrength() * 1000);
																
				p.sendMessage("§a§lInformation about this horse...");
				if(h.getCustomName() != null)
				{
					p.sendMessage("§6Name - §c" + h.getCustomName());
				}
				else
				{
					p.sendMessage("§6Name - §cThis horse has not been named yet.");
				}
				
				p.sendMessage("§6Age - §c" + h.getAge());
				p.sendMessage("§6Jump Strength - §c" + j/1000.0);
				p.sendMessage("§6Domestication - §c" + h.getDomestication() + "/" + h.getMaxDomestication());
				p.sendMessage("§6Color - §c" + h.getColor().name());
				p.sendMessage("§6Style - §c" + h.getStyle().name());
				p.sendMessage("§6Variant - §c" + h.getVariant().name());
				//p.sendMessage("§6Health - §c" + h.getHealth() + "/" +  h.getMaxHealth());
				if(h.getOwner() != null)
				{
					p.sendMessage("§6Owner - §c" + h.getOwner().getName());
				}
				else
				{
					p.sendMessage("§6Owner - §cNo owner");
				}
				
			}
			else
			{
				p.sendMessage("You are not on a horse.");
			}
		}
		
		return true;
		
    }

}
