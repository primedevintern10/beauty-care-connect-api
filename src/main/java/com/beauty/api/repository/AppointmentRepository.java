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

    @Query("{ 'client._id' : ?0 }")
    List<Appointment> findByClientId(String clientId);

    @Query("{ 'client._id' : ?0, 'status._id' : ?1 }")
    List<Appointment> findByClientIdAndStatusId(String clientId, String statusId);

    @Query("{ 'branch._id' : ?0, 'status._id' : ?1 }")
    List<Appointment> findByBranchIdAndStatus(String branchId, String statusId);

    @Query(value = "{ 'branch._id' : ?0, 'status._id' : ?1 }", count = true)
    int countByBranchIdAndStatus(String branchId, String statusId);

    @Query(value = "{ 'status._id' : ?0 }", count = true)
    long countByStatusId(String statusId);
}
