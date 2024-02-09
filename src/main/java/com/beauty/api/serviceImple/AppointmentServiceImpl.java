package com.beauty.api.serviceImple;

import com.beauty.api.collection.Appointment;
import com.beauty.api.models.Review;
import com.beauty.api.repository.AppointmentRepository;
import com.beauty.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    @Override
    public Optional<Appointment> getAppointmentById(String appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public boolean delete(String appointmentId) {
        appointmentRepository.deleteById(appointmentId);
        return false;
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

    @Override
    public List<Appointment> getAppointmentsByBranchId(String branchId) {
        return appointmentRepository.findByBranchId(branchId);
    }

    @Override
    public List<Appointment> getAppointmentsByClientId(String clientId) {
        return appointmentRepository.findByClientId(clientId);
    }

    @Override
    public List<Appointment> getAppointmentsByClientAndStatusId(String clientId, String statusId) {
        return appointmentRepository.findByClientIdAndStatusId(clientId, statusId);
    }

    @Override
    public int calculateTotalRatingForBranch(String branchId) {
        List<Appointment> completedAppointments = appointmentRepository.findByBranchIdAndStatus(branchId, "65917db3c01b79393720710c");
        return completedAppointments.stream()
                .mapToInt(appointment -> appointment.getReviews().stream()
                        .mapToInt(Review::getRating).sum())
                .sum();
    }

    @Override
    public int getTotalCompletedAppointmentsForBranch(String branchId) {
        return appointmentRepository.countByBranchIdAndStatus(branchId, "65917db3c01b79393720710c");
    }
}
