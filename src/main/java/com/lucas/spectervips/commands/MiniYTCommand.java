package com.lucas.spectervips.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lucas.spectervips.utils.FireworkEffectPlayer;
import com.lucas.spectervips.utils.Titles;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MiniYTCommand implements CommandExecutor {
	
	FireworkEffectPlayer fplayer = new FireworkEffectPlayer();
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((!command.getName().equalsIgnoreCase("ativarminiyt"))) {
			return true;
		}
		if (!sender.hasPermission("specter.miniyts")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			p.sendMessage("§cUtilize /ativarminiyt <usuário>.");
			return true;
		}
		if (args.length == 1) {
			Player p1 = Bukkit.getServer().getPlayer(args[0]);
			PermissionUser pex = PermissionsEx.getUser(p1);
			if (p1 == null) {
				sender.sendMessage("§cEste usuário não está on-line.");
				return true;
			}
			p.sendMessage("§aTag ativada para o usuário " + p1.getName() + "!");
	        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p1.getName() + " group add MiniYT");
			p1.sendMessage("");
			p1.sendMessage("§fOhh yeah! Seu §d[MiniYT] §ffoi ativada com sucesso.");
			p1.sendMessage("§fTenha um ótimo jogo!");
			p1.sendMessage("");
			p1.playSound(p1.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
			p1.getWorld().strikeLightningEffect(p1.getLocation());
			try {
				Color color = Color.RED;
				fplayer.playFirework(p1.getWorld(), p1.getLocation(), getRandomEffect(color));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage(" §f[§d§l❁§f] §d" + p1.getName() + " §ftornou-se §d[MiniYT]§f.");
			Bukkit.broadcastMessage("");
			for (Player x : Bukkit.getOnlinePlayers()) {
				Titles.sendTitle(x, "§d" + p1.getName(), "§ftornou-se §d[MiniYT]§f!", 5, 5, 5);
			}
			return true;
		}
		return false;
	}
	
	public static FireworkEffect getRandomEffect(Color color) {
		return FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(color).build();
	}
}

