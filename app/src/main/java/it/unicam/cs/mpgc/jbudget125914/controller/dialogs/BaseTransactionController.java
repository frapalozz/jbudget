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

package it.unicam.cs.mpgc.jbudget125914.controller.dialogs;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Base transaction controller dialog
 * @param <T> Transaction type
 */
@Getter
public abstract class BaseTransactionController<
        T extends Transaction<FinancialAmount, LocalDate, FinancialTag, FinancialAccount>
        > extends BaseNewTransactionController<T> implements Initializable {


    @FXML
    private DatePicker date;
    @FXML
    private Button applyButton;

    /**
     * Apply changes
     */
    @FXML
    private void apply() {
        if(getApplyButton().getText().equals("Create")) {
            applyCreate();
        } else {
            applyUpdate();
        }
    }

    /**
     * Set the applyButton text
     * @param text the new text
     */
    public void setApplyButton(String text) {
        getApplyButton().setText(text);
    }

    /**
     * Set the date for the Transaction
     * @param date the new date
     */
    public void setDate(LocalDate date) {
        getDate().setValue(date);
    }



    /**
     * Apply the changes to the Transaction
     * @param transaction the Transaction to change
     */
    @Override
    protected boolean applyChanges(T transaction) {
        if(!super.applyChanges(transaction) || sanityCheckTransaction()) {
            return false;
        }
        transaction.setDate(date.getValue());
        return true;
    }

    private boolean sanityCheckTransaction() {
        if(date.getValue() == null) {
            alertBuilder("Date not valid");
            return true;
        }
        return false;
    }

    /**
     * Create the new Transaction
     */
    protected abstract void applyCreate();

    /**
     * Update the Transaction
     */
    protected abstract void applyUpdate();
}
