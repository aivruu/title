package net.xtitle.api.adapt;

import com.google.common.base.Preconditions;
import net.xtitle.api.XTitle;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;

public class SimpleAdaptManager implements AdaptManager {
	private SimpleAdapt currentAdapt;
	
	public SimpleAdaptManager() {}
	
	@Override
	public void adapt(SimpleAdapt adapt) {
		Preconditions.checkNotNull(adapt, "The adapt to set can't be null, please specify a valid adapt.");
		
		// If the server version is equals or higher to 1.13, the adapt is not necessary.
		if (XTitle.canSupport(13)) return;
		
		currentAdapt = adapt;
	}
	
	@Override
	public void findAdapt() {
		String packageName = Bukkit.getServer().getClass().getPackage().getName();
		
		try {
			Class<?> clazz = Class.forName("net.xtitle.adapt." + packageName.substring(packageName.lastIndexOf('.') + 1) + ".AdaptHandler");
			if (SimpleAdapt.class.isAssignableFrom(clazz)) currentAdapt = (SimpleAdapt) clazz.getConstructor().newInstance();
		} catch (InvocationTargetException | InstantiationException | NoSuchMethodException | ClassNotFoundException
					| IllegalAccessException exception) {
			Bukkit.getLogger().severe("Cannot found the adapt required for this server version, check if this server version is supported.");
			Bukkit.getLogger().severe("Or check if you've the adapt necessary.");
			exception.printStackTrace();
		}
	}
	
	@Override
	public SimpleAdapt getAdapt() {
		if (currentAdapt == null) throw new IllegalStateException("The adapt for this server isn't established!");
		
		return currentAdapt;
	}
}
