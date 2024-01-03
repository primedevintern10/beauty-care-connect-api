package com.beauty.api.serviceImple;

import com.beauty.api.collection.Appointment;
import com.beauty.api.repository.AppointmentRepository;
import com.beauty.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public String save(Appointment appointment) {
        return appointmentRepository.save(appointment).get_id();
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(String appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public void delete(String appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public Appointment update(Appointment appointment, String appointmentId) {
        Appointment existingAppointmentData = appointmentRepository.findById(appointmentId).orElse(null);

        if (existingAppointmentData != null) {
            existingAppointmentData.setClient(appointment.getClient());
            existingAppointmentData.setCompany(appointment.getCompany());
            existingAppointmentData.setBranch(appointment.getBranch());
            existingAppointmentData.setServices(appointment.getServices());
            existingAppointmentData.setDate(appointment.getDate());
            existingAppointmentData.setStartTime(appointment.getStartTime());
            existingAppointmentData.setEndTime(appointment.getEndTime());
            existingAppointmentData.setAssignee(appointment.getAssignee());
            existingAppointmentData.setStatus(appointment.getStatus());
            existingAppointmentData.setReviews(appointment.getReviews());
            existingAppointmentData.setNote(appointment.getNote());

            return appointmentRepository.save(existingAppointmentData);
        } else {
            return null;
        }
    }
}
