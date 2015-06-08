package me.chris.HorseToolkit;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class HorseToolkitListener implements Listener
{
	@EventHandler(priority = EventPriority.NORMAL)
	public void playerInteract(PlayerInteractEntityEvent event)
	{
		Player p = event.getPlayer();
		Entity e = event.getRightClicked();

		Horse h;

		if (!(e instanceof Horse))
		{
			return;
		}

		h = (Horse) e;

		AnimalTamer owner = h.getOwner();

		if (owner != null)
		{

			if (!owner.getName().equalsIgnoreCase(p.getName()))
			{
				if (Variables.perms.has(p, "HorseToolkit.ride"))
				{
					p.sendMessage("§a[HorseToolkit] §4This horse is not yours, but you have perms to ride it.");
				}
				else
				{
					p.sendMessage("§a[HorseToolkit] §4That horse was not tamed by you. You cannot ride it.");
					event.setCancelled(true);
				}
			}
		}

	}
}
