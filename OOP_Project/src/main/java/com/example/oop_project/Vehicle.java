package com.example.oop_project;

import java.time.LocalDateTime;

public abstract class Vehicle {
    protected String registrationNumber;
    protected LocalDateTime entryTime;
    protected LocalDateTime exitTime;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        this.entryTime = null;
        this.exitTime = null;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public abstract String getVehicleDetails();
}
