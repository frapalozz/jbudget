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

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Recurrence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import lombok.Getter;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * This controller is for NewRecurrentTransactionDialog.fxml view
 */
@Getter
public class RecurrenceTransactionDialogController extends BaseNewTransactionController<FinancialTransaction> implements Initializable {

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private ChoiceBox<Recurrence> recurrence;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recurrence.getItems().addAll(Recurrence.values());
        super.initialize(url, resourceBundle);
    }

    /**
     * Generate recurrent transaction
     */
    @FXML
    private void apply() {
        applyRecurrenceChanges();
        getService().update();
    }

    private void applyRecurrenceChanges() {
        Set<FinancialTag> tags = getTags();
        if(super.sanityCheck(tags) || sanityCheckRecurrence()) return;

        FinancialTransaction transaction = new FinancialTransaction(
                new FinancialAmount(BigDecimal.valueOf(Double.parseDouble(getAmount().getText()))),
                startDate.getValue(),
                getDescription().getText(),
                getAccount().getValue(),
                tags
        );
        getService().getDaoManager().getTransactionDAO().createRecurrenceTransactions(
                startDate.getValue(),
                endDate.getValue(),
                recurrence.getValue(),
                transaction,
                this::cloneTransaction
        );
        getStage().close();
    }

    private FinancialTransaction cloneTransaction(FinancialTransaction transaction) {
        FinancialTransaction transactionClone = new FinancialTransaction();
        transactionClone.setAmount(transaction.getAmount());
        transactionClone.setDescription(transaction.getDescription());
        transactionClone.setAccount(transaction.getAccount());
        transactionClone.setDate(transaction.getDate());
        transactionClone.setTags(transaction.getTags());
        return transactionClone;
    }

    private boolean sanityCheckRecurrence() {
        if(startDate.getValue() == null) {
            alertBuilder("start date not valid");
            return true;
        }
        if(endDate.getValue() == null) {
            alertBuilder("end date not valid");
            return true;
        }
        if(startDate.getValue().isAfter(endDate.getValue())) {
            alertBuilder("start date after end date");
            return true;
        }
        if(recurrence.getValue() == null) {
            alertBuilder("recurrence not valid");
            return true;
        }
        return false;
    }
}
