package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TransactionDialogController implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private TextField amount;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<FinancialAccount> account;
    @FXML
    private ChoiceBox<FinancialCategory> category;
    @FXML
    private ChoiceBox<FinancialTag> tag;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amount.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if(newText.matches("-?\\d*\\.?\\d*"))
                return change;
            return null;
        }));
        account.getItems().addAll(service.getGroup().getAccounts());
        category.getItems().addAll(service.getGroup().getCategories());
        tag.getItems().addAll(service.getGroup().getTags());
    }

    @FXML
    private void apply() {

    }

    public void setDescription(String description) {
        this.description.setText(description);
    }
    public void setAmount(String amount) {
        this.amount.setText(amount);
    }
    public void setDate(LocalDate date) {
        this.date.setValue(date);
    }
    public void setAccount(FinancialAccount account) {
        this.account.getItems().add(account);
        this.account.setValue(account);
    }
    public void setCategory(FinancialCategory category) {
        this.category.getItems().add(category);
        this.category.setValue(category);
    }
    public void setTag(FinancialTag tag) {
        this.tag.getItems().add(tag);
        this.tag.setValue(tag);
    }

}
