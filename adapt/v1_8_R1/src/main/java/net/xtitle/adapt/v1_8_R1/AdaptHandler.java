package net.xtitle.adapt.v1_8_R1;

import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.PacketDataSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R1.PlayerConnection;
import net.xtitle.api.adapt.SimpleAdapt;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class AdaptHandler
implements SimpleAdapt {
	public AdaptHandler() {}
	
	@Override
	public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
	
		connection.sendPacket(new PacketPlayOutTitle(fadeIn, stay, fadeOut));
		connection.sendPacket(new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\": \"" + title + "\"}")));
		connection.sendPacket(new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\": \"" + subtitle + "\"}")));
	}
	
	@Override
	public void sendTabList(Player player, String header, String footer) {
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		PacketDataSerializer dataSerializer = new PacketDataSerializer(Unpooled.buffer());
		
		dataSerializer.a(ChatSerializer.a("{\"text\": \"" + footer + "\"}"));
		packet.a(dataSerializer);
		
		dataSerializer.a(ChatSerializer.a("{\"text\": \"" + header + "\"}"));
		packet.a(dataSerializer);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	@Override
	public void sendActionBar(Player player, String message) {
		((CraftPlayer) player).getHandle()
			 .playerConnection
			 .sendPacket(new PacketPlayOutChat(ChatSerializer.a("{\"text\": \"" + message + "\"}"), (byte) 2));
	}
}
