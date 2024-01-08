package com.beauty.api.service;

import com.beauty.api.collection.AppointmentStatus;

import java.util.List;
import java.util.Optional;

public interface AppointmentStatusService {
    String save(AppointmentStatus appointmentStatus);

    List<AppointmentStatus> getAllAppointmentStatuses();

    Optional<AppointmentStatus> getAppointmentStatusById(String appointmentStatusId);

    void delete(String appointmentStatusId);

    AppointmentStatus update(AppointmentStatus appointmentStatus, String appointmentStatusId);

}
