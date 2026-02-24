package eu.gigu979.risk.process.test.utils;



import java.util.ResourceBundle;

public final class ConfigUtils {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("config");

	// Chiavi delle properties definite come costanti per evitare hardcoding nei metodi
	private static final String KEY_API_HOST = "api.host";
	private static final String KEY_API_PATH = "api.path";
	private static final String KEY_HTTP_MAX_CONN = "http.max.conn";
	private static final String KEY_HTTP_TIMEOUT = "http.timeout.ms";

	private ConfigUtils() {
		// Prevent instantiation
	}

	public static String getHost() {
		return BUNDLE.getString(KEY_API_HOST);
	}

	public static String getApiPath() {
		return BUNDLE.getString(KEY_API_PATH);
	}

	public static String getFullUrl() {
		return getHost() + getApiPath();
	}

	public static int getMaxConnections() {
		return Integer.parseInt(BUNDLE.getString(KEY_HTTP_MAX_CONN));
	}

	public static int getTimeout() {
		return Integer.parseInt(BUNDLE.getString(KEY_HTTP_TIMEOUT));
	}
}