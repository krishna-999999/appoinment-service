package com.westgate.appointmentservice.service;

import com.westgate.appointmentservice.dto.AppointmentRequest;
import com.westgate.appointmentservice.dto.AppointmentResponse;
import com.westgate.appointmentservice.entity.Appointment;
import com.westgate.appointmentservice.entity.AppointmentStatus;
import com.westgate.appointmentservice.exception.AppointmentConflictException;
import com.westgate.appointmentservice.exception.AppointmentNotFoundException;
import com.westgate.appointmentservice.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public AppointmentResponse createAppointment(AppointmentRequest request) {

		validateTime(request);

		boolean conflict = appointmentRepository.existsConflictingAppointment(request.getProviderId(),
				request.getStartTime(), request.getEndTime(), null, AppointmentStatus.CANCELLED);

		if (conflict) {
			throw new AppointmentConflictException();
		}

		Appointment appointment = new Appointment();

		appointment.setPatientId(request.getPatientId());
		appointment.setProviderId(request.getProviderId());
		appointment.setStartTime(request.getStartTime());
		appointment.setEndTime(request.getEndTime());
		appointment.setReason(request.getReason());
		appointment.setStatus(AppointmentStatus.SCHEDULED);

		Appointment saved = appointmentRepository.save(appointment);

		return convertToResponse(saved);
	}

	@Override
	public List<AppointmentResponse> getAllAppointments() {

		return appointmentRepository.findAll().stream().map(this::convertToResponse).toList();
	}

	@Override
	public AppointmentResponse getAppointmentById(Long id) {

		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException(id));

		return convertToResponse(appointment);
	}

	@Override
	public AppointmentResponse updateAppointment(Long id, AppointmentRequest request) {

		validateTime(request);

		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException(id));

		if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
			throw new AppointmentConflictException();
		}

		boolean conflict = appointmentRepository.existsConflictingAppointment(request.getProviderId(),
				request.getStartTime(), request.getEndTime(), id, AppointmentStatus.CANCELLED);

		if (conflict) {
			throw new AppointmentConflictException();
		}

		appointment.setPatientId(request.getPatientId());
		appointment.setProviderId(request.getProviderId());
		appointment.setStartTime(request.getStartTime());
		appointment.setEndTime(request.getEndTime());
		appointment.setReason(request.getReason());

		Appointment updated = appointmentRepository.save(appointment);

		return convertToResponse(updated);
	}

	@Override
	public void cancelAppointment(Long id) {

		Appointment appointment = appointmentRepository.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException(id));

		appointment.setStatus(AppointmentStatus.CANCELLED);

		appointmentRepository.save(appointment);
	}

	@Override
	public List<AppointmentResponse> getAppointmentsByPatient(Long patientId) {

		return appointmentRepository.findByPatientId(patientId).stream().map(this::convertToResponse).toList();
	}

	@Override
	public List<AppointmentResponse> getAppointmentsByProvider(Long providerId) {

		return appointmentRepository.findByProviderId(providerId).stream().map(this::convertToResponse).toList();
	}

	private void validateTime(AppointmentRequest request) {

		if (!request.getEndTime().isAfter(request.getStartTime())) {
			throw new IllegalArgumentException("End time must be after start time");
		}
	}

	private AppointmentResponse convertToResponse(Appointment appointment) {

		return new AppointmentResponse(appointment.getId(), appointment.getPatientId(), appointment.getProviderId(),
				appointment.getStartTime(), appointment.getEndTime(), appointment.getReason(), appointment.getStatus());
	}
}