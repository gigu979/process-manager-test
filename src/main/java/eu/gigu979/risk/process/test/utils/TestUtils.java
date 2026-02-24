package eu.gigu979.risk.process.test.utils;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import eu.gigu979.risk.process.test.model.ApiResponse;
import eu.gigu979.risk.process.test.model.ProcessModelDto;

public final class TestUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

	private TestUtils() {
		// Prevent instantiation
	}

	public static String toJson(Object obj) throws IOException {
		try {
			return MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new IOException("Error serializing object to JSON", e);
		}
	}

	/**
	 * Creates a valid DTO with default values. Used as a baseline for tests.
	 */
	public static ProcessModelDto createValidDto() {
		ProcessModelDto dto = new ProcessModelDto();
		dto.setName("TEST_PROCESS");
		dto.setRunDate(LocalDate.now());
		dto.setPriority("HIGH");
		dto.setEngine("V8");
		return dto;
	}

	/**
	 * Extracts and deserializes the ApiResponse from the HTTP response.
	 */
	public static ApiResponse<?> parseResponse(CloseableHttpResponse response) throws IOException {
		String json = EntityUtils.toString(response.getEntity());
		return MAPPER.readValue(json, ApiResponse.class);
	}
}