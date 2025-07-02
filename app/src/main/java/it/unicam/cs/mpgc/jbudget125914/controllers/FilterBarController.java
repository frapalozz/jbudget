package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class FilterBarController implements Initializable {

    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startDate.setValue(service.getStartDate());
        endDate.setValue(service.getEndDate());
        service.getChanges().addListener((obs, oldValue, newValue) -> {
            Platform.runLater(() -> {
                startDate.setValue(service.getStartDate());
                endDate.setValue(service.getEndDate());
            });
        });
    }

    @FXML
    public void setStartDate() {
        service.setStartDate(startDate.getValue());
        service.update();
    }

    @FXML
    public void setEndDate() {
        service.setEndDate(endDate.getValue());
        service.update();
    }

    @FXML
    public void openDialog() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/filterDialog.fxml"));

            Parent root = loader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Filter");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);
            dialog.showAndWait();
            service.update();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
