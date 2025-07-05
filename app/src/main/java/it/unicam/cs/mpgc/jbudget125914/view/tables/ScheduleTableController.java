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

import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialSchedule;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller for ScheduleTable.fxml view
 */
public class ScheduleTableController extends BaseTableController<FinancialSchedule> implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getTable().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                getService().getFilterManager().setSchedule(newValue);
                System.out.println(getService().getFilterManager().getSchedule());
            }
        );

        getService().getChanges().addListener((observable, oldValue, newValue) -> {
            getTable().getItems().clear();
            tableSetter();
        });

        buildTable("DESCRIPTION", "AMOUNT", "DUE DATE", "ACCOUNT", "TAGS");
    }

    private void tableSetter() {
        if(getService().getFetchManager().getSchedules() != null)
            getService().getFetchManager().getSchedules().forEach(t -> getTable().getItems().add(t));
    }
}
