package net.xtitle.lib.managers;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.xtitle.api.TitleManager;
import net.xtitle.api.adapt.SimpleAdapt;
import net.xtitle.lib.XTitle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SimpleTitleManager
implements TitleManager {
	private final SimpleAdapt adapt;
	
	public SimpleTitleManager(SimpleAdapt adapt) {
		this.adapt = adapt;
	}
	
	/**
	 * Reset the current title of player.
	 *
	 * @param player Player object.
	 */
	@Override
	public void clearTitle(Player player) {
		// Checks if the server version is equals or higher to 1.17
		if (XTitle.supports(17)) player.resetTitle();
		else adapt.clearTitle(player);
	}
	
	/**
	 * Sends a title to player without time parameters.
	 *
	 * @param player Player object.
	 * @param title Title message.
	 * @param subtitle Subtitle message.
	 */
	@Override
	public void sendTitle(Player player, String title, String subtitle) {
		// Checks if the server version is equals or higher to 1.17
		if (XTitle.supports(17)) player.sendTitle(title, subtitle, 20, 60, 20);
		else adapt.sendTitle(player, title, subtitle);
	}
	
	/**
	 * Sends a title to player with the times.
	 *
	 * @param player Player object.
	 * @param fadeIn In-coming time.
	 * @param stay Staying time.
	 * @param fadeOut Outing time.
	 * @param title Title message.
	 * @param subtitle Subtitle message.
	 */
	@Override
	public void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
		// Checks if the server version is equals or higher to 1.17
		if (XTitle.supports(17)) player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
		else adapt.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
	}
	
	/**
	 * Sends an actionbar to player without a duration.
	 *
	 * @param player Player object.
	 * @param message ActionBar message.
	 */
	@Override
	public void sendActionBar(Player player, String message) {
		// Checks if the server version is equals or higher to 1.17
		if (XTitle.supports(17)) player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
		else adapt.sendActionBar(player, message);
	}
	
	/**
	 * Sends an actionbar to player with a ticks duration.
	 *
	 * [!] Method code taken out from XSeries repository.
	 *
	 * @param plugin A JavaPlugin object/instance.
	 * @param player Player object.
	 * @param message The actionbar message.
	 * @param duration The duration.
	 */
	@Override
	public void sendActionBar(JavaPlugin plugin, Player player, String message, long duration) {
		// Checks if the duration value is minor than '1'.
		if (duration < 1) return;
		
		new BukkitRunnable() {
			long repeater = duration;
			
			@Override
			public void run() {
				// Checks if the server version is equals or higher to 1.17
				if (XTitle.supports(17)) player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
				else adapt.sendActionBar(player, message);
				
				repeater -= 40L;
				if (repeater - 40L < -20L) cancel();
			}
		}.runTaskTimerAsynchronously(plugin, 0L, 40L);
	}
}
