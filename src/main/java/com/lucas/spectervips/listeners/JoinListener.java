package com.lucas.spectervips.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.lucas.spectervips.APIs.ThreadVIP;

public class JoinListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onLogin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		ThreadVIP tv = new ThreadVIP();
		tv.updateVip(p);
		tv.start();
	}

	@EventHandler
	public void IsVipDays(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().toLowerCase().equalsIgnoreCase("/tempo vip")) {
			e.setCancelled(true);
			p.chat("/diasvip");
			return;
		}
	}
}
