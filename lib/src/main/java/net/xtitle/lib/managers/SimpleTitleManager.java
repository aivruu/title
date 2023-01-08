package net.xtitle.lib.managers;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.xtitle.api.TitleManager;
import net.xtitle.api.adapt.SimpleAdapt;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;
import static net.xtitle.lib.XTitle.canSupport;

public class SimpleTitleManager
implements TitleManager {
	private final SimpleAdapt adapt;
	
	public SimpleTitleManager(@Nullable SimpleAdapt adapt) {
		this.adapt = adapt;
	}
	
	/**
	 * Reset the current title of player.
	 *
	 * @param player Player object.
	 */
	@Override
	public void clearTitle(@Nonnull Player player) {
		checkNotNull(player, "The player can't be null.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.resetTitle();
		else {
			assert adapt != null;
			adapt.clearTitle(player);
		}
	}
	
	/**
	 * Sends a title to player without time parameters.
	 *
	 * @param player Player object.
	 * @param title Title message.
	 * @param subtitle Subtitle message.
	 */
	@Override
	public void sendTitle(@Nonnull Player player, @Nonnull String title, @Nonnull String subtitle) {
		checkNotNull(player, "The player can't be null.");
		checkNotNull(title, "The title message can't be null.");
		checkNotNull(subtitle, "The subtitle message is null.");
		
		checkArgument(!title.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		checkArgument(!subtitle.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.sendTitle(title, subtitle, 20, 60, 20);
		else {
			assert adapt != null;
			adapt.sendTitle(player, title, subtitle);
		}
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
	public void sendTitle(@Nonnull Player player, int fadeIn, int stay, int fadeOut, @Nonnull String title, @Nonnull String subtitle) {
		checkNotNull(player, "The player can't be null.");
		checkNotNull(title, "The title message can't be null.");
		checkNotNull(subtitle, "The subtitle message is null.");
		
		checkArgument(!title.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		checkArgument(!subtitle.isEmpty(), "The title can't be empty. If you want to send an empty title use the clearTitle() method.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
		else {
			assert adapt != null;
			adapt.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
		}
	}
	
	@Override
	public void sendTabList(Player player, String header, String footer) {
		checkNotNull(player, "The player can't be null.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.setPlayerListHeaderFooter(header, footer);
		else {
			assert adapt != null;
			adapt.sendTabList(player, header, footer);
		}
	}
	
	@Override
	public void sendActionBar(@Nonnull Player player, @Nonnull String message) {
		checkNotNull(player, "The subtitle message is null.");
		checkNotNull(message, "The actionbar message can't be null.");
		
		checkArgument(!message.isEmpty(), "The actionbar message can't be empty.");
		
		// Checks if the server version is equals or higher to 1.17
		if (canSupport(17)) player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
		else {
			assert adapt != null;
			adapt.sendActionBar(player, message);
		}
	}
	
	@Override
	public void sendActionBar(@Nonnull JavaPlugin plugin, @Nonnull Player player, @Nonnull String message, long duration) {
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
					assert adapt != null;
					adapt.sendActionBar(player, message);
				}
				
				repeater -= 40L;
				if (repeater - 40L < -20L) cancel();
			}
		}.runTaskTimerAsynchronously(plugin, 0L, 40L);
	}
}
