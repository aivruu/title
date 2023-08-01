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
	
	private PacketPlayOutPlayerListHeaderFooter headerFooterPacket;
	
	public AdaptHandler() {
		json = "{\"text\": \"%s\"}";
		serializer = new PacketDataSerializer(Unpooled.buffer());
	}
	
	@Override
	public void showTitle(
		final @NotNull Player player,
		final @NotNull String title,
		final @NotNull String subtitle,
		final int fadeIn,
		final int stay,
		final int fadeOut
	) {
		final PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		
		// Sends the packet for the time values.
		connection.sendPacket(new PacketPlayOutTitle(fadeIn, stay, fadeOut));
		
		connection.sendPacket(new PacketPlayOutTitle(
			PacketPlayOutTitle.EnumTitleAction.TITLE,
			IChatBaseComponent.ChatSerializer.a(String.format(json, title))
		));
		connection.sendPacket(new PacketPlayOutTitle(
			PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
			IChatBaseComponent.ChatSerializer.a(String.format(json, subtitle))
		));
	}
	
	@Override
	public void setHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer) {
		headerFooterPacket = new PacketPlayOutPlayerListHeaderFooter();
		
		try {
			serializer.a(IChatBaseComponent.ChatSerializer.a(String.format(json, footer)));
			serializer.a(IChatBaseComponent.ChatSerializer.a(String.format(json, header)));
			
			headerFooterPacket.a(serializer);
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(headerFooterPacket);
	}
	
	@Override
	public void setHeader(final @NotNull Player player, final @NotNull String content) {
		((CraftPlayer) player).getHandle()
			.playerConnection
			.sendPacket(new PacketPlayOutPlayerListHeaderFooter(IChatBaseComponent.ChatSerializer.a(String.format(json, content))));
	}
	
	@Override
	public void setFooter(final @NotNull Player player, final @NotNull String content) {
		headerFooterPacket = new PacketPlayOutPlayerListHeaderFooter();
		
		try {
			serializer.a(IChatBaseComponent.ChatSerializer.a(String.format(json, content)));
			headerFooterPacket.a(serializer);
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(headerFooterPacket);
	}
	
	@Override
	public void showActionBar(final @NotNull Player player, final @NotNull String message) {
		((CraftPlayer) player).getHandle()
			.playerConnection
			.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(String.format(json, message)), (byte) 2));
	}

}
