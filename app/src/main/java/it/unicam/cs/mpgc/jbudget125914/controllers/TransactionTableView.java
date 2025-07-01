package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.services.TransactionService;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;

public class TransactionTableView implements Initializable {

    private TransactionService<FinancialTransaction, FinancialAccount, FinancialTag, BigDecimal, LocalDate, FinancialAmount> controller;

    @FXML
    private TableView<FinancialTransaction> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new TransactionService<>(FinancialTransaction.class);

        TableColumn<FinancialTransaction, String> description = new TableColumn<>("DESCRIPTION");
        TableColumn<FinancialTransaction, BigDecimal> amount = new TableColumn<>("AMOUNT");
        TableColumn<FinancialTransaction, BigDecimal> date = new TableColumn<>("DATE");
        TableColumn<FinancialTransaction, FinancialAccount> account = new TableColumn<>("ACCOUNT");
        TableColumn<FinancialTransaction, Set<Tag>> tags = new TableColumn<>("TAGS");

        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        tags.setCellValueFactory(new PropertyValueFactory<>("tags"));

        table.getColumns().addAll(description, amount, date, account, tags);

        controller.findAll().forEach(t -> table.getItems().add(t));
    }
}
