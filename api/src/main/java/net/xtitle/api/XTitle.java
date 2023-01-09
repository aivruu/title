package net.xtitle.api;

import net.xtitle.api.adapt.SimpleAdapt;
import net.xtitle.api.adapt.SimpleAdaptManager;
import org.bukkit.Bukkit;

public interface XTitle {
	/**
	 * Returns true if the server version that's running is equals or higher than the version specified.
	 * Overwise will be return false.
	 *
	 * @param version The version number. Example: 8 (1.8)
	 * @return A boolean value.
	 */
	static boolean canSupport(int version) {
		return Integer.parseInt(Bukkit.getBukkitVersion()
			 .split("-")[0]
			 .split("\\.")[1]) >= version;
	}
	
	/**
	 * Returns a new instance of SimpleAdaptManager (AdaptManager implementation) object.
	 *
	 * @return a SimpleAdaptManager instance.
	 */
	static SimpleAdaptManager newAdaptManager() {
		return new SimpleAdaptManager();
	}
	
	/**
	 * Returns a new instance of SimpleTitleManager (TitleManager implementation) object.
	 *
	 * @param adapt A SimpleAdapt object, this object can be null for the versions 1.17 or newer.
	 * @return A SimpleTitleManager instance.
	 */
	static SimpleTitleManager newTitleManager(SimpleAdapt adapt) {
		return new SimpleTitleManager(adapt);
	}
}
