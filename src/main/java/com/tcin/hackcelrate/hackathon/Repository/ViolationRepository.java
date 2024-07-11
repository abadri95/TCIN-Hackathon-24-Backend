package com.tcin.hackcelrate.hackathon.Repository;

import com.tcin.hackcelrate.hackathon.model.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ViolationRepository extends JpaRepository<Violation, Long> {
    List<Violation> findByVehicleNumberAndChassisNumber(String vehicleNumber, String chassisNumber);

    Optional<Violation> findByChallanNo(String challanId);

}