package com.tcin.hackcelrate.hackathon.controller;

import com.tcin.hackcelrate.hackathon.model.Violation;
import com.tcin.hackcelrate.hackathon.model.ViolationRequest;
import com.tcin.hackcelrate.hackathon.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ViolationController {

    @Autowired
    private ViolationService violationService;

    @GetMapping("/fetch-all-violations")
    public List<Violation> getAllTrafficViolations() {
        return violationService.findAll();
    }

    @PostMapping("/add-violation")
    public ResponseEntity<?> addViolation(@RequestBody Violation violation) {
        violation.setChallanDate(LocalDateTime.now());
        Violation savedViolation = violationService.saveViolation(violation);
        return ResponseEntity.ok(savedViolation);
    }

    @GetMapping("/fetch-violation")
    public ResponseEntity<?> getDetails(@RequestParam String vehicleNumber, @RequestParam String chassisNumber) {
        List<Violation> violation = violationService.getViolationDetails(vehicleNumber, chassisNumber);
        if (!violation.isEmpty()) {
            return ResponseEntity.ok(violation);
        } else {
            return ResponseEntity.status(404).body("No details found");
        }
    }

    @GetMapping("/fetch-violation-challan")
    public ResponseEntity<?> getDetails(@RequestParam String challanId) {
        Optional<Violation> trafficViolation = violationService.getViolationDetailsByChallan(challanId);
        if (trafficViolation.isPresent()) {
            return ResponseEntity.ok(trafficViolation.get());
        } else {
            return ResponseEntity.status(404).body("No details found");
        }
    }

    @PutMapping("/update-violation-details")
    public ResponseEntity<Violation> updateTrafficViolation(
            @PathVariable String challanId, @RequestBody Violation trafficViolationDetails) {
        Optional<Violation> trafficViolation = violationService.getViolationDetailsByChallan(challanId);

        if (trafficViolation.isPresent()) {
            Violation violation = trafficViolation.get();
            violation.setViolatorName(trafficViolationDetails.getViolatorName());
            violation.setDlRcNumber(trafficViolationDetails.getDlRcNumber());
            violation.setState(trafficViolationDetails.getState());
            violation.setChallanDate(trafficViolationDetails.getChallanDate());
            violation.setAmount(trafficViolationDetails.getAmount());
            violation.setStatus(trafficViolationDetails.getStatus());
            violation.setPaymentSource(trafficViolationDetails.getPaymentSource());
            violation.setChallanPrint(trafficViolationDetails.getChallanPrint());
            violation.setReceipt(trafficViolationDetails.getReceipt());
            violation.setPayment(trafficViolationDetails.getPayment());
            violation.setPaymentVerify(trafficViolationDetails.getPaymentVerify());
            Violation updatedViolation = violationService.saveViolation(violation);
            return ResponseEntity.ok(updatedViolation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrafficViolation(@PathVariable long id) {
        violationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}