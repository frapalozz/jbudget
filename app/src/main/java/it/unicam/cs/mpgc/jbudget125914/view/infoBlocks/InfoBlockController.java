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

package it.unicam.cs.mpgc.jbudget125914.view.infoBlocks;

import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.FinancialServiceManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class is a controller for the InfoBlock.fxml view
 */
public class InfoBlockController implements Initializable {

    @FXML
    private Label amount1;
    @FXML
    private Label amount2;
    @FXML
    private Label amount3;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service.getChanges().addListener((obs, oldValue, newValue) ->
            Platform.runLater(() -> {
                String currency = service.getFilterManager().getGroup() != null ? service.getFilterManager().getGroup().getCurrency() : "";

                generateCategoryBlocks(currency, service.getFetchManager().getTransactions());
            })
        );

        String currency = service.getFilterManager().getGroup() != null ? service.getFilterManager().getGroup().getCurrency() : "";
        generateCategoryBlocks(currency, service.getFetchManager().getTransactions());
    }

    private void generateCategoryBlocks(String currency, List<FinancialTransaction> transactions) {
        setAmount1(currency);
        setAmount2(currency, transactions);
        setAmount3(currency, transactions);
    }

    private void setAmount1(String currency) {
        this.amount1.setText( currency + " " + (service.getFetchManager().getBalance() != null? service.getFetchManager().getBalance() : "0"));
    }

    private void setAmount2(String currency, List<FinancialTransaction> transactions) {
        if(transactions == null) {
            this.amount2.setText("0");
            return;
        }
        this.amount2.setText(
                currency + " " + transactions
                        .stream()
                        .filter(t -> t.getAmount().signum() > 0)
                        .filter(t -> t.getDate().isAfter(service.getFilterManager().getStartDate().minusDays(1)) &&
                                t.getDate().isBefore(service.getFilterManager().getEndDate().plusDays(1)))
                        .map(t -> t.getAmount().getAmount())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    private void setAmount3(String currency, List<FinancialTransaction> transactions) {
        if(transactions == null) {
            this.amount3.setText("0");
            return;
        }
        this.amount3.setText(
                currency + " " + transactions
                        .stream()
                        .filter(t -> t.getAmount().signum() < 0)
                        .filter(t -> t.getDate().isAfter(service.getFilterManager().getStartDate().minusDays(1)) &&
                                t.getDate().isBefore(service.getFilterManager().getEndDate().plusDays(1)))
                        .map(t -> t.getAmount().getAmount())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }
}
