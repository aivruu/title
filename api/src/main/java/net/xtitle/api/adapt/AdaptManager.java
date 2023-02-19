package net.xtitle.api.adapt;

/**
 * Interface model for the AdaptManager that manages the library adapts.
 */
public interface AdaptManager {
	/**
	 * Set up the adapt correspond to use.
	 *
	 * @param adapt The adapt for the server version that will be use.
	 * @return This instance if the adapt was installed or null if was not installed.
	 */
	AdaptManager adapt(SimpleAdapt adapt);
	
	/**
	 * Search automatically the adapt correspond to the server version for use.
	 *
	 * @return This instance if the adapt was installed or null if was not installed.
	 */
	AdaptManager findAdapt();
	
	/**
	 * Returns the current SimpleData object. This will be return null if there not set an adapt.
	 *
	 * @return A SimpleAdapt object or null.
	 */
	SimpleAdapt getAdapt();
}
