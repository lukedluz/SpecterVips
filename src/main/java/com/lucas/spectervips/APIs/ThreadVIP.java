package com.lucas.spectervips.APIs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.lucas.spectervips.Main;
import com.lucas.spectervips.utils.ParticleEffect;
import com.lucas.spectervips.utils.Promotions;
import com.lucas.spectervips.utils.Titles;
import com.lucas.spectervips.utils.UltimateFancy;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ThreadVIP<SpecterEssency> extends Thread {

	private String function = "";
	private Player p;
	private String player;
	private String tipo = "";
	private int l = 0;
	private long time = 0L;

	public String getPlayer() {
		return this.player;
	}

	public void darvip(Player p, String tipo, int dias, long time) {
		this.function = "ativarvip";
		this.tipo = tipo;
		this.p = p;
		this.l = dias;
		this.time = time;
	}

	public void rvip(Player p, String tipo) {
		this.function = "removervip";
		this.tipo = String.valueOf(tipo.charAt(0)).toUpperCase() + tipo.substring(1);
		this.p = p;
	}

	public void sendInfo(Player p) {
		this.function = "sendinfo";
		this.p = p;
	}

	public void updateVip(Player p) {
		this.p = p;
		this.function = "update";
	}

	@SuppressWarnings("deprecation")
	public void run() {
		if (this.function.equalsIgnoreCase("update")) {
			try {
				Connection c = Main.sql.getNewConnection();
				Statement stmt = c.createStatement();
				Statement stmt2 = c.createStatement();
				Statement stmt3 = c.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT Player FROM SpecterVips WHERE Player='" + this.p.getName() + "'");
				if (rs.next()) {
					int count = 0;
					while(rs.next()) {
					    count++;
					}
					if (count <= 1) {
						stmt2.execute("UPDATE `permissions_inheritance` SET `parent` = '" + "Membro" + "' WHERE `child` = '" + p.getUniqueId().toString() + "'");
					}
					ResultSet rs1 = stmt2
							.executeQuery("SELECT * FROM SpecterVips WHERE Player='" + this.p.getName() + "'");
					rs1.next();
					long l = Long.parseLong(rs1.getString("Time"));
					String tipo2 = rs1.getString("Tipo");
					if (l < System.currentTimeMillis()) {
						this.p.sendMessage("§c");
						this.p.sendMessage("§cInfelizmente seu tempo de VIP " + tipo2 + " acabou.");
						this.p.sendMessage("§cObrigado por colaborar com o servidor.");
						this.p.sendMessage("§cRenove seu VIP agora em: §bhttps://loja.redespecter.xyz");
						this.p.sendMessage("");
						PermissionUser pex = PermissionsEx.getUser(this.p);
						PermissionGroup[] groups = pex.getGroups();
						for (PermissionGroup group : groups) {
							if (group.getName().equalsIgnoreCase("Free")) {
								if (tipo2.equalsIgnoreCase("Free")) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											"pex user " + p.getName() + " group remove Free");
								}
							} else if (group.getName().equalsIgnoreCase("Marvin")) {
								if (tipo2.equalsIgnoreCase("Marvin")) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											"pex user " + p.getName() + " group remove Marvin");
								}
							} else if (group.getName().equalsIgnoreCase("Atlanta")) {
								if (tipo2.equalsIgnoreCase("Atlanta")) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											"pex user " + p.getName() + " group remove Atlanta");
								}
							} else if (group.getName().equalsIgnoreCase("Revenge")) {
								if (tipo2.equalsIgnoreCase("Revenge")) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											"pex user " + p.getName() + " group remove Revenge");
								}
							} else if (group.getName().equalsIgnoreCase("Imperatriz")) {
								if (tipo2.equalsIgnoreCase("Imperatriz")) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											"pex user " + p.getName() + " group remove Imperatriz");
								}
							} else if (group.getName().equalsIgnoreCase("Caribe")) {
								if (tipo2.equalsIgnoreCase("Caribe")) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											"pex user " + p.getName() + " group remove Caribe");
								}
							}
						}
						stmt3.execute("DELETE FROM SpecterVips WHERE Player='" + this.p.getName() + "' AND Tipo='" + tipo2
								+ "'");
					} else {
						ResultSet rs2 = stmt3
								.executeQuery("SELECT Tipo FROM SpecterVips WHERE Player='" + this.p.getName() + "'");
						rs2.next();
						String tipo = rs2.getString("Tipo");
						if (this.tipo.equalsIgnoreCase("Free")) {
							if (tipo.equalsIgnoreCase("Free")) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										"pex user " + p.getName() + " group add Free");
							}
						}
						if (this.tipo.equalsIgnoreCase("Marvin")) {
							if (tipo.equalsIgnoreCase("Marvin")) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										"pex user " + p.getName() + " group add Marvin");
							}
						}
						if (this.tipo.equalsIgnoreCase("Atlanta")) {
							if (tipo.equalsIgnoreCase("Atlanta")) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										"pex user " + p.getName() + " group add Atlanta");
							}
						}
						if (this.tipo.equalsIgnoreCase("Revenge")) {
							if (tipo.equalsIgnoreCase("Revenge")) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										"pex user " + p.getName() + " group add Revenge");
							}
						}
						if (this.tipo.equalsIgnoreCase("Imperatriz")) {
							if (tipo.equalsIgnoreCase("Imperatriz")) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										"pex user " + p.getName() + " group add Imperatriz");
							}
						}
						if (this.tipo.equalsIgnoreCase("Caribe")) {
							if (tipo.equalsIgnoreCase("Caribe")) {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
										"pex user " + p.getName() + " group add Caribe");
							}
						}
						rs2.close();
					}
					rs1.close();
				}
				c.close();
				stmt.close();
				stmt2.close();
				stmt3.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (this.function.equalsIgnoreCase("removervip")) {
			try {
				Connection c = Main.sql.getNewConnection();
				Statement stmt = c.createStatement();
				Statement stmt2 = c.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT Player FROM SpecterVips WHERE Player='" + this.p.getName() + "'");
				if (rs.next()) {
					int count = 0;
					while(rs.next()) {
					    count++;
					}
					if (count <= 1) {
						stmt2.execute("UPDATE `permissions_inheritance` SET `parent` = '" + "Membro" + "' WHERE `child` = '" + p.getUniqueId().toString() + "'");
					}
					stmt2.execute("DELETE FROM SpecterVips WHERE Player='" + this.p.getName() + "' AND Tipo='" + this.tipo
							+ "'");
				}
				c.close();
				stmt.close();
				stmt2.close();
				rs.close();
				PermissionUser pex = PermissionsEx.getUser(this.p);
				PermissionGroup[] groups = pex.getGroups();
				for (PermissionGroup group : groups) {
					if (group.getName().equalsIgnoreCase("Free")) {
						if (this.tipo.equalsIgnoreCase("Free")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"pex user " + p.getName() + " group remove Free");
						}
					} else if (group.getName().equalsIgnoreCase("Marvin")) {
						if (this.tipo.equalsIgnoreCase("Marvin")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"pex user " + p.getName() + " group remove Marvin");
						}
					} else if (group.getName().equalsIgnoreCase("Atlanta")) {
						if (this.tipo.equalsIgnoreCase("Atlanta")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"pex user " + p.getName() + " group remove Atlanta");
						}
					} else if (group.getName().equalsIgnoreCase("Revenge")) {
						if (this.tipo.equalsIgnoreCase("Revenge")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"pex user " + p.getName() + " group remove Revenge");
						}
					} else if (group.getName().equalsIgnoreCase("Imperatriz")) {
						if (this.tipo.equalsIgnoreCase("Imperatriz")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"pex user " + p.getName() + " group remove Imperatriz");
						}
					} else if (group.getName().equalsIgnoreCase("Caribe")) {
						if (this.tipo.equalsIgnoreCase("Caribe")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
									"pex user " + p.getName() + " group remove Caribe");
						}
					}
				}
				this.p.sendMessage("");
				this.p.sendMessage("§cSeu VIP foi removido por um Administrador.");
				this.p.sendMessage("");
				this.p.playSound(this.p.getLocation(), Sound.ENTITY_CAT_PURREOW, 1.0F, 0.5F);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (this.function.equalsIgnoreCase("ativarvip")) {
			Main.plugin.info("Iniciando processo de ativação, usuário: " + this.p.getName() + " VIP: " + this.tipo);
			if (this.tipo.equalsIgnoreCase("Free")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"pex user " + this.p.getName() + " group add Free");
				try {
					long l = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(this.l) + this.time;
					Calendar ca = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String time = sdf.format(ca.getTime());
					Connection c = Main.sql.getNewConnection();
					Statement stmt = c.createStatement();
					Statement stmt2 = c.createStatement();
					stmt.execute("UPDATE `permissions_inheritance` SET `parent` = '" + "VIP" + "' WHERE `child` = '" + p.getUniqueId().toString() + "'");
					stmt2.execute("INSERT INTO SpecterVips (Player, Tipo, Time, Inicio) VALUES ('" + this.p.getName()
							+ "','" + "Free" + "','" + l + "','" + time + "');");
					c.close();
					stmt.close();
					stmt2.close();
					this.p.sendMessage("");
					this.p.sendMessage("§fOhh yeah! Seu VIP §e[Free] §ffoi ativado com sucesso.");
					this.p.sendMessage("§fDuração: §7" + this.l + " dias.");
					this.p.sendMessage("");
					new UltimateFancy("§eClique ").next().text("§lAQUI§r").clickRunCmd("/diasvip").next()
							.text("§e para mais informações de seu VIP.").send(this.p);
					this.p.playSound(this.p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
					this.p.getWorld().strikeLightningEffect(this.p.getLocation());
					ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, this.p.getLocation(), 16.0D);
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(" §f[§e§l❁§f] §e" + this.p.getName() + " §fativou o VIP §e[Free]§f.");
					Bukkit.broadcastMessage("");
					for (Player p : Bukkit.getOnlinePlayers()) {
						Titles.sendTitle(p, "§e" + this.p.getName(), "§fativou o VIP §e[Free]§f!", 5, 5, 5);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (this.tipo.equalsIgnoreCase("Marvin")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"pex user " + this.p.getName() + " group add Marvin");
				try {
					long l = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(this.l) + this.time;
					Calendar ca = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String time = sdf.format(ca.getTime());
					Connection c = Main.sql.getNewConnection();
					Statement stmt = c.createStatement();
					Statement stmt2 = c.createStatement();
					stmt.execute("UPDATE `permissions_inheritance` SET `parent` = '" + "VIP" + "' WHERE `child` = '" + p.getUniqueId().toString() + "'");
					stmt2.execute("INSERT INTO SpecterVips (Player, Tipo, Time, Inicio) VALUES ('" + this.p.getName()
							+ "','" + "Marvin" + "','" + l + "','" + time + "');");
					c.close();
					stmt.close();
					stmt2.close();
					this.p.sendMessage("");
					this.p.sendMessage("§fOhh yeah! Seu VIP §6[Marvin] §ffoi ativado com sucesso.");
					this.p.sendMessage("§fDuração: §7" + this.l + " dias.");
					this.p.sendMessage("");
					new UltimateFancy("§eClique ").next().text("§lAQUI§r").clickRunCmd("/diasvip").next()
							.text("§e para mais informações de seu VIP.").send(this.p);
					this.p.playSound(this.p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
					this.p.getWorld().strikeLightningEffect(this.p.getLocation());
					ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, this.p.getLocation(), 16.0D);
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(" §f[§6§l❁§f] §6" + this.p.getName() + " §fativou o VIP §6[Marvin]§f.");
					Bukkit.broadcastMessage("");
					for (Player p : Bukkit.getOnlinePlayers()) {
						Titles.sendTitle(p, "§6" + this.p.getName(), "§fativou o VIP §6[Marvin]§f!", 5, 5, 5);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (this.tipo.equalsIgnoreCase("Atlanta")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"pex user " + this.p.getName() + " group add Atlanta");
				try {
					long l = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(this.l) + this.time;
					Calendar ca = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String time = sdf.format(ca.getTime());
					Connection c = Main.sql.getNewConnection();
					Statement stmt = c.createStatement();
					Statement stmt2 = c.createStatement();
					stmt.execute("UPDATE `permissions_inheritance` SET `parent` = '" + "VIP" + "' WHERE `child` = '" + p.getUniqueId().toString() + "'");
					stmt2.execute("INSERT INTO SpecterVips (Player, Tipo, Time, Inicio) VALUES ('" + this.p.getName()
							+ "','" + "Atlanta" + "','" + l + "','" + time + "');");
					c.close();
					stmt.close();
					stmt2.close();
					this.p.sendMessage("");
					this.p.sendMessage("§fOhh yeah! Seu VIP §1[Atlanta] §ffoi ativado com sucesso.");
					this.p.sendMessage("§fDuração: §7" + this.l + " dias.");
					this.p.sendMessage("");
					new UltimateFancy("§eClique ").next().text("§lAQUI§r").clickRunCmd("/diasvip").next()
							.text("§e para mais informações de seu VIP.").send(this.p);
					this.p.playSound(this.p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
					this.p.getWorld().strikeLightningEffect(this.p.getLocation());
					ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, this.p.getLocation(), 16.0D);
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(" §f[§1§l❁§f] §1" + this.p.getName() + " §fativou o VIP §1[Atlanta]§f.");
					Bukkit.broadcastMessage("");
					for (Player p : Bukkit.getOnlinePlayers()) {
						Titles.sendTitle(p, "§1" + this.p.getName(), "§fativou o VIP §1[Atlanta]§f!", 5, 5, 5);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (this.tipo.equalsIgnoreCase("Revenge")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"pex user " + this.p.getName() + " group add Revenge");
				try {
					long l = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(this.l) + this.time;
					Calendar ca = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String time = sdf.format(ca.getTime());
					Connection c = Main.sql.getNewConnection();
					Statement stmt = c.createStatement();
					Statement stmt2 = c.createStatement();
					stmt.execute("UPDATE `permissions_inheritance` SET `parent` = '" + "VIP" + "' WHERE `child` = '" + p.getUniqueId().toString() + "'");
					stmt2.execute("INSERT INTO SpecterVips (Player, Tipo, Time, Inicio) VALUES ('" + this.p.getName()
							+ "','" + "Revenge" + "','" + l + "','" + time + "');");
					c.close();
					stmt.close();
					stmt2.close();
					this.p.sendMessage("");
					this.p.sendMessage("§fOhh yeah! Seu VIP §a[Revenge] §ffoi ativado com sucesso.");
					this.p.sendMessage("§fDuração: §7" + this.l + " dias.");
					this.p.sendMessage("");
					new UltimateFancy("§eClique ").next().text("§lAQUI§r").clickRunCmd("/diasvip").next()
							.text("§e para mais informações de seu VIP.").send(this.p);
					this.p.playSound(this.p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
					this.p.getWorld().strikeLightningEffect(this.p.getLocation());
					ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, this.p.getLocation(), 16.0D);
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(" §f[§a§l❁§f] §a" + this.p.getName() + " §fativou o VIP §a[Revenge]§f.");
					Bukkit.broadcastMessage("");
					for (Player p : Bukkit.getOnlinePlayers()) {
						Titles.sendTitle(p, "§a" + this.p.getName(), "§fativou o VIP §a[Revenge]§f!", 5, 5, 5);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (this.tipo.equalsIgnoreCase("Imperatriz")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"pex user " + this.p.getName() + " group add Imperatriz");
				try {
					long l = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(this.l) + this.time;
					Calendar ca = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String time = sdf.format(ca.getTime());
					Connection c = Main.sql.getNewConnection();
					Statement stmt = c.createStatement();
					Statement stmt2 = c.createStatement();
					stmt.execute("UPDATE `permissions_inheritance` SET `parent` = '" + "VIP" + "' WHERE `child` = '" + p.getUniqueId().toString() + "'");
					stmt2.execute("INSERT INTO SpecterVips (Player, Tipo, Time, Inicio) VALUES ('" + this.p.getName()
							+ "','" + "Imperatriz" + "','" + l + "','" + time + "');");
					c.close();
					stmt.close();
					stmt2.close();
					this.p.sendMessage("");
					this.p.sendMessage("§fOhh yeah! Seu VIP §d[Imperatriz] §ffoi ativado com sucesso.");
					this.p.sendMessage("§fDuração: §7" + this.l + " dias.");
					this.p.sendMessage("");
					new UltimateFancy("§eClique ").next().text("§lAQUI§r").clickRunCmd("/diasvip").next()
							.text("§e para mais informações de seu VIP.").send(this.p);
					this.p.playSound(this.p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
					this.p.getWorld().strikeLightningEffect(this.p.getLocation());
					ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, this.p.getLocation(), 16.0D);
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(" §f[§d§l❁§f] §d" + this.p.getName() + " §fativou o VIP §d[Imperatriz]§f.");
					Bukkit.broadcastMessage("");
					for (Player p : Bukkit.getOnlinePlayers()) {
						Titles.sendTitle(p, "§d" + this.p.getName(), "§fativou o VIP §d[Imperatriz]§f!", 5, 5, 5);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (this.tipo.equalsIgnoreCase("Caribe")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"pex user " + this.p.getName() + " group add Caribe");
				try {
					long l = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(this.l) + this.time;
					Calendar ca = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String time = sdf.format(ca.getTime());
					Connection c = Main.sql.getNewConnection();
					Statement stmt = c.createStatement();
					Statement stmt2 = c.createStatement();
					stmt.execute("UPDATE `permissions_inheritance` SET `parent` = '" + "VIP" + "' WHERE `child` = '" + p.getUniqueId().toString() + "'");
					stmt2.execute("INSERT INTO SpecterVips (Player, Tipo, Time, Inicio) VALUES ('" + this.p.getName()
							+ "','" + "Caribe" + "','" + l + "','" + time + "');");
					c.close();
					stmt.close();
					stmt2.close();
					this.p.sendMessage("");
					this.p.sendMessage("§fOhh yeah! Seu VIP §b[Caribe] §ffoi ativado com sucesso.");
					this.p.sendMessage("§fDuração: §7" + this.l + " dias.");
					this.p.sendMessage("");
					new UltimateFancy("§eClique ").next().text("§lAQUI§r").clickRunCmd("/diasvip").next()
							.text("§e para mais informações de seu VIP.").send(this.p);
					this.p.playSound(this.p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
					this.p.getWorld().strikeLightningEffect(this.p.getLocation());
					ParticleEffect.VILLAGER_HAPPY.display(1.0F, 1.0F, 1.0F, 20.0F, 70, this.p.getLocation(), 16.0D);
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(" §f[§b§l❁§f] §b" + this.p.getName() + " §fativou o VIP §b[Caribe]§f.");
					Bukkit.broadcastMessage("");
					for (Player p : Bukkit.getOnlinePlayers()) {
						Titles.sendTitle(p, "§b" + this.p.getName(), "§fativou o VIP §b[Caribe]§f!", 5, 5, 5);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Promotions.checkPomocoes(this.tipo, this.p);
		}
		if (this.function.equalsIgnoreCase("sendinfo")) {
			try {
				Connection c = Main.sql.getNewConnection();
				Statement stmt = c.createStatement();
				Statement stmt1 = c.createStatement();
				Statement stmt2 = c.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT Player FROM SpecterVips WHERE Player='" + this.p.getName() + "'");
				if (rs.next()) {
					ResultSet rs1 = stmt
							.executeQuery("SELECT Tipo FROM SpecterVips WHERE Player='" + this.p.getName() + "'");
					ResultSet rs2 = stmt1
							.executeQuery("SELECT Inicio FROM SpecterVips WHERE Player='" + this.p.getName() + "'");
					ResultSet rs3 = stmt2
							.executeQuery("SELECT Time FROM SpecterVips WHERE Player='" + this.p.getName() + "'");
					rs1.next();
					rs2.next();
					rs3.next();
					String tipo = rs1.getString("Tipo");
					String inicio = rs2.getString("Inicio");
					String time = Main.format(Long.parseLong(rs3.getString("Time")));
					this.p.sendMessage("");
					this.p.sendMessage("§a Informação sobre seu atual VIP:");
					this.p.sendMessage("");
					this.p.sendMessage("§f Tipo: §b" + tipo);
					this.p.sendMessage("§f Início: §b" + inicio);
					this.p.sendMessage("§f Expira em: §b" + time);
					this.p.sendMessage("");
					this.p.playSound(this.p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
					rs1.close();
					rs2.close();
					rs3.close();
				} else {
					this.p.sendMessage("§cVocê não tem nenhum VIP ativo.");
				}
				stmt2.close();
				stmt1.close();
				rs.close();
				stmt.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
				this.p.sendMessage("§cOcorreu um erro durante este processo, verifique o console.");
			}
		}
	}
}
