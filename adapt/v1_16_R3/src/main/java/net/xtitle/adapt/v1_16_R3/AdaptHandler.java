package net.xtitle.adapt.v1_16_R3;

import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import net.xtitle.api.adapt.SimpleAdapt;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

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
		packet.header = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
		packet.footer = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
	
	@Override
	public void sendActionBar(Player player, String message) {
		((CraftPlayer) player).getHandle()
			 .playerConnection
			 .sendPacket(new PacketPlayOutTitle(
				  PacketPlayOutTitle.EnumTitleAction.ACTIONBAR,
				  IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"))
			 );
	}
}
