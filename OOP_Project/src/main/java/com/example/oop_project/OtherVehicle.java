package com.example.oop_project;

public class OtherVehicle extends Vehicle {
    private String vehicleType;
    private String driverCNIC;

    public OtherVehicle(String registrationNumber, String vehicleType, String driverCNIC) {
        super(registrationNumber);
        this.vehicleType = vehicleType;
        this.driverCNIC = driverCNIC;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getDriverCNIC() {
        return driverCNIC;
    }

    @Override
    public String getVehicleDetails() {
        return "Other Vehicle - Type: " + vehicleType +
                ", Registration: " + getRegistrationNumber() +
                ", Driver CNIC: " + driverCNIC +
                ", Entry Time: " + getEntryTime() +
                ", Exit Time: " + (getExitTime() != null ? getExitTime() : "Still Inside");
    }
}
