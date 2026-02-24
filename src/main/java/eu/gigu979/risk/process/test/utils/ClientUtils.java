package eu.gigu979.risk.process.test.utils;

import java.io.IOException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * The Class ClientUtils.
 */
public final class ClientUtils {

	private ClientUtils() {
		// Prevent instantiation
	}

	/**
	 * Creates the default client.
	 */
	public static CloseableHttpClient createDefaultClient() {
		int timeout = ConfigUtils.getTimeout();
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout).build();
		return HttpClients.custom()
				.setDefaultRequestConfig(config)
				.setMaxConnTotal(ConfigUtils.getMaxConnections()).build();
	}

	/**
	 * Creates a pre-configured HttpPost with JSON entity.
	 * @throws IOException 
	 */
	public static HttpPost createPostRequest(Object body) throws IOException {
		HttpPost request = new HttpPost(ConfigUtils.getFullUrl());
		String json = TestUtils.toJson(body);
		request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
		return request;
	}

	/**
	 * Creates a pre-configured HttpGet for a specific resource ID.
	 */
	public static HttpGet createGetRequest(String suffix) {
		String url = ConfigUtils.getFullUrl() + (suffix.startsWith("/") ? suffix : "/" + suffix);
		return new HttpGet(url);
	}
}