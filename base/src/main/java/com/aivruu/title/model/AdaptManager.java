package com.aivruu.title.model;

import com.aivruu.title.adapt.ServerAdaptModel;
import org.jetbrains.annotations.NotNull;

public interface AdaptManager {
	/**
	 * Set up the adapt correspond for use.
	 *
	 * @param adapt The adapt for the server version.
	 *
	 * @return True if these adapt were set successful, else will return false.
	 */
	boolean of(final @NotNull ServerAdaptModel adapt);
	
	/**
	 * Search automatically the adapt correspond for the server version to use.
	 *
	 * @return True if the adapt were installed successful, else will return false.
	 */
	boolean find();
	
	/**
	 * Returns a ServerAdaptModel object .
	 *
	 * @return The ServerAdaptModel object, or an exception if the adapt isn't installed (maybe not necessary).
	 */
	@NotNull ServerAdaptModel get();
}
