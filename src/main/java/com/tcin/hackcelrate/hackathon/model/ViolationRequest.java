package com.tcin.hackcelrate.hackathon.model;

public class ViolationRequest {

    private final String vehicleNumber;

    private final String chassisNumber;

    public ViolationRequest(String vehicleNumber, String chassisNumber) {
        this.vehicleNumber = vehicleNumber;
        this.chassisNumber = chassisNumber;
    }
    @Override
    public String toString() {
        return "ViolationRequest{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                '}';
    }
}
