package net.xtitle.lib.managers;

import net.xtitle.api.AdaptManager;
import net.xtitle.api.adapt.SimpleAdapt;
import net.xtitle.lib.XTitle;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;

public class SimpleAdaptManager
implements AdaptManager {
	private SimpleAdapt adapt;
	
	public SimpleAdaptManager() {}
	
	/**
	 * Set up the adapt correspond to use.
	 *
	 * @param adapt The adapt for the server version that will be use.
	 */
	@Override
	public void adapt(SimpleAdapt adapt) {
		// If the server version is equals or higher to 1.17, the adapt is not necessary.
		if (XTitle.canSupport(17)) return;
		
		// Checks if the adapt to set is null.
		if (adapt == null) throw new IllegalArgumentException("The adapt to set can't be null, please specify a valid adapt.");
		
		this.adapt = adapt;
	}
	
	/**
	 * Search automatically the adapt correspond to the server version for use.
	 */
	@Override
	public void findAdapt() {
		String packageName = Bukkit.getServer()
			 .getClass()
			 .getPackage()
			 .getName();
		String version = packageName.substring(packageName.lastIndexOf('.') + 1);
		
		try {
			Class<?> clazz = Class.forName("net.xtitle.adapt." + version + ".AdaptHandler");
			if (SimpleAdapt.class.isAssignableFrom(clazz)) adapt = (SimpleAdapt) clazz.getConstructor().newInstance();
		} catch (InvocationTargetException | InstantiationException | NoSuchMethodException
					| ClassNotFoundException | IllegalAccessException exception) {
			Bukkit.getLogger().severe("Cannot found the adapt required for this server version, check if this server version is supported.");
			Bukkit.getLogger().severe("Or check if you've the adapt necessary.");
			exception.printStackTrace();
		}
	}
	
	/**
	 * Returns the current SimpleData object. This will be return null if there not set an adapt.
	 *
	 * @return A SimpleAdapt object or null.
	 */
	@Override
	public SimpleAdapt getAdapt() {
		return adapt;
	}
}
