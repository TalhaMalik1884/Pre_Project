package com.example.oop_project;

public class FacultyVehicle extends Vehicle {
    private String facultyName;
    private String department;

    public FacultyVehicle(String registrationNumber, String facultyName, String department) {
        super(registrationNumber);
        this.facultyName = facultyName;
        this.department = department;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getVehicleDetails() {
        return "Faculty Vehicle - Registration: " + registrationNumber +
                ", Faculty Name: " + facultyName +
                ", Department: " + department;
    }
}

