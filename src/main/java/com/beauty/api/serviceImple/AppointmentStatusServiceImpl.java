package com.beauty.api.serviceImple;

import com.beauty.api.collection.AppointmentStatus;
import com.beauty.api.repository.AppointmentStatusRepository;
import com.beauty.api.service.AppointmentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentStatusServiceImpl implements AppointmentStatusService {
    @Autowired
    private AppointmentStatusRepository appointmentStatusRepository;

    @Override
    public String save(AppointmentStatus appointmentStatus) {
        return appointmentStatusRepository.save(appointmentStatus).getStatus();
    }

    @Override
    public List<AppointmentStatus> getAllAppointmentStatuses() {
        return appointmentStatusRepository.findAll();
    }

    @Override
    public Optional<AppointmentStatus> getAppointmentStatusById(String appointmentStatusId) {
        return appointmentStatusRepository.findById(appointmentStatusId);
    }

    @Override
    public void delete(String appointmentStatusId) {
        appointmentStatusRepository.deleteById(appointmentStatusId);
    }

    @Override
    public AppointmentStatus update(AppointmentStatus appointmentStatus, String appointmentStatusId) {
        AppointmentStatus existingAppointmentStatus = appointmentStatusRepository.findById(appointmentStatusId).orElse(null);

        if (existingAppointmentStatus != null) {
            existingAppointmentStatus.setStatus(appointmentStatus.getStatus());
            existingAppointmentStatus.setIsEnabled(appointmentStatus.getIsEnabled());

            return appointmentStatusRepository.save(existingAppointmentStatus);
        } else {
            return null;
        }
    }
}
