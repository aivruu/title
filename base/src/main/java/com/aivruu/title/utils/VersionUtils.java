package com.aivruu.title.utils;

import org.bukkit.Bukkit;

public class VersionUtils {
	private static final int VERSION = Integer.parseInt(Bukkit.getBukkitVersion()
		.split("-")[0]
		.split("\\.")[1]);
	
	public static boolean equalsOrHigher(final int targetVersion) {
		return VERSION >= targetVersion;
	}
}
