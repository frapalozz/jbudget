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

package it.unicam.cs.mpgc.jbudget125914.controller.tables;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.controller.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Base table controller for all the tables
 * @param <T> transaction type
 */
@Getter
public class BaseTableController<T> extends BaseController implements Initializable {

    @FXML
    private TableView<T> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     * Build the transaction table with the specified column name
     * @param dateText date text
     */
    protected void buildTable(String dateText) {
        TableColumn<T, String> description = new TableColumn<>("DESCRIPTION");
        TableColumn<T, BigDecimal> amount = new TableColumn<>("AMOUNT");
        TableColumn<T, LocalDate> date = new TableColumn<>(dateText);
        TableColumn<T, FinancialAccount> account = new TableColumn<>("ACCOUNT");
        TableColumn<T, Set<FinancialTag>> tags = new TableColumn<>("TAGS");
        setCells(description, amount, date, account, tags);
    }

    /**
     * Bind the column to the transaction attributes
     * @param description description column
     * @param amount amount column
     * @param date date column
     * @param account account column
     * @param tags tags column
     */
    protected void setCells(
            TableColumn<T,String> description,
            TableColumn<T, BigDecimal> amount,
            TableColumn<T, LocalDate> date,
            TableColumn<T, FinancialAccount> account,
            TableColumn<T, Set<FinancialTag>> tags) {

        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        getTable().getColumns().add(description);
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        getTable().getColumns().add(amount);
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        getTable().getColumns().add(date);
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        getTable().getColumns().add(account);
        tags.setCellValueFactory(new PropertyValueFactory<>("tags"));
        getTable().getColumns().add(tags);
    }
}
