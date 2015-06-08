package me.chris.HorseToolkit;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.milkbowl.vault.permission.Permission;

/**
 * @author Christopher Pybus
 * @date Mar 25, 2012
 * @file SimpleChatVariables.java
 * @package me.chris.HorseToolkit
 * @purpose
 */

public class Variables
{

	public static Logger log;
	public static HorseToolkitMain plugin;

	public static Permission perms;
	

	public static HashMap<Player, Player> awaitingApproval;
	public static HashMap<Player, Location> previousLocations;

	public Variables(HorseToolkitMain plugin)
	{
		Variables.plugin = plugin;
		log = Logger.getLogger("Minecraft");
		awaitingApproval = new HashMap<Player, Player>();
		previousLocations = new HashMap<Player, Location>();
	}
}
