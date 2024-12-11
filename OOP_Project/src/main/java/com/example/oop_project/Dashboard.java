package com.example.oop_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Dashboard extends Application {
    private VehicleManager vehicleManager = new VehicleManager();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("University Transport System");

        Button btnLogEntry = new Button("Log Vehicle Entry");
        Button btnLogExit = new Button("Log Vehicle Exit");
        Button btnViewLogs = new Button("View All Logs");
        Button btnDailyReport = new Button("Generate Daily Report");
        Button btnDurationReport = new Button("Generate Duration Report");
        Button btnExit = new Button("Exit");

        btnLogEntry.setOnAction(e -> openLogEntryWindow());
        btnLogExit.setOnAction(e -> openLogExitWindow());
        btnViewLogs.setOnAction(e -> openViewLogsWindow());
        btnDailyReport.setOnAction(e -> openDailyReportWindow());
        btnDurationReport.setOnAction(e -> openDurationReportWindow());
        btnExit.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(10, btnLogEntry, btnLogExit, btnViewLogs, btnDailyReport, btnDurationReport, btnExit);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #f0f4f8; -fx-spacing: 15;");

        primaryStage.setScene(new Scene(layout, 400, 300));
        primaryStage.show();
    }

    private void openLogEntryWindow() {
        Stage stage = new Stage();
        stage.setTitle("Log Vehicle Entry");

        Label lblVehicleType = new Label("Vehicle Type:");
        ComboBox<String> cbVehicleType = new ComboBox<>();
        cbVehicleType.getItems().addAll("University Bus", "Faculty Vehicle", "Other");

        Label lblRegNumber = new Label("Registration Number:");
        TextField tfRegNumber = new TextField();

        Label lblCNIC = new Label("Driver CNIC:");
        TextField tfCNIC = new TextField();
        lblCNIC.setVisible(false);
        tfCNIC.setVisible(false);

        cbVehicleType.setOnAction(e -> {
            if ("Other".equals(cbVehicleType.getValue())) {
                lblCNIC.setVisible(true);
                tfCNIC.setVisible(true);
            } else {
                lblCNIC.setVisible(false);
                tfCNIC.setVisible(false);
            }
        });

        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> {
            String type = cbVehicleType.getValue();
            String regNumber = tfRegNumber.getText();
            String cnic = tfCNIC.getText();
            vehicleManager.logEntry(type, regNumber, "Other".equals(type) ? cnic : null);
            stage.close();
        });

        VBox layout = new VBox(10, lblVehicleType, cbVehicleType, lblRegNumber, tfRegNumber, lblCNIC, tfCNIC, btnSubmit);
        layout.setStyle("-fx-padding: 20; -fx-spacing: 10;");

        stage.setScene(new Scene(layout, 300, 250));
        stage.show();
    }

    private void openLogExitWindow() {
        Stage stage = new Stage();
        stage.setTitle("Log Vehicle Exit");

        Label lblRegNumber = new Label("Registration Number:");
        TextField tfRegNumber = new TextField();

        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> {
            String regNumber = tfRegNumber.getText();
            vehicleManager.logExit(regNumber);
            stage.close();
        });

        VBox layout = new VBox(10, lblRegNumber, tfRegNumber, btnSubmit);
        layout.setStyle("-fx-padding: 20; -fx-spacing: 10;");

        stage.setScene(new Scene(layout, 300, 150));
        stage.show();
    }

    private void openViewLogsWindow() {
        Stage stage = new Stage();
        stage.setTitle("All Logs");

        TextArea logsArea = new TextArea(vehicleManager.getAllLogs());
        logsArea.setEditable(false);

        VBox layout = new VBox(10, logsArea);
        layout.setStyle("-fx-padding: 20;");

        stage.setScene(new Scene(layout, 500, 400));
        stage.show();
    }

    private void openDailyReportWindow() {
        Stage stage = new Stage();
        stage.setTitle("Daily Report");

        Label lblDate = new Label("Date (YYYY-MM-DD):");
        TextField tfDate = new TextField();

        Button btnGenerate = new Button("Generate");
        btnGenerate.setOnAction(e -> {
            String date = tfDate.getText();
            String report = vehicleManager.getDailyReport(date);
            vehicleManager.saveReportToFile("DailyReport_" + date + ".txt", report);
            showReportWindow("Daily Report", report);
            stage.close();
        });

        VBox layout = new VBox(10, lblDate, tfDate, btnGenerate);
        layout.setStyle("-fx-padding: 20; -fx-spacing: 10;");

        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void openDurationReportWindow() {
        Stage stage = new Stage();
        stage.setTitle("Duration Report");

        Label lblStartDate = new Label("Start Date (YYYY-MM-DD):");
        TextField tfStartDate = new TextField();

        Label lblEndDate = new Label("End Date (YYYY-MM-DD):");
        TextField tfEndDate = new TextField();

        Button btnGenerate = new Button("Generate");
        btnGenerate.setOnAction(e -> {
            String startDate = tfStartDate.getText();
            String endDate = tfEndDate.getText();
            String report = vehicleManager.getDurationReport(startDate, endDate);
            vehicleManager.saveReportToFile("DurationReport_" + startDate + "_to_" + endDate + ".txt", report);
            showReportWindow("Duration Report", report);
            stage.close();
        });

        VBox layout = new VBox(10, lblStartDate, tfStartDate, lblEndDate, tfEndDate, btnGenerate);
        layout.setStyle("-fx-padding: 20; -fx-spacing: 10;");

        stage.setScene(new Scene(layout, 350, 250));
        stage.show();
    }

    private void showReportWindow(String title, String content) {
        Stage stage = new Stage();
        stage.setTitle(title);

        TextArea reportArea = new TextArea(content);
        reportArea.setEditable(false);

        VBox layout = new VBox(10, reportArea);
        layout.setStyle("-fx-padding: 20;");

        stage.setScene(new Scene(layout, 500, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



