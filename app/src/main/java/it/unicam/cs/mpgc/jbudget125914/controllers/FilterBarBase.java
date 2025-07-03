package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import lombok.Getter;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

@Getter
public abstract class FilterBarBase implements Initializable {

    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    @FXML
    private ChoiceBox<FinancialGroup> selectGroup;

    @FXML
    private Label info;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startDate.setValue(service.getStartDate());
        endDate.setValue(service.getEndDate());
        selectGroup.setValue(service.getGroup());

        selectGroup.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null)
                service.setGroup(newValue);
            service.setAccounts(new HashSet<>());
            service.setTags(new HashSet<>());
            if(newValue != null)
                service.update();
        });

        service.getGroupService().findAll().forEach(group -> {
            selectGroup.getItems().add(group);
        });

        service.getChanges().addListener((obs, oldValue, newValue) -> {
            Platform.runLater(() -> {
                selectGroup.getItems().clear();
                selectGroup.setValue(service.getGroup());

                startDate.setValue(service.getStartDate());
                endDate.setValue(service.getEndDate());

                service.getGroupService().findAll().forEach(group -> {
                    selectGroup.getItems().add(group);
                });
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

        openDialogBuilder("filterDialog", "Filter");
    }

    protected void openDialogBuilder(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/"+path+".fxml"));

            Parent root = loader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle(title);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);

            dialog.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
