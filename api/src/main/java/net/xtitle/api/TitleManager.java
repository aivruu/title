package net.xtitle.api;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Interface for manage and send visual Titles, ActionBars or Headers/Footers.
 */
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
	 * Sends an animated title to the player.
	 *
	 * @param plugin A JavaPlugin object/instance.
	 * @param player Player object.
	 * @param duration The duration for show the animation.
	 * @param animationRate The update-rate for the animation.
	 * @param titleContent The text lines for the title.
	 * @param subtitleContent The text lines for the subtitle.
	 */
	void sendAnimatedTitle(JavaPlugin plugin, Player player, long duration, int animationRate, List<String> titleContent, List<String> subtitleContent);
	
	/**
	 * Sends a new Header/Footer format for the player tab-list.
	 *
	 * @param player Player object.
	 * @param footer TabList Footer text.
	 * @param header TabList Header text.
	 */
	void sendTabList(Player player, String footer, String header);
	
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
	 * @param duration The duration for the actionbar.
	 */
	void sendActionBar(JavaPlugin plugin, Player player, String message, long duration);
	
	/**
	 * Sends an animated actionbar message.
	 *
	 * @param plugin A JavaPlugin object/instance.
	 * @param player Player object.
	 * @param contentMessage The content message for the animation.
	 * @param animationDelay The delay value for the actionbar task.
	 * @param duration The duration for show the animation.
	 */
	void sendAnimatedActionBar(JavaPlugin plugin, Player player, List<String> contentMessage, int animationDelay, long duration);
}
