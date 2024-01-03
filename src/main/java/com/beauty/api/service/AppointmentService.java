package com.beauty.api.service;

import com.beauty.api.collection.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    String save(Appointment appointment);

    List<Appointment> getAllAppointments();

    Optional<Appointment> getAppointmentById(String appointmentId);

    void delete(String appointmentId);

    Appointment update(Appointment appointment, String appointmentId);
}
