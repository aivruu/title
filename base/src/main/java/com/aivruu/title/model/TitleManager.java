package com.aivruu.title.model;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface TitleManager {
	/**
	 * Sends an empty title to player.
	 *
	 * @param player Player to who send the title.
	 */
	void clearTitle(final @NotNull Player player);
	
	/**
	 * Sends a title to player with non-modifiable times.
	 *
	 * @param player Player to who send the title.
	 * @param title Title message.
	 * @param subtitle Subtitle message.
	 */
	void showTitle(final @NotNull Player player, final @NotNull String title, final @NotNull String subtitle);
	
	/**
	 * Sends a title to player.
	 *
	 * @param player Player to who send the title.
	 * @param title Title message.
	 * @param subtitle Subtitle message content.
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
	 * @param player Player to who modify the tab-list.
	 * @param header Content for the header.
	 * @param footer Content for the footer.
	 */
	void setHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer);
	
	/**
	 * Sets the header for the player tab-list.
	 *
	 * @param player Player to who modify the header.
	 * @param header Content for the header.
	 */
	void setHeader(final @NotNull Player player, final @NotNull String header);
	
	/**
	 * Sets the footer for the player tab-list.
	 *
	 * @param player Player to who modify the footer.
	 * @param footer Content for the footer.
	 */
	void setFooter(final @NotNull Player player, final @NotNull String footer);
	
	/**
	 * Sends an actionbar to player.
	 *
	 * @param plugin A JavaPlugin instance.
	 * @param player Player to who send the actionbar.
	 * @param message Content of the message.
	 * @param durationSeconds Duration for the actionbar.
	 */
	void showActionBar(final @NotNull JavaPlugin plugin, final @NotNull Player player, final @NotNull String message, final int durationSeconds);
}
