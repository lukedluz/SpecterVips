package com.lucas.spectervips.utils;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
@SuppressWarnings("all")
public class Titles {

	public static void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
		CraftPlayer craftPlayer = (CraftPlayer) player;
		PlayerConnection connection = craftPlayer.getHandle().playerConnection;
		IChatBaseComponent titleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + title + "'}");
		IChatBaseComponent subtitleJSON = IChatBaseComponent.ChatSerializer.a("{'text': '" + subTitle + "'}");
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleJSON, fadeIn, stay, fadeOut);
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleJSON);
		connection.sendPacket(titlePacket);
		connection.sendPacket(subtitlePacket);
	}

	public static void setForPlayer(Player p, String header, String footer) {
		CraftPlayer craftplayer = (CraftPlayer) p;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent top = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
		IChatBaseComponent bottom = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		try {
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, top);
			headerField.setAccessible(!headerField.isAccessible());
			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, bottom);
			footerField.setAccessible(!footerField.isAccessible());
		} catch (Exception ev) {
			ev.printStackTrace();
		}
		connection.sendPacket(packet);
	}
}
