package com.aivruu.title.impl;

import com.aivruu.title.adapt.ServerAdaptModel;
import com.aivruu.title.model.TitleManager;
import com.aivruu.title.utils.VersionUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleTitleManagerImpl implements TitleManager {
	private final ServerAdaptModel adapt;
	
	public SimpleTitleManagerImpl(final @Nullable ServerAdaptModel adapt) {
		this.adapt = adapt;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void clearTitle(final @NotNull Player player) {
		player.sendTitle("", "");
	}
	
	@Override
	public void showTitle(final @NotNull Player player, final @NotNull String title, final @NotNull String subtitle) {
		if (VersionUtils.equalsOrHigher(13)) {
			player.sendTitle(title, subtitle, 20, 60, 20);
			return;
		}
		
		assert adapt != null;
		adapt.showTitleWithoutTimes(player, title, subtitle);
	}
	
	@Override
	public void showTitle(
		final @NotNull Player player,
		final @NotNull String title,
		final @NotNull String subtitle,
		final int fadeIn,
		final int stay,
		final int fadeOut
	) {
		if (VersionUtils.equalsOrHigher(13)) {
			player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
			return;
		}
		
		assert adapt != null;
		adapt.showTitle(player, title, subtitle, fadeIn, stay, fadeOut);
	}
	
	@Override
	public void setHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer) {
		if (VersionUtils.equalsOrHigher(13)) {
			player.setPlayerListHeaderFooter(header, footer);
			return;
		}
		
		assert adapt != null;
		adapt.setHeaderAndFooter(player, header, footer);
	}
	
	@Override
	public void setHeader(final @NotNull Player player, final @NotNull String header) {
		if (VersionUtils.equalsOrHigher(13)) {
			player.setPlayerListHeader(header);
			return;
		}
		
		assert adapt != null;
		adapt.setHeader(player, header);
	}
	
	@Override
	public void setFooter(final @NotNull Player player, final @NotNull String footer) {
		if (VersionUtils.equalsOrHigher(13)) {
			player.setPlayerListFooter(footer);
			return;
		}
		
		assert adapt != null;
		adapt.setFooter(player, footer);
	}
	
	@Override
	public void showActionBar(final @NotNull JavaPlugin plugin, final @NotNull Player player, final @NotNull String message, final int durationSeconds) {
		if (durationSeconds <= 0) {
			throw new IllegalArgumentException("The actionbar duration cannot be equal or lower than zero.");
		}
		
		new BukkitRunnable() {
			long repeater = (long) durationSeconds * 20;
			
			@Override
			public void run() {
				if (VersionUtils.equalsOrHigher(13)) {
					player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
				} else {
					assert adapt != null;
					adapt.showActionBar(player, message);
				}
				
				repeater -= 40L;
				if (repeater - 40L < -20L) {
					cancel();
				}
				
			}
		}.runTaskTimerAsynchronously(plugin, 0L, 40L);
	}
}
