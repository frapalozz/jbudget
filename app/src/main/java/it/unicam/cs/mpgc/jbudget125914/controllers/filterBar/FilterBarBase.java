package it.unicam.cs.mpgc.jbudget125914.controllers.filterBar;

import it.unicam.cs.mpgc.jbudget125914.controllers.util.ControllerUtil;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ComboBox<FinancialGroup> selectGroup;

    @FXML
    private Label info;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startDate.setValue(service.getFilterManager().getStartDate());
        endDate.setValue(service.getFilterManager().getEndDate());
        selectGroup.setValue(service.getFilterManager().getGroup());

        selectGroup.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(selectGroup.getSelectionModel().isEmpty()) return;
            if(oldValue != newValue) {
                service.getFilterManager().setGroup(newValue);
                service.getFilterManager().setAccounts(new HashSet<>());
                service.getFilterManager().setTags(new HashSet<>());
                service.update();
            }
        });

        service.getChanges().addListener((obs, oldValue, newValue) ->
            Platform.runLater(() -> {
                selectGroup.setValue(service.getFilterManager().getGroup());

                startDate.setValue(service.getFilterManager().getStartDate());
                endDate.setValue(service.getFilterManager().getEndDate());

                if(info.getText().equals("Loading..."))
                    info.setText("Data loaded");
            })
        );
    }

    @FXML
    public void populateAccounts() {
        selectGroup.getItems().clear();
        selectGroup.setValue(service.getFilterManager().getGroup());
        if(service.getFetchManager().getGroups() != null)
            service.getFetchManager().getGroups().forEach(group ->
                selectGroup.getItems().add(group)
            );
    }

    @FXML
    public void setStartDate() {
        service.getFilterManager().setStartDate(startDate.getValue());
        service.update();
    }

    @FXML
    public void setEndDate() {
        service.getFilterManager().setEndDate(endDate.getValue());
        service.update();
    }

    @FXML
    public void openDialog() {
        openDialogBuilder("filterDialog", "Filter");
    }

    protected void openDialogBuilder(String path, String title) {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder(path,title);
    }
}
