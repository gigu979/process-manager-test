package eu.gigu979.risk.process.test.exception;

import java.util.ResourceBundle;

public enum BusinessError {

	RUNTIME_ERROR("BE-001", "event.error.runtime"),
	ENTITY_NOT_FOUND("BE-002", "event.error.unprocessable"),
	BAD_REQUEST("BE-003", "event.error.bad.request"),
	NAME_MISSING("BE-004", "event.error.name.missing"),
    DATE_MISSING("BE-005", "event.error.date.missing"),
    PAYLOAD_MISSING("BE-006", "event.error.payload.missing");

	private static final ResourceBundle bundle = ResourceBundle.getBundle("business_errors");
	private final String code;
	private final String messageProperty;

	BusinessError(String code, String messageProperty) {
		this.code = code;
		this.messageProperty = messageProperty;
	}

	public String getCode() {
		return code;
	}

	public String getMessageProperty() {
		return messageProperty;
	}

	public static String getMessage(BusinessError error) {
		return bundle.getString(error.getMessageProperty());
	}
}