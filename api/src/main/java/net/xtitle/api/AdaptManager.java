package net.xtitle.api;

import net.xtitle.api.adapt.SimpleAdapt;

public interface AdaptManager {
	/**
	 * Set up the adapt correspond to use.
	 *
	 * @param adapt The adapt for the server version that will be use.
	 */
	void adapt(SimpleAdapt adapt);
	
	/**
	 * Search automatically the adapt correspond to the server version for use.
	 */
	void findAdapt();
	
	/**
	 * Returns the current SimpleData object. This will be return null if there not set an adapt.
	 *
	 * @return A SimpleAdapt object or null.
	 */
	SimpleAdapt getAdapt();
}
