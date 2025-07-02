package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashSet;

public class FilterDialogController extends Dialog<ButtonType> {
    @FXML private ListView<FinancialAccount> accountsList;
    @FXML private ListView<FinancialTag> tagsList;
    @FXML private Button selectAllAccounts;
    @FXML private Button clearAllAccounts;
    @FXML private Button selectAllTags;
    @FXML private Button clearAllTags;

    private final ObservableList<FinancialAccount> selectedAccounts = FXCollections.observableArrayList();
    private final ObservableList<FinancialTag> selectedTags = FXCollections.observableArrayList();
    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @FXML
    public void initialize() {
        // Enable multiple selection
        accountsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tagsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Sample data - replace with your actual data
        accountsList.setItems(FXCollections.observableArrayList(
                service.getGroupService().getAccounts(service.getGroupId())
        ));

        tagsList.setItems(FXCollections.observableArrayList(
                service.getGroupService().getTags(service.getGroupId())
        ));

        // Button actions
        selectAllAccounts.setOnAction(e -> accountsList.getSelectionModel().selectAll());
        clearAllAccounts.setOnAction(e -> accountsList.getSelectionModel().clearSelection());
        selectAllTags.setOnAction(e -> tagsList.getSelectionModel().selectAll());
        clearAllTags.setOnAction(e -> tagsList.getSelectionModel().clearSelection());
    }

    public void getSelectedAccounts() {
        selectedAccounts.setAll(accountsList.getSelectionModel().getSelectedItems());
    }

    public void getSelectedTags() {
        selectedTags.setAll(tagsList.getSelectionModel().getSelectedItems());
    }

    @FXML
    public void apply() {
        getSelectedAccounts();
        getSelectedTags();
        service.setTags(new HashSet<>(selectedTags));
        service.setAccounts(new HashSet<>(selectedAccounts));
    }
}