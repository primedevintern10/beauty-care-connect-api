package com.beauty.api.service;

import com.beauty.api.collection.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment save(Appointment appointment);

    List<Appointment> getAllAppointments();

    Optional<Appointment> getAppointmentById(String appointmentId);

    boolean delete(String appointmentId);

    Appointment update(Appointment appointment, String appointmentId);

    List<Appointment> getAppointmentsByBranchId(String branchId);
}
