package eu.gigu979.risk.process.test.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * DTO per la rappresentazione del processo. Include i campi di input e i
 * metadati di sistema restituiti dall'API e presenti su DB.
 */
public class ProcessModelDto {

	private Long id;
	private String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate runDate;

	private String status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createTimestamp;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime updateTimestamp;

	// --- Getter e Setter ---

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getRunDate() {
		return runDate;
	}

	public void setRunDate(LocalDate runDate) {
		this.runDate = runDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(LocalDateTime createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public LocalDateTime getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
}