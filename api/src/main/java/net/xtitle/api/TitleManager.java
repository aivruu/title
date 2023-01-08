package net.xtitle.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface TitleManager {
	/**
	 * Reset the current title of player.
	 *
	 * @param player Player object.
	 */
	void clearTitle(Player player);
	
	/**
	 * Sends a title to player without time parameters.
	 *
	 * @param player Player object.
	 * @param title Title message.
	 * @param subtitle Subtitle message.
	 */
	void sendTitle(Player player, String title, String subtitle);
	
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
	void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle);
	
	/**
	 * Sends a new Header/Footer format for the player tab-list.
	 *
	 * @param player Player object.
	 * @param header TabList Header text.
	 * @param footer TabList Footer text.
	 */
	void sendTabList(Player player, String header, String footer);
	
	/**
	 * Sends an actionbar to player without a duration.
	 *
	 * @param player Player object.
	 * @param message ActionBar message.
	 */
	void sendActionBar(Player player, String message);
	
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
	void sendActionBar(JavaPlugin plugin, Player player, String message, long duration);
}
