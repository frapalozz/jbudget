package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class InfoBlockView implements Initializable {

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @FXML
    private Label amount1;

    @FXML
    private Label amount2;

    @FXML
    private Label amount3;

    private void setAmount1(String amount1) {
        this.amount1.setText(amount1);
    }

    private void setAmount2(String amount2) {
        this.amount2.setText(amount2);
    }

    private void setAmount3(String amount3) {
        this.amount3.setText(amount3);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<FinancialTransaction> transactions = service.getTransactionService().findAll();

        setAmount1(
                service.getTransactionService().getTransactionAmount(LocalDate.now(), FinancialAmount.class, BigDecimal.class, null)
                        .add(service.getAccountService().accountsInitialBalance(FinancialAmount.class, BigDecimal.class)).toString()
        );

        setAmount2(
                transactions
                    .stream()
                    .filter(t -> t.getAmount().signum() > 0)
                    .map(t -> t.getAmount().getAmount())
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .toString()
        );

        setAmount3(
                transactions
                    .stream()
                    .filter(t -> t.getAmount().signum() < 0)
                    .map(t -> t.getAmount().getAmount())
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .toString()
        );
    }
}
