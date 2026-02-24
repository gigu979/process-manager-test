package eu.gigu979.risk.process.test.utils;

import java.util.ResourceBundle;

/**
 * Utility per la gestione centralizzata delle configurazioni. Carica i dati da
 * config.properties e query.properties senza valori hardcoded.
 */
public final class ConfigUtils {

	private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle.getBundle("config");
	private static final ResourceBundle QUERY_BUNDLE = ResourceBundle.getBundle("query");

	// Chiavi API
	private static final String KEY_API_HOST = "api.host";
	private static final String KEY_API_PATH = "api.path";
	private static final String KEY_HTTP_MAX_CONN = "http.max.conn";
	private static final String KEY_HTTP_TIMEOUT = "http.timeout.ms";

	// Chiavi JDBC Teradata
	private static final String KEY_JDBC_URL = "jdbc.url";
	private static final String KEY_JDBC_USER = "jdbc.user";
	private static final String KEY_JDBC_PASS = "jdbc.pass";
	private static final String KEY_JDBC_DRIVER = "jdbc.driver";

	private ConfigUtils() {
		// Prevent instantiation
	}

	// --- Metodi API ---

	public static String getHost() {
		return CONFIG_BUNDLE.getString(KEY_API_HOST);
	}

	public static String getApiPath() {
		return CONFIG_BUNDLE.getString(KEY_API_PATH);
	}

	public static String getFullUrl() {
		return getHost() + getApiPath();
	}

	public static int getMaxConnections() {
		return Integer.parseInt(CONFIG_BUNDLE.getString(KEY_HTTP_MAX_CONN));
	}

	public static int getTimeout() {
		return Integer.parseInt(CONFIG_BUNDLE.getString(KEY_HTTP_TIMEOUT));
	}

	// --- Metodi JDBC (Spring JDBC) ---

	public static String getJdbcUrl() {
		return CONFIG_BUNDLE.getString(KEY_JDBC_URL);
	}

	public static String getJdbcUser() {
		return CONFIG_BUNDLE.getString(KEY_JDBC_USER);
	}

	public static String getJdbcPass() {
		return CONFIG_BUNDLE.getString(KEY_JDBC_PASS);
	}

	public static String getJdbcDriver() {
		return CONFIG_BUNDLE.getString(KEY_JDBC_DRIVER);
	}

	// --- Metodo Query ---

	/**
	 * Recupera una query SQL dal file query.properties. * @param key La chiave
	 * della query
	 * 
	 * @return La stringa SQL
	 */
	public static String getQuery(String key) {
		return QUERY_BUNDLE.getString(key);
	}
}