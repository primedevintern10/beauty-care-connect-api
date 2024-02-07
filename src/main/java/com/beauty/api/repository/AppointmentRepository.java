package com.beauty.api.repository;

import com.beauty.api.collection.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    @Query("{ 'branch._id' : ?0 }")
    List<Appointment> findByBranchId(String branchId);
}
