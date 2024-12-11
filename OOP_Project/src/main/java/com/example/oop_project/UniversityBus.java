package com.example.oop_project;

public class UniversityBus extends Vehicle {
    private String driverName;
    private String conductorName;

    public UniversityBus(String registrationNumber, String driverName, String conductorName) {
        super(registrationNumber);
        this.driverName = driverName;
        this.conductorName = conductorName;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getConductorName() {
        return conductorName;
    }

    @Override
    public String getVehicleDetails() {
        return "University Bus - Registration: " + getRegistrationNumber() +
                ", Driver: " + driverName +
                ", Conductor: " + conductorName +
                ", Entry Time: " + getEntryTime() +
                ", Exit Time: " + (getExitTime() != null ? getExitTime() : "Still Inside");
    }
}


