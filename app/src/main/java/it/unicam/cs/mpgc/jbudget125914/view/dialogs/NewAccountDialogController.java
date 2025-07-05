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

import it.unicam.cs.mpgc.jbudget125914.view.util.ControllerUtil;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller for NewAccountDialog.fxml view
 */
public class NewAccountDialogController extends BaseDialog implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField initialAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.formatAmount(initialAmount);
    }

    /**
     * Generate new account
     */
    @FXML
    public void apply() {
        if(sanityCheck())
            return;
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(initialAmount.getText()));
        FinancialAccount account = new FinancialAccount(
                name.getText(),
                new FinancialAmount(value),
                getService().getFilterManager().getGroup()
                );

        getService()
                .getFilterManager()
                .getGroup()
                .getAccounts()
                .add(getService()
                        .getGeneralManager()
                        .getAccountDAO()
                        .create(account));
        getService().getFetchManager().updateGroup(getService().getGeneralManager(), getService().getFilterManager());
        getStage().close();
        alertBuilder("Now select the new account");
    }

    private boolean sanityCheck() {
        if(name.getText().isEmpty()) {
            alertBuilder("Please enter a name");
            return true;
        }

        if(initialAmount.getText().isEmpty() || initialAmount.getText().contains("-")) {
            alertBuilder("Initial amount not valid");
            return true;
        }
        return false;
    }
}
