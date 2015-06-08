package me.chris.HorseToolkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWelcome implements CommandExecutor
{
	// private static final String NOPERMS =
	// "§a[SimpleChat] §4You do not have perms to access the following command:";
	// private static final String INVALIDCOMMAND =
	// "§a[SimpleChat] §4The following command is invalid:";

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args)
    {
		if (!(sender instanceof Player))
		{
			return false;
		}

		Player p = (Player) sender;
		int n = args.length;
		
		if (n == 0)
		{
			p.sendMessage("§5=====================================================");
			p.sendMessage("§a Welcome to §cHorseToolkit §aPlugin §9(Version 1.0)");
			p.sendMessage("§a Designed and Programmed by §9Hotshot2162");
			p.sendMessage("§5=====================================================");
		}
		else if (n == 1)
		{
			if (args[0].equalsIgnoreCase("help"))
			{
				p.sendMessage("§5================§c [ HorseToolkit Help ] §5================");
				p.sendMessage("§c/HorseToolkit §e- States the general info.");
				p.sendMessage("§c/HorseToolkit help §e- Brings up the help menu. ");
				p.sendMessage("§c/htp §7[player] §e- Requests TP to another player. ");
				p.sendMessage("§c/haccept §e- Accepts a TP request. ");
				p.sendMessage("§c/hback §e- TPs back to previous location. ");
				p.sendMessage("§c/hstats §e- Displays statistics of the horse youre riding. ");
				p.sendMessage("§c/hsetowner §7[player] §e- Changes the owner of the horse youre riding. ");
			}
			else
			{
				p.sendMessage("§a[HorseToolkit] §4That command was invalid.");
			}
		}
		else
		{
			p.sendMessage("§a[HorseToolkit] §4That command was invalid.");
		}
	    return true;
    }
}
