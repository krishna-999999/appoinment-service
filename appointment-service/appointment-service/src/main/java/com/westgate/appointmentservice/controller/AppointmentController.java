package com.westgate.appointmentservice.controller;

import com.westgate.appointmentservice.dto.AppointmentRequest;
import com.westgate.appointmentservice.dto.AppointmentResponse;
import com.westgate.appointmentservice.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	private final AppointmentService appointmentService;

	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@PostMapping
	public ResponseEntity<AppointmentResponse> createAppointment(@Valid @RequestBody AppointmentRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(request));
	}

	@GetMapping
	public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {

		return ResponseEntity.ok(appointmentService.getAllAppointments());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {

		return ResponseEntity.ok(appointmentService.getAppointmentById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable Long id,
			@Valid @RequestBody AppointmentRequest request) {

		return ResponseEntity.ok(appointmentService.updateAppointment(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {

		appointmentService.cancelAppointment(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<List<AppointmentResponse>> getAppointmentsByPatient(@PathVariable Long patientId) {

		return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(patientId));
	}

	@GetMapping("/provider/{providerId}")
	public ResponseEntity<List<AppointmentResponse>> getAppointmentsByProvider(@PathVariable Long providerId) {

		return ResponseEntity.ok(appointmentService.getAppointmentsByProvider(providerId));
	}
}