package net.xtitle.api;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.xtitle.api.adapt.SimpleAdapt;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class SimpleTitleManager implements TitleManager {
	private static SimpleTitleManager instance;
	
	private final SimpleAdapt serverAdapt;
	
	private SimpleTitleManager(SimpleAdapt serverAdapt) {
		this.serverAdapt = serverAdapt;
	}
	
	public static SimpleTitleManager register(SimpleAdapt adapt) {
		return instance = new SimpleTitleManager(adapt);
	}
	
	public static SimpleTitleManager get() {
		return instance;
	}
	
	public static void unregister() {
		instance = null;
	}
	
	@Override
	public void clearTitle(Player player) {
		checkNotNull(player, "The player can't be null.");
		
		// Checks if the server version is equals or higher to 1.13
		if (canSupport(13)) {
			player.resetTitle();
			return;
		}
		
		assert serverAdapt != null;
		serverAdapt.clearTitle(player);
	}
	
	@Override
	public void sendTitle(Player player, String title, String subtitle) {
		checkNotNull(player, "The player can't be null.");
		checkNotNull(title, "The title message can't be null.");
		checkNotNull(subtitle, "The subtitle message is null.");
		
		checkArgument(!title.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		checkArgument(!subtitle.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		
		// Checks if the server version is equals or higher to 1.13
		if (canSupport(13)) {
			player.sendTitle(title, subtitle, 20, 60, 20);
			return;
		}
		
		assert serverAdapt != null;
		serverAdapt.sendTitle(player, title, subtitle);
	}
	
	@Override
	public void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
		checkNotNull(player, "The player can't be null.");
		checkNotNull(title, "The title message can't be null.");
		checkNotNull(subtitle, "The subtitle message is null.");
		
		checkArgument(!title.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		checkArgument(!subtitle.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		
		// Checks if the server version is equals or higher to 1.13
		if (canSupport(13)) {
			player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
			return;
		}
		
		assert serverAdapt != null;
		serverAdapt.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
	}
	
	@Override
	public void sendAnimatedTitle(JavaPlugin plugin, Player player, long duration, int animationRate, List<String> titleContent, List<String> subtitleContent) {
		checkNotNull(plugin, "The JavaPlugin instance is null.");
		checkNotNull(player, "The player can't be null.");
		checkNotNull(titleContent, "The title message content can't be null.");
		checkNotNull(subtitleContent, "The subtitle message content is null.");
		
		if ((duration < 1) || animationRate < 1) return;
		
		new BukkitRunnable() {
			int rate = animationRate;
			long repeater = duration;
			
			@Override
			public void run() {
				if ((rate == titleContent.size() - 1) || (rate == subtitleContent.size() - 1)) rate = 0;
				rate++;
				
				if (canSupport(13)) player.sendTitle(titleContent.get(rate), subtitleContent.get(rate), 1, 80, 1);
				else {
					assert serverAdapt != null;
					serverAdapt.sendTitle(player, titleContent.get(rate), subtitleContent.get(rate), 1, 80, 1);
				}
				
				repeater -= 40L;
				if (repeater - 40L < -20L) cancel();
			}
		}.runTaskTimerAsynchronously(plugin, 0L, animationRate);
	}
	
	@Override
	public void sendTabList(Player player, String footer, String header) {
		checkNotNull(player, "The player can't be null.");
		
		// Checks if the server version is equals or higher to 1.13
		if (canSupport(13)) {
			player.setPlayerListHeaderFooter(header, footer);
			return;
		}
		
		assert serverAdapt != null;
		// I don't know why, but I don't think that this be a bug.
		// For legacy servers using NMS native the header and footer are inverted.
		// So, the header is the footer, and the footer is the header, something like that :8
		serverAdapt.sendTabList(player, footer, header);
	}
	
	@Override
	public void sendActionBar(Player player, String message) {
		checkNotNull(player, "The subtitle message is null.");
		checkNotNull(message, "The actionbar message can't be null.");
		
		checkArgument(!message.isEmpty(), "The actionbar message can't be empty.");
		
		// Checks if the server version is equals or higher to 1.13
		if (canSupport(13)) {
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
			return;
		}
		
		assert serverAdapt != null;
		serverAdapt.sendActionBar(player, message);
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
				if (canSupport(13)) player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
				else {
					assert serverAdapt != null;
					serverAdapt.sendActionBar(player, message);
				}
				
				repeater -= 40L;
				if (repeater - 40L < -20L) cancel();
			}
		}.runTaskTimerAsynchronously(plugin, 0L, 40L);
	}
	
	@Override
	public void sendAnimatedActionBar(JavaPlugin plugin, Player player, List<String> contentMessage, int animationDelay, long duration) {
		checkNotNull(plugin, "The JavaPlugin instance is null.");
		checkNotNull(player, "The subtitle message is null.");
		checkNotNull(contentMessage, "The actionbar content message can't be null.");
		
		if ((duration < 1) || animationDelay < 1) return;
		
		new BukkitRunnable() {
			int rate = animationDelay;
			long repeater = duration;
			
			@Override
			public void run() {
				if (rate == contentMessage.size() - 1) {
					cancel();
					return;
				}
				
				rate++;
				
				if (canSupport(13)) player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(contentMessage.get(rate)));
				else {
					assert serverAdapt != null;
					serverAdapt.sendActionBar(player, contentMessage.get(rate));
				}
				
				repeater -= 40L;
				if (repeater - 40L < -20L) cancel();
			}
		}.runTaskTimerAsynchronously(plugin, 0L, animationDelay);
	}
	
	/**
	 * Returns true if the server version that's running is equals or higher than the version specified.
	 * Overwise will be return false.
	 *
	 * @param version The version number. Example: 8 (1.8)
	 * @return A boolean value.
	 */
	private boolean canSupport(int version) {
		return Integer.parseInt(Bukkit.getBukkitVersion()
			 .split("-")[0]
			 .split("\\.")[1]) >= version;
	}
}
