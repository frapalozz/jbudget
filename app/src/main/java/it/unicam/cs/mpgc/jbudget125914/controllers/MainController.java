package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class MainController implements Initializable {

    private FinancialServiceManager service;

    @FXML
    private VBox dashboard;
    @FXML
    private VBox transactions;
    @FXML
    private VBox scheduler;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = FinancialServiceManager.getInstance();
        fetchStartData();

        generateDashboard();
        generateTransactions();
        generateScheduler();
    }

    private void fetchStartData() {
        service.getFilterManager().setGroup(null);
        service.getFilterManager().setEndDate(LocalDate.now());
        service.getFilterManager().setStartDate(LocalDate.now().minusDays(90));
        service.getFilterManager().setAccounts(new HashSet<>());
        service.getFilterManager().setTags(new HashSet<>());
        service.update();
    }

    @FXML
    public void generateDashboard() {

        addHorizontalDivider(dashboard);
        buildBlock("InfoBlock", dashboard);
        addHorizontalDivider(dashboard);
        buildBlock("FilterBar", dashboard);
        addHorizontalDivider(dashboard);
        buildBlock("LineChart", dashboard);
        addHorizontalDivider(dashboard);
        buildBlock("CategoryBlock", dashboard);
    }

    @FXML
    private void generateTransactions() {
        addHorizontalDivider(transactions);
        buildBlock("FilterBarTransactions", transactions);
        addHorizontalDivider(transactions);
        buildBlock("TransactionTable", transactions);
    }

    @FXML
    private void generateScheduler() {

    }

    private void addHorizontalDivider(VBox vbox) {
        Pane pane = new Pane();
        pane.setPrefWidth(Double.MAX_VALUE);
        pane.setMaxWidth(Double.MAX_VALUE);
        pane.setPrefHeight(1);
        pane.setMinHeight(1);
        pane.setStyle("-fx-background-color: white;");
        pane.setOpacity(0.25);
        vbox.getChildren().add(pane);
    }

    private void buildBlock(String filePath, VBox box) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/"+filePath+".fxml"));
        try {
            Parent root = loader.load();
            box.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
