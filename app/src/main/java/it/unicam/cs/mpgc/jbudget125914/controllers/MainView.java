package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class MainView implements Initializable {

    private FinancialServiceManager service;

    @FXML
    private VBox main1;

    @FXML
    private Tab dashboard;
    @FXML
    private Tab transactions;
    @FXML
    private Tab scheduler;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = FinancialServiceManager.getInstance();
        service.setGroupId(1L);
        service.setAccounts(new HashSet<>());
        service.setTags(new HashSet<>());
        service.setEndDate(LocalDate.now());
        service.setStartDate(LocalDate.now().minusDays(90));
        fetchStartData();

        dashboard.setOnSelectionChanged(event -> {if(dashboard.isSelected()) generateDashboard();});

        transactions.setOnSelectionChanged(event -> {if(transactions.isSelected()) generateTransactions();});

        scheduler.setOnSelectionChanged(event -> {if(scheduler.isSelected()) generateScheduler();});

        generateDashboard();
    }

    private void fetchStartData() {
        service.setAccounts(new HashSet<>());
        service.setTags(new HashSet<>());
        service.update();
    }

    @FXML
    public void generateDashboard() {

        buildBlock("InfoBlock");
        addHorizontalDivider();
        buildBlock("FilterBar");
        addHorizontalDivider();
        buildBlock("LineChart");
        addHorizontalDivider();
        buildBlock("CategoryBlock");
    }

    @FXML
    private void generateTransactions() {

    }

    @FXML
    private void generateScheduler() {

    }

    private void addHorizontalDivider() {
        Pane pane = new Pane();
        pane.setPrefWidth(Double.MAX_VALUE);
        pane.setMaxWidth(Double.MAX_VALUE);
        pane.setPrefHeight(1);
        pane.setMinHeight(1);
        pane.setStyle("-fx-background-color: white;");
        pane.setOpacity(0.25);
        main1.getChildren().add(pane);
    }

    private void buildBlock(String filePath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/"+filePath+".fxml"));
        try {
            Parent root = loader.load();
            main1.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
