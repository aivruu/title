package net.xtitle.api;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.xtitle.api.adapt.SimpleAdapt;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;
import static net.xtitle.api.XTitle.canSupport;

public class SimpleTitleManager implements TitleManager {
	private final SimpleAdapt serverAdapt;
	
	public SimpleTitleManager(SimpleAdapt serverAdapt) {
		this.serverAdapt = serverAdapt;
	}
	
	@Override
	public void clearTitle(Player player) {
		checkNotNull(player, "The player can't be null.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.resetTitle();
		else {
			assert serverAdapt != null;
			serverAdapt.clearTitle(player);
		}
	}
	
	@Override
	public void sendTitle(Player player, String title, String subtitle) {
		checkNotNull(player, "The player can't be null.");
		checkNotNull(title, "The title message can't be null.");
		checkNotNull(subtitle, "The subtitle message is null.");
		
		checkArgument(!title.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		checkArgument(!subtitle.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.sendTitle(title, subtitle, 20, 60, 20);
		else {
			assert serverAdapt != null;
			serverAdapt.sendTitle(player, title, subtitle);
		}
	}
	
	@Override
	public void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
		checkNotNull(player, "The player can't be null.");
		checkNotNull(title, "The title message can't be null.");
		checkNotNull(subtitle, "The subtitle message is null.");
		
		checkArgument(!title.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		checkArgument(!subtitle.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
		else {
			assert serverAdapt != null;
			serverAdapt.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
		}
	}
	
	@Override
	public void sendTabList(Player player, String footer, String header) {
		checkNotNull(player, "The player can't be null.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.setPlayerListHeaderFooter(header, footer);
		else {
			assert serverAdapt != null;
			// I don't know why, but I don't think that this be a bug.
			// For legacy servers using NMS native the header and footer are inverted.
			// So, the header is the footer, and the footer is the header, something like that :8
			serverAdapt.sendTabList(player, footer, header);
		}
	}
	
	@Override
	public void sendActionBar(Player player, String message) {
		checkNotNull(player, "The subtitle message is null.");
		checkNotNull(message, "The actionbar message can't be null.");
		
		checkArgument(!message.isEmpty(), "The actionbar message can't be empty.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
		else {
			assert serverAdapt != null;
			serverAdapt.sendActionBar(player, message);
		}
	}
	
	@Override
	public void sendActionBar(JavaPlugin plugin, Player player, String message, long duration) {
		checkNotNull(plugin, "The JavaPlugin instance is null.");
		checkNotNull(player, "The subtitle message is null.");
		checkNotNull(message, "The actionbar message can't be null.");
		
		checkArgument(!message.isEmpty(), "The actionbar message can't be empty.");
		
		// Checks if the duration value is minor than '1'.
		if (duration < 1) return;
		
		new BukkitRunnable() {
			long repeater = duration;
			
			@Override
			public void run() {
				// Checks if the server version is equals or higher to 1.17
				if (canSupport(17)) player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
				else {
					assert serverAdapt != null;
					serverAdapt.sendActionBar(player, message);
				}
				
				repeater -= 40L;
				if (repeater - 40L < -20L) cancel();
			}
		}.runTaskTimerAsynchronously(plugin, 0L, 40L);
	}
}
