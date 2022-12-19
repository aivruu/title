package net.xtitle.lib;

import net.xtitle.api.adapt.SimpleAdapt;
import net.xtitle.lib.managers.SimpleAdaptManager;
import net.xtitle.lib.managers.SimpleTitleManager;
import org.bukkit.Bukkit;

public interface XTitle {
	/**
	 * Returns true if the introduced version number is major than the server version that's running.
	 * Overwise will be return false.
	 *
	 * @param version The version number. Example: 8 (1.8)
	 * @return A boolean value.
	 */
	static boolean supports(int version) {
		return version >= Integer.parseInt(Bukkit.getBukkitVersion()
			 .split("-")[0]
			 .split("\\.")[1]);
	}
	
	/**
	 * Returns a new instance of SimpleAdaptManager (AdaptManager implementation) object.
	 *
	 * @return a SimpleAdaptManager instance.
	 */
	static SimpleAdaptManager adaptManager() {
		return new SimpleAdaptManager();
	}
	
	/**
	 * Returns a new instance of SimpleTitleManager (TitleManager implementation) object.
	 *
	 * @param adapt A SimpleAdapt object, this object can be null for the versions 1.17 or newer.
	 * @return A SimpleTitleManager instance.
	 */
	static SimpleTitleManager titleManager(SimpleAdapt adapt) {
		return new SimpleTitleManager(adapt);
	}
}
