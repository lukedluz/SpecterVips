package com.lucas.spectervips;

import java.util.concurrent.TimeUnit;

import com.lucas.spectervips.commands.MiniYTCommand;
import com.lucas.spectervips.commands.VIPCommand;
import com.lucas.spectervips.commands.YTCommand;
import com.lucas.spectervips.commands.YTPlusCommand;
import com.lucas.spectervips.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.lucas.spectervips.utils.SQL;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static SQL sql;
	private static Main i;

	public Main() {
		Main.i = this;
	}

	public static Main getInstance() {
		return i;
	}

	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("§7| §bSpecterVips            §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §bVersão 1.0             §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §fStatus: §aLigado         §7|");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("");
		plugin = this;
		sql = new SQL();
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getCommand("ativarvip").setExecutor(new VIPCommand());
		getCommand("tirarvip").setExecutor(new VIPCommand());
		getCommand("diasvip").setExecutor(new VIPCommand());
		getCommand("vips").setExecutor(new VIPCommand());
		getCommand("ativarytplus").setExecutor(new YTPlusCommand());
		getCommand("ativaryt").setExecutor(new YTCommand());
		getCommand("ativarminiyt").setExecutor(new MiniYTCommand());
		info("Habilitado com sucesso.");
	}

	public void info(String msg) {
		Bukkit.getConsoleSender().sendMessage("§f§lREDE §b§lSPECTER §f " + msg);
	}

	public static String format(long time) {
		time -= System.currentTimeMillis();
		String format = "";
		long hours = TimeUnit.MILLISECONDS.toHours(time);
		long hoursInMillis = TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(time - hoursInMillis);
		long minutesInMillis = TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(time - (hoursInMillis + minutesInMillis));
		int days = (int) (time / 86400000L);
		if (hours > 0L) {
			if (days > 0) {
				time -= TimeUnit.DAYS.toMillis(days);
				hours = TimeUnit.MILLISECONDS.toHours(time - minutesInMillis);
				format = days + " dias, " + hours + (hours > 1L ? " horas" : " hora");
				return format;
			}
			format = hours + (hours > 1L ? " horas" : " hora");
		}
		if (minutes > 0L) {
			if ((seconds > 0L) && (hours > 0L)) {
				format = format + ", ";
			} else if (hours > 0L) {
				format = format + " e ";
			}
			format = format + minutes + (minutes > 1L ? " minutos" : " minuto");
		}
		if (seconds > 0L) {
			if ((hours > 0L) || (minutes > 0L)) {
				format = format + " e ";
			}
			format = format + seconds + (seconds > 1L ? " segundos" : " segundo");
		}
		if (format.equals("")) {
			long rest = time / 100L;
			if (rest == 0L) {
				rest = 1L;
			}
			format = "0." + rest + " segundo";
		}
		if (days > 0) {
			format = days + " dias";
		}
		return format;
	}

	public static Main getPlugin() {
		return JavaPlugin.getPlugin(Main.class);
	}
}
