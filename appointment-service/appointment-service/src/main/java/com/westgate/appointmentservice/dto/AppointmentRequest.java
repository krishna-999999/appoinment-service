package com.westgate.appointmentservice.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AppointmentRequest {

	@NotNull(message = "Patient ID is required")
	private Long patientId;

	@NotNull(message = "Provider ID is required")
	private Long providerId;

	@NotNull(message = "Start time is required")
	@Future(message = "Start time must be in the future")
	private LocalDateTime startTime;

	@NotNull(message = "End time is required")
	private LocalDateTime endTime;

	@NotBlank(message = "Reason is required")
	private String reason;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}