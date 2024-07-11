package com.tcin.hackcelrate.hackathon.service;

import com.tcin.hackcelrate.hackathon.Repository.ViolationRepository;
import com.tcin.hackcelrate.hackathon.model.Violation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViolationService {

    @Autowired
    private ViolationRepository violationRepository;

    public List<Violation> getViolationDetails(String vehicleNumber, String chassisNumber) {
        return violationRepository.findByVehicleNumberAndChassisNumber(vehicleNumber, chassisNumber);
    }

    public Optional<Violation> getViolationDetailsByChallan(String challanId) {
        return violationRepository.findByChallanNo(challanId);
    }

    public Violation saveViolation(Violation violation) {
        return violationRepository.save(violation);
    }

    public List<Violation> findAll() {
        return violationRepository.findAll();
    }

    public void deleteById(long id) {
        violationRepository.deleteById(id);
    }
}
