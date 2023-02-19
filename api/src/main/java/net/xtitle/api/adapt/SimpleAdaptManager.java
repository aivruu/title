package net.xtitle.api.adapt;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.logging.Logger;

public class SimpleAdaptManager implements AdaptManager {
	private static SimpleAdaptManager instance;
	
	private final Logger logger;
	
	private SimpleAdapt currentAdapt;
	
	private SimpleAdaptManager(Logger logger) {
		this.logger = Objects.requireNonNull(logger, "The Logger object from plugin cannot be null.");
	}
	
	public static SimpleAdaptManager register(Logger logger) {
		return instance = new SimpleAdaptManager(logger);
	}
	
	public static SimpleAdaptManager get() {
		return instance;
	}
	
	public static void unregister() {
		instance = null;
	}
	
	@Override
	public AdaptManager adapt(SimpleAdapt adapt) {
		Preconditions.checkNotNull(adapt, "The adapt to set can't be null, please specify a valid adapt.");
		
		// If the server version is equals or higher to 1.13, the adapt is not necessary and will return null.
		if (canSupport(13)) return null;
		
		currentAdapt = adapt;
		return this;
	}
	
	@Override
	public AdaptManager findAdapt() {
		if (canSupport(13)) return null;
		
		String packageName = Bukkit.getServer().getClass().getPackage().getName();
		
		try {
			Class<?> clazz = Class.forName("net.xtitle.adapt." + packageName.substring(packageName.lastIndexOf('.') + 1) + ".AdaptHandler");
			if (SimpleAdapt.class.isAssignableFrom(clazz)) currentAdapt = (SimpleAdapt) clazz.getConstructor().newInstance();
		} catch (InvocationTargetException | InstantiationException | NoSuchMethodException | ClassNotFoundException
					| IllegalAccessException exception) {
			logger.severe("Cannot found the adapt required for this server version, check if this server version is supported.");
			logger.severe("Or check if you've the adapt necessary.");
			exception.printStackTrace();
			return null;
		}
		
		return this;
	}
	
	@Override
	public SimpleAdapt getAdapt() {
		return currentAdapt;
	}
	
	/**
	 * Returns true if the server version that's running is equals or higher than the version specified.
	 * Overwise will be return false.
	 *
	 * @param version The version number. Example: 8 (1.8)
	 * @return A boolean value.
	 */
	private boolean canSupport(int version) {
		return Integer.parseInt(Bukkit.getBukkitVersion()
			 .split("-")[0]
			 .split("\\.")[1]) >= version;
	}
}
