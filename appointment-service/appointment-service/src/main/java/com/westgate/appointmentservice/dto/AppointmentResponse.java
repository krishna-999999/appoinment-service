package com.westgate.appointmentservice.dto;

import com.westgate.appointmentservice.entity.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentResponse {

	private Long id;
	private Long patientId;
	private Long providerId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String reason;
	private AppointmentStatus status;

	public AppointmentResponse() {
	}

	public AppointmentResponse(Long id, Long patientId, Long providerId, LocalDateTime startTime, LocalDateTime endTime,
			String reason, AppointmentStatus status) {
		this.id = id;
		this.patientId = patientId;
		this.providerId = providerId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reason = reason;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public String getReason() {
		return reason;
	}

	public AppointmentStatus getStatus() {
		return status;
	}
}