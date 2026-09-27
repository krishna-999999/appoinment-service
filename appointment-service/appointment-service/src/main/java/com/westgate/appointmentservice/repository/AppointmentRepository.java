package com.westgate.appointmentservice.repository;

import com.westgate.appointmentservice.entity.Appointment;
import com.westgate.appointmentservice.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("""
			    SELECT COUNT(a) > 0
			    FROM Appointment a
			    WHERE a.providerId = :providerId
			    AND a.status <> :cancelledStatus
			    AND a.startTime < :endTime
			    AND a.endTime > :startTime
			    AND (:appointmentId IS NULL OR a.id <> :appointmentId)
			""")
	boolean existsConflictingAppointment(@Param("providerId") Long providerId,
			@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,
			@Param("appointmentId") Long appointmentId, @Param("cancelledStatus") AppointmentStatus cancelledStatus);

	List<Appointment> findByPatientId(Long patientId);

	List<Appointment> findByProviderId(Long providerId);
}