/**
 * MIT License
 * Copyright (c) 2025 Francesco Palozzi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

/**
 * This class is the controller for ScheduleTable.fxml view
 */
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
        TableColumn<FinancialSchedule, BigDecimal> date = new TableColumn<>("DUE DATE");
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
