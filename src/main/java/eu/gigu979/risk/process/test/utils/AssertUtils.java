package eu.gigu979.risk.process.test.utils;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.jupiter.api.Assertions;

import eu.gigu979.risk.process.test.exception.BusinessError;
import eu.gigu979.risk.process.test.model.ApiResponse;

public final class AssertUtils {

	private AssertUtils() {
	}

	public static void assertBaseResponse(CloseableHttpResponse response, int expectedHttpStatus, ApiResponse<?> body) {
		Assertions.assertNotNull(response, "HTTP response should not be null");

		// In HttpClient 4.x si usa getStatusLine().getStatusCode()
		Assertions.assertEquals(expectedHttpStatus, response.getStatusLine().getStatusCode(), "HTTP status code mismatch");

		Assertions.assertNotNull(body, "Response body should not be null");
		Assertions.assertNotNull(body.getCode(), "Field 'code' is missing in ApiResponse");
		Assertions.assertNotNull(body.getMessage(), "Field 'message' is missing in ApiResponse");
	}

	public static void assertBusinessError(CloseableHttpResponse response, int expectedHttpStatus, ApiResponse<?> body, BusinessError expectedError) {
		assertBaseResponse(response, expectedHttpStatus, body);
		Assertions.assertEquals(expectedError.getCode(), body.getCode(), "Business error code mismatch");
	}

	public static void assertSuccess(CloseableHttpResponse response, ApiResponse<?> body) {
		assertBaseResponse(response, HttpStatus.SC_OK, body);
		Assertions.assertEquals("0", body.getCode(), "Success code should be '0'");
		Assertions.assertEquals("OK", body.getMessage(), "Success message should be 'OK'");
		Assertions.assertNotNull(body.getData(), "Business data should not be null on success");
	}
}