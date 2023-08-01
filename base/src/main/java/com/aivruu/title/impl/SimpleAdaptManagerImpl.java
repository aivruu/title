package com.aivruu.title.impl;

import com.aivruu.title.Constants;
import com.aivruu.title.adapt.ServerAdaptModel;
import com.aivruu.title.model.AdaptManager;
import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class SimpleAdaptManagerImpl implements AdaptManager {
	private ServerAdaptModel adapt;
	
	@Override
	public boolean of(final @NotNull ServerAdaptModel adapt) {
		if (Constants.SERVER_VERSION > 12) return false;
		
		this.adapt = Preconditions.checkNotNull(adapt, "ServerAdaptModel impl cannot be null.");
		return true;
	}
	
	@Override
	public boolean find() {
		if (Constants.SERVER_VERSION > 12) return false;
		
		final String packageName = Bukkit.getServer().getClass().getPackage().getName();
		final String version = packageName.substring(packageName.lastIndexOf('.') + 1);
		
		Class<?> clazz;
		try {
			clazz = Class.forName("com.aivruu.title.adapt." + version + ".AdaptHandler");
			if (!ServerAdaptModel.class.isAssignableFrom(clazz)) {
				clazz = null;
			}
			
			adapt = (ServerAdaptModel) clazz.getConstructor().newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InstantiationException |
		         InvocationTargetException exception) {
			clazz = null;
		}
		
		return clazz != null;
	}
	
	@Override
	public @NotNull ServerAdaptModel get() {
		if (adapt == null) {
			throw new IllegalStateException("The adapt to get wasn't installed for this server version.");
		}
		
		return adapt;
	}
}
