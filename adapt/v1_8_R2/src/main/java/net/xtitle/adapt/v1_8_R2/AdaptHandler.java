package net.xtitle.adapt.v1_8_R2;

import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketDataSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import net.minecraft.server.v1_8_R2.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import net.xtitle.api.adapt.SimpleAdapt;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.IOException;

public class AdaptHandler
implements SimpleAdapt {
	public AdaptHandler() {}
	
	@Override
	public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		
		connection.sendPacket(new PacketPlayOutTitle(fadeIn, stay, fadeOut));
		connection.sendPacket(new PacketPlayOutTitle(
			 PacketPlayOutTitle.EnumTitleAction.TITLE,
			 IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}"))
		);
		connection.sendPacket(new PacketPlayOutTitle(
			 PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
			 IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}"))
		);
	}
	
	@Override
	public void sendTabList(Player player, String header, String footer) {
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		PacketDataSerializer dataSerializer = new PacketDataSerializer(Unpooled.buffer());
		
		try {
			dataSerializer.a(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}"));
			packet.a(dataSerializer);
			
			dataSerializer.a(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}"));
			packet.a(dataSerializer);
		} catch (IOException exception) { exception.printStackTrace(); }
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	@Override
	public void sendActionBar(Player player, String message) {
		((CraftPlayer) player).getHandle()
			 .playerConnection
			 .sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"), (byte) 2));
	}
}
