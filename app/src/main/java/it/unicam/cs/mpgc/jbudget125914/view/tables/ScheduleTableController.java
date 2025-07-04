package it.unicam.cs.mpgc.jbudget125914.view.tables;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialSchedule;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ScheduleTableController implements Initializable {

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @FXML
    private TableView<FinancialSchedule> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                service.getFilterManager().setSchedule(newValue)
        );

        service.getChanges().addListener((observable, oldValue, newValue) -> {
            table.getItems().clear();
            tableSetter();
        });

        tableInitialize();
        tableSetter();
    }

    private void tableSetter() {
        if(service.getFetchManager().getSchedules() != null)
            service.getFetchManager().getSchedules().forEach(t -> table.getItems().add(t));
    }

    private void tableInitialize() {
        TableColumn<FinancialSchedule, String> description = new TableColumn<>("DESCRIPTION");
        TableColumn<FinancialSchedule, BigDecimal> amount = new TableColumn<>("AMOUNT");
        TableColumn<FinancialSchedule, BigDecimal> date = new TableColumn<>("DATE");
        TableColumn<FinancialSchedule, FinancialAccount> account = new TableColumn<>("ACCOUNT");
        TableColumn<FinancialSchedule, Set<FinancialTag>> tags = new TableColumn<>("TAGS");

        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        tags.setCellValueFactory(new PropertyValueFactory<>("tags"));

        table.getColumns().addAll(description, amount, date, account, tags);
    }
}
