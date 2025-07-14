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

package it.unicam.cs.mpgc.jbudget125914.controller.filterBar;

import it.unicam.cs.mpgc.jbudget125914.controller.BaseController;
import it.unicam.cs.mpgc.jbudget125914.controller.util.ControllerUtil;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Getter;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * FilterBase is an abstract class for all the bar controller
 */
@Getter
public abstract class FilterBarBase extends BaseController implements Initializable {

    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private ComboBox<FinancialGroup> selectGroup;
    @FXML private Label info;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addSelectGroupListener();
        addListener();
    }

    /**
     * Populate the selectGroup ComboBox
     */
    @FXML
    public void populateGroups() {
        selectGroup.getItems().clear();
        selectGroup.setValue(getService().getFilterManager().getGroup());
        if(getService().getFetchManager().getGroups() != null)
            getService().getFetchManager().getGroups().forEach(group -> selectGroup.getItems().add(group));
    }

    /**
     * Set the new Start date
     */
    @FXML
    public void setStartDate() {
        getService().getFilterManager().setStartDate(startDate.getValue());
        getService().update();
    }

    /**
     * Set the new End date
     */
    @FXML
    public void setEndDate() {
        getService().getFilterManager().setEndDate(endDate.getValue());
        getService().update();
    }

    /**
     * Open filter dialog
     */
    @FXML
    public void openDialog() {
        openDialogBuilder("dialogs/FilterDialog", "Filter");
    }

    /**
     * Dialog builder
     * @param path fxml path
     * @param title dialog title
     */
    protected void openDialogBuilder(String path, String title) {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder(path,title);
    }

    private void addSelectGroupListener() {
        selectGroup.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(selectGroup.getSelectionModel().isEmpty()) return;
            if(oldValue != newValue) {
                getService().getFilterManager().setGroup(newValue);
                getService().getFilterManager().setAccounts(new HashSet<>());
                getService().getFilterManager().setTags(new HashSet<>());
                getService().update();
            }
        });
    }

    private void addListener() {
        getService().getChanges().addListener((obs, oldValue, newValue) ->
                Platform.runLater(() -> {
                    selectGroup.setValue(getService().getFilterManager().getGroup());

                    startDate.setValue(getService().getFilterManager().getStartDate());
                    endDate.setValue(getService().getFilterManager().getEndDate());

                    if(info.getText().equals("Loading..."))
                        info.setText("Data loaded");
                })
        );
    }
}
