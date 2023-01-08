package net.xtitle.api.adapt;

import org.bukkit.entity.Player;

public interface SimpleAdapt {
	/**
	 * Default method for send just a title and subtitle.
	 *
	 * @param player Player object.
	 * @param title Title message.
	 * @param subtitle Subtitle message.
	 */
	default void sendTitle(Player player, String title, String subtitle) {
		sendTitle(player, title, subtitle, 20, 60, 20);
	}
	
	/**
	 * Default method for clear the current title sent to player.
	 *
	 * @param player Player object.
	 */
	default void clearTitle(Player player) {
		sendTitle(player, "", "");
	}
	
	/**
	 * Send a title with times settings.
	 *
	 * @param player Player object.
	 * @param title Title message.
	 * @param subtitle Subtitle message.
	 * @param fadeIn In-coming time.
	 * @param stay Staying time.
	 * @param fadeOut Outing time.
	 */
	void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut);
	
	/**
	 * Sets a new TabList (Header/Footer) format for the player.
	 *
	 * @param player Player object.
	 * @param header Header text.
	 * @param footer Footer text.
	 */
	void sendTabList(Player player, String header, String footer);
	
	/**
	 * Send an actionbar to player.
	 *
	 * @param player Player object.
	 * @param message Message to send.
	 */
	void sendActionBar(Player player, String message);
}
