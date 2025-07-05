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

package it.unicam.cs.mpgc.jbudget125914.view.dialogs;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashSet;

/**
 * This class is the controller for FilterDialog.fxml view
 */
public class FilterDialogController extends BaseDialog {
    @FXML
    private ListView<FinancialAccount> accountsList;
    @FXML
    private ListView<FinancialTag> tagsList;
    @FXML
    private Button selectAllAccounts;
    @FXML
    private Button clearAllAccounts;
    @FXML
    private Button selectAllTags;
    @FXML
    private Button clearAllTags;

    private final ObservableList<FinancialAccount> selectedAccounts = FXCollections.observableArrayList();
    private final ObservableList<FinancialTag> selectedTags = FXCollections.observableArrayList();

    /**
     * Initialize the Filter Dialog
     */
    @FXML
    public void initialize() {
        selectionModel();
        populate();
        buttonAction();
    }

    /**
     * Apply filters
     */
    @FXML
    public void apply() {
        getSelectedAccounts();
        getSelectedTags();
        getService().getFilterManager().setTags(new HashSet<>(selectedTags));
        getService().getFilterManager().setAccounts(new HashSet<>(selectedAccounts));
        getService().update();
        getStage().close();
    }

    private void selectionModel() {
        accountsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tagsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void populate() {
        accountsList.setItems(FXCollections.observableArrayList(
                getService().getFilterManager().getGroup().getAccounts()
        ));

        tagsList.setItems(FXCollections.observableArrayList(
                getService().getFilterManager().getGroup().getTags()
        ));
    }

    private void buttonAction() {
        selectAllAccounts.setOnAction(e -> accountsList.getSelectionModel().selectAll());
        clearAllAccounts.setOnAction(e -> accountsList.getSelectionModel().clearSelection());
        selectAllTags.setOnAction(e -> tagsList.getSelectionModel().selectAll());
        clearAllTags.setOnAction(e -> tagsList.getSelectionModel().clearSelection());
    }

    private void getSelectedAccounts() {
        selectedAccounts.setAll(accountsList.getSelectionModel().getSelectedItems());
    }

    private void getSelectedTags() {
        selectedTags.setAll(tagsList.getSelectionModel().getSelectedItems());
    }

}