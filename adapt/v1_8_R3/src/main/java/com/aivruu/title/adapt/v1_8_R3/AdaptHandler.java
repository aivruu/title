package com.aivruu.title.adapt.v1_8_R3;

import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import com.aivruu.title.adapt.ServerAdaptModel;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AdaptHandler implements ServerAdaptModel {
	private final String json;
	private final PacketDataSerializer serializer;
	
	public AdaptHandler() {
		json = "{\"text\": \"%s\"}";
		serializer = new PacketDataSerializer(Unpooled.buffer());
	}
	
	@Override
	public void showTitle(final @NotNull Player player, final @NotNull String title, final @NotNull String subtitle, final int fadeIn, final int stay, final int fadeOut) {
		final PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		
		// Performs the packet for time values.
		connection.sendPacket(new PacketPlayOutTitle(fadeIn, stay, fadeOut));
		
		connection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, parse(title)));
		connection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, parse(subtitle)));
	}
	
	@Override
	public void setHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer) {
		final PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		
		try {
			serializer.a(parse(footer));
			serializer.a(parse(header));
			
			packet.a(serializer);
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	@Override
	public void setHeader(final @NotNull Player player, final @NotNull String content) {
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerListHeaderFooter(parse(content)));
	}
	
	@Override
	public void setFooter(final @NotNull Player player, final @NotNull String content) {
		final PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
	
		try {
			serializer.a(parse(content));
			serializer.a(parse(""));
			
			packet.a(serializer);
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	@Override
	public void showActionBar(final @NotNull Player player, final @NotNull String message) {
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(parse(message), (byte) 2));
	}
	
	private @NotNull IChatBaseComponent parse(final @NotNull String text) {
		return IChatBaseComponent.ChatSerializer.a(String.format(json, text));
	}
}
