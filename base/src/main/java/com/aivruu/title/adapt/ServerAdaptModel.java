package com.aivruu.title.adapt;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Object for manage the internal implementations.
 */
public interface ServerAdaptModel {
	/**
	 * Sends a title with times non-modifiable.
	 *
	 * @param player Player to who send the title.
	 * @param title Title message.
	 * @param subtitle Subtitle message.
	 */
	default void showTitleWithoutTimes(final @NotNull Player player, final @NotNull String title, final @NotNull String subtitle) {
		showTitle(player, title, subtitle, 20, 60, 20);
	}
	
	/**
	 * Sends an empty title.
	 *
	 * @param player Player to who send the title.
	 */
	default void clearTitle(final @NotNull Player player) {
		showTitle(player, "", "", 20, 60, 20);
	}
	
	/**
	 * Sends a title with modifiable times.
	 *
	 * @param player Player to who send the title.
	 * @param title Title message.
	 * @param subtitle Subtitle message.
	 * @param fadeIn Appear time.
	 * @param stay Stay time.
	 * @param fadeOut Out time.
	 */
	void showTitle(
		final @NotNull Player player,
		final @NotNull String title,
		final @NotNull String subtitle,
		final int fadeIn,
		final int stay,
		final int fadeOut
	);
	
	/**
	 * Modifies the tab-list header and footer.
	 *
	 * @param player Player to who modify the header/footer.
	 * @param header Content for the header.
	 * @param footer Content for the footer.
	 */
	void setHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer);
	
	/**
	 * Modifies the tab-list header.
	 *
	 * @param player Player to who modify the header.
	 * @param content Content for the header.
	 */
	void setHeader(final @NotNull Player player, final @NotNull String content);
	
	/**
	 * Modifies the tab-list footer.
	 *
	 * @param player Player to who modify the header/footer.
	 * @param content Content for the footer.
	 */
	void setFooter(final @NotNull Player player, final @NotNull String content);
	
	/**
	 * Send an actionbar to player.
	 *
	 * @param player Player object.
	 * @param message Message to send.
	 */
	void showActionBar(final @NotNull Player player, final @NotNull String message);
}
