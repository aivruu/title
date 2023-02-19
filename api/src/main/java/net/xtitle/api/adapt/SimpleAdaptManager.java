package net.xtitle.api.adapt;

import com.google.common.base.Preconditions;
import net.xtitle.api.XTitle;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.logging.Logger;

public class SimpleAdaptManager implements AdaptManager {
	private final Logger logger;
	
	private SimpleAdapt currentAdapt;
	
	public SimpleAdaptManager(Logger logger) {
		this.logger = Objects.requireNonNull(logger, "The Logger object from plugin cannot be null.");
	}
	
	@Override
	public AdaptManager adapt(SimpleAdapt adapt) {
		Preconditions.checkNotNull(adapt, "The adapt to set can't be null, please specify a valid adapt.");
		
		// If the server version is equals or higher to 1.13, the adapt is not necessary and will return null.
		if (XTitle.canSupport(13)) return null;
		
		currentAdapt = adapt;
		return this;
	}
	
	@Override
	public AdaptManager findAdapt() {
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
}
