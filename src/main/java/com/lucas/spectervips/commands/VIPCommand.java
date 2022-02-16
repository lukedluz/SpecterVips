package com.lucas.spectervips.commands;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lucas.spectervips.Main;
import com.lucas.spectervips.APIs.ThreadVIP;

public class VIPCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("ativarvip")) {
			if ((sender instanceof Player)) {
				Player p = (Player) sender;
				if (!p.hasPermission("specter.vip")) {
					p.sendMessage("§cVocê não tem permissão para executar este comando.");
					return true;
				}
			}
			if (args.length == 0) {
				sender.sendMessage("§cUtilize /ativarvip <usuário> <VIP> <tempo>.");
			} else if (args.length == 3) {
				int dias = 0;
				try {
					dias = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					sender.sendMessage("§cPor favor, utilize apenas números.");
					return true;
				}
				Player p = Bukkit.getPlayer(args[0]);
				if ((args[1].equalsIgnoreCase("Marvin")) || (args[1].equalsIgnoreCase("Atlanta")) || (args[1].equalsIgnoreCase("Revenge")) || (args[1].equalsIgnoreCase("Imperatriz"))|| (args[1].equalsIgnoreCase("Caribe"))) {
					if (p != null) {
						try {
							Connection c = Main.sql.getNewConnection();
							Statement stmt = c.createStatement();
							ResultSet rs = stmt.executeQuery("SELECT * FROM SpecterVips WHERE Player='" + Bukkit.getPlayer(args[0]).getName() + "'");
							boolean hasvip = false;
							if (rs.next()) {
								if (!rs.getString("Tipo").equalsIgnoreCase(args[1])) {
									ThreadVIP th = new ThreadVIP();
									th.darvip(p, args[1], dias, 0L);
									th.start();
									return true;
								}
								hasvip = true;
							}
							if ((args[1].equalsIgnoreCase("Marvin")) || (args[1].equalsIgnoreCase("Atlanta")) || (args[1].equalsIgnoreCase("Revenge")) || (args[1].equalsIgnoreCase("Imperatriz"))|| (args[1].equalsIgnoreCase("Caribe"))) {
								if (hasvip) {
									long time = Long.parseLong(rs.getString("Time")) - System.currentTimeMillis();
									ThreadVIP th = new ThreadVIP();
									stmt.execute("DELETE FROM SpecterVips WHERE Player='" + p.getName() + "' AND Tipo='" + String.valueOf(args[1].charAt(0)).toUpperCase() + args[1].substring(1)
											+ "'");
									th.darvip(p, args[1], dias, time);
									th.start();
								} else {
									long time = 0L;
									ThreadVIP th = new ThreadVIP();
									stmt.execute("DELETE FROM SpecterVips WHERE Player='" + p.getName() + "' AND Tipo='" + String.valueOf(args[1].charAt(0)).toUpperCase() + args[1].substring(1)
											+ "'");
									th.darvip(p, args[1], dias, time);
									th.start();
								}
								sender.sendMessage("§aVIP ativado para o usuário " + p.getName() + "!");
							} else {
								sender.sendMessage("§cNão foi possível localizar este VIP.");
							}
							rs.close();
							c.close();
							stmt.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					sender.sendMessage("§cNão foi possível localizar este VIP.");
				}
			} else {
				sender.sendMessage("§cUtilize /ativarvip <usuário> <VIP> <tempo>.");
			}
		}
		if (label.equalsIgnoreCase("tirarvip")) {
			if ((sender instanceof Player)) {
				Player p = (Player) sender;
				if (!p.hasPermission("specter.vip")) {
					p.sendMessage("§cVocê não tem permissão para executar este comando.");
					return true;
				}
			}
			if (args.length != 2) {
				sender.sendMessage("§cUtilize /tirarvip <usuário> <VIP>.");
			} else {
				String player = args[0];
				String tipo = args[1];
				if (Bukkit.getPlayer(player) != null) {
					try {
						Connection c = Main.sql.getNewConnection();
						Statement stmt = c.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT Player FROM SpecterVips WHERE Player='" + Bukkit.getPlayer(player).getName() + "'");
						if (rs.next()) {
							ThreadVIP v = new ThreadVIP();
							v.rvip(Bukkit.getPlayer(player), tipo);
							v.start();
							sender.sendMessage("§cProcessando remoção de VIP...");
						} else {
							sender.sendMessage("§cEste usuário não tem nenhum VIP ativo.");
						}
						c.close();
						stmt.close();
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
						sender.sendMessage("§cOcorreu um erro durante este processo, verifique o console.");
					}
				} else {
					sender.sendMessage("§cEste usuário não está on-line.");
				}
			}
		}
		if ((label.equalsIgnoreCase("diasvip")) && ((sender instanceof Player))) {
			Player p = (Player) sender;
			ThreadVIP v = new ThreadVIP();
			v.sendInfo(p);
			v.start();
		}
		if (label.equalsIgnoreCase("vips")) {
			if ((sender instanceof Player)) {
				Player p = (Player) sender;
				if (!p.hasPermission("spectervips.vip")) {
					p.sendMessage("§cVocê não tem permissão para executar este comando.");
					return true;
				}
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("listar")) {
					Player p = (Player) sender;
					if (!p.isOp()) {
						p.sendMessage("§cVocê não tem permissão para executar este comando.");
						return true;
					}
					try {
						Connection c = Main.sql.getNewConnection();
						Statement stmt = c.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM SpecterVips");
						p.sendMessage("");
						int a = 0;
						while (rs.next()) {
							long l = Long.parseLong(rs.getString("Time"));
							boolean i = l > System.currentTimeMillis();
							if (i) {
								a++;
								p.sendMessage(rs.getString("Player") + ": §7(" + rs.getString("Tipo") + "§7) (" + Main.format(Long.parseLong(rs.getString("Time"))) + "§7)");
							}
						}
						p.sendMessage("");
						p.sendMessage("§fTotal de: §e" + a + "§f VIPs ativados até o momento atual.");
						p.sendMessage("");
						c.close();
						stmt.close();
					} catch (Exception e) {
						e.printStackTrace();
						p.sendMessage("§cOcorreu um erro durante este processo, verifique o console.");
					}
					return true;
				}
				sender.sendMessage("");
				sender.sendMessage("§e/diasvip §8-§7 Visualizar as informações de seu VIP.");
				sender.sendMessage("§e/ativarvip <usuário> <VIP> <tempo> §8-§7 Ativar um VIP para um usuário.");
				sender.sendMessage("§e/tirarvip <usuário> §8-§7 Remover o VIP de um usuário.");
				sender.sendMessage("§e/vips listar §8-§7 Listar todos os usuários VIPs do servidor.");
				sender.sendMessage("");
				return true;
			}
			sender.sendMessage("");
			sender.sendMessage("§e/diasvip §8-§7 Visualizar as informações de seu VIP.");
			sender.sendMessage("§e/ativarvip <usuário> <VIP> <tempo> §8-§7 Ativar VIP para um usuário.");
			sender.sendMessage("§e/tirarvip <usuário> §8-§7 Remover o VIP de um usuário.");
			sender.sendMessage("§e/vips listar §8-§7 Listar todos os usuários VIPs do servidor.");
			sender.sendMessage("");
		}
		return false;
	}
}
