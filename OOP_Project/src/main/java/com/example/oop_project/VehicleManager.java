package com.example.oop_project;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VehicleManager {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private List<LogEntry> logs = new ArrayList<>();

    // Log structure
    private static class LogEntry {
        String type;
        String regNumber;
        String cnic;
        String action; // Entry or Exit
        LocalDateTime timestamp;

        LogEntry(String type, String regNumber, String cnic, String action) {
            this.type = type;
            this.regNumber = regNumber;
            this.cnic = cnic;
            this.action = action;
            this.timestamp = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return String.format("%s - Type: %s, RegNumber: %s%s, Time: %s",
                    action,
                    type,
                    regNumber,
                    (cnic != null ? ", CNIC: " + cnic : ""),
                    timestamp.format(DATE_TIME_FORMATTER));
        }
    }

    public void logEntry(String type, String regNumber, String cnic) {
        logs.add(new LogEntry(type, regNumber, cnic, "Entry"));
    }

    public void logExit(String regNumber) {
        logs.add(new LogEntry("N/A", regNumber, null, "Exit"));
    }

    public String getAllLogs() {
        if (logs.isEmpty()) {
            return "No vehicle logs available.";
        }
        StringBuilder result = new StringBuilder();
        for (LogEntry log : logs) {
            result.append(log).append("\n");
        }
        return result.toString();
    }

    public String getDailyReport(String date) {
        LocalDate queryDate = LocalDate.parse(date, DATE_FORMATTER);
        List<LogEntry> filteredLogs = new ArrayList<>();
        for (LogEntry log : logs) {
            if (log.timestamp.toLocalDate().equals(queryDate)) {
                filteredLogs.add(log);
            }
        }

        if (filteredLogs.isEmpty()) {
            return "No vehicle logs for " + date + ".";
        }

        StringBuilder result = new StringBuilder("Vehicle Logs for " + date + ":\n");
        for (LogEntry log : filteredLogs) {
            result.append(log).append("\n");
        }
        return result.toString();
    }

    public String getDurationReport(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);
        List<LogEntry> filteredLogs = new ArrayList<>();
        for (LogEntry log : logs) {
            LocalDate logDate = log.timestamp.toLocalDate();
            if ((logDate.isEqual(start) || ((java.time.LocalDate) logDate).isAfter(start)) &&
                    (logDate.isEqual(end) || logDate.isBefore(end))) {
                filteredLogs.add(log);
            }
        }

        if (filteredLogs.isEmpty()) {
            return "No vehicle logs between " + startDate + " and " + endDate + ".";
        }

        StringBuilder result = new StringBuilder("Vehicle Logs from " + startDate + " to " + endDate + ":\n");
        for (LogEntry log : filteredLogs) {
            result.append(log).append("\n");
        }
        return result.toString();
    }

    public void saveReportToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

