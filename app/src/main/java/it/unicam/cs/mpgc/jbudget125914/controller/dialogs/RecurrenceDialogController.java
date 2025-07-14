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

import it.unicam.cs.mpgc.jbudget125914.controller.util.ControllerUtil;
import javafx.fxml.FXML;

/**
 * This controller is for RecurrenceSelectorDialog.fxml view
 */
public class RecurrenceDialogController extends BaseDialog {

    /**
     * Open new transaction
     */
    @FXML
    public void openNewTransactionDialog() {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder("dialogs/NewTransactionDialog", "New Transaction");
        getStage().close();
    }

    /**
     * Open new recurrent transaction
     */
    @FXML
    public void openNewRecurrentTransactionDialog() {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder("dialogs/NewRecurrentTransactionDialog", "New Recurrent Transaction");
        getStage().close();
    }
}
