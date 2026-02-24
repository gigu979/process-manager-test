package eu.gigu979.risk.process.test.suite;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import eu.gigu979.risk.process.test.exception.BusinessError;
import eu.gigu979.risk.process.test.model.ApiResponse;
import eu.gigu979.risk.process.test.model.ProcessRequestDto;
import eu.gigu979.risk.process.test.utils.AssertUtils;
import eu.gigu979.risk.process.test.utils.ClientUtils;
import eu.gigu979.risk.process.test.utils.TestUtils;

public class ProcessApiErrorSuite {

	private CloseableHttpClient httpClient;

	@BeforeEach
	void setUp() {
		httpClient = ClientUtils.createDefaultClient();
	}

	@AfterEach
	void tearDown() throws IOException {
		if (httpClient != null) {
			httpClient.close();
		}
	}

	@Test
	@DisplayName("Test Error: Name Missing (BE-004)")
	void testCreateEvent_NameMissing() throws IOException, ParseException {
		ProcessRequestDto dto = TestUtils.createValidDto();
		dto.setName(null);

		// Utilizzo factory method per eliminare BASE_URL e StringEntity manuale
		HttpPost request = ClientUtils.createPostRequest(dto);

		try (CloseableHttpResponse response = httpClient.execute(request)) {
			ApiResponse<?> body = TestUtils.parseResponse(response);
			AssertUtils.assertBusinessError(response, 422, body, BusinessError.NAME_MISSING);
		}
	}

	@Test
	@DisplayName("Test Error: Date Missing (BE-005)")
	void testCreateEvent_DateMissing() throws IOException, ParseException {
		ProcessRequestDto dto = TestUtils.createValidDto();
		dto.setRunDate(null);

		HttpPost request = ClientUtils.createPostRequest(dto);

		try (CloseableHttpResponse response = httpClient.execute(request)) {
			ApiResponse<?> body = TestUtils.parseResponse(response);
			AssertUtils.assertBusinessError(response, 422, body, BusinessError.DATE_MISSING);
		}
	}

	@Test
	@DisplayName("Test Error: Entity Not Found (BE-002)")
	void testGetEvent_NotFound() throws IOException, ParseException {
		// Utilizzo factory method per la gestione dell'URL della risorsa
		HttpGet request = ClientUtils.createGetRequest("-1");

		try (CloseableHttpResponse response = httpClient.execute(request)) {
			ApiResponse<?> body = TestUtils.parseResponse(response);
			AssertUtils.assertBusinessError(response, 404, body, BusinessError.ENTITY_NOT_FOUND);
		}
	}

	@Test
	@DisplayName("Test Error: Bad Request (BE-003)")
	void testGenericBadRequest() throws IOException, ParseException {
		// Per casi limite come JSON malformato, passiamo direttamente la stringa
		// ClientUtils gestirà la creazione della richiesta con gli header corretti
		HttpPost request = ClientUtils.createPostRequest("{ malformed json }");

		try (CloseableHttpResponse response = httpClient.execute(request)) {
			ApiResponse<?> body = TestUtils.parseResponse(response);
			AssertUtils.assertBusinessError(response, 400, body, BusinessError.BAD_REQUEST);
		}
	}
}