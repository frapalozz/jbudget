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
import it.unicam.cs.mpgc.jbudget125914.view.BaseController;
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
public class InfoBlockController extends BaseController implements Initializable {

    @FXML
    private Label amount1;
    @FXML
    private Label amount2;
    @FXML
    private Label amount3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getService().getChanges().addListener((obs, oldValue, newValue) ->
            Platform.runLater(() -> {
                String currency = getService().getFilterManager().getGroup() != null ? getService().getFilterManager().getGroup().getCurrency() : "";
                generateCategoryBlocks(currency, getService().getFetchManager().getTransactions());
            })
        );
    }

    private void generateCategoryBlocks(String currency, List<FinancialTransaction> transactions) {
        setAmount1(currency);
        setAmount2(currency, transactions);
        setAmount3(currency, transactions);
    }

    private void setAmount1(String currency) {
        if(getService().getFilterManager().getGroup() == null) {
            this.amount1.setText(currency+" "+0);
        } else {
            this.amount1.setText(currency + " " + (getService().getFetchManager().getBalance() != null ? getService().getFetchManager().getBalance() : "0"));
        }
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
                        .filter(t -> t.getDate().isAfter(getService().getFilterManager().getStartDate().minusDays(1)) &&
                                t.getDate().isBefore(getService().getFilterManager().getEndDate().plusDays(1)))
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
                        .filter(t -> t.getDate().isAfter(getService().getFilterManager().getStartDate().minusDays(1)) &&
                                t.getDate().isBefore(getService().getFilterManager().getEndDate().plusDays(1)))
                        .map(t -> t.getAmount().getAmount())
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }
}
