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

package it.unicam.cs.mpgc.jbudget125914.view.filterBar;

import it.unicam.cs.mpgc.jbudget125914.view.dialogs.TransactionDialogController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

/**
 * This class is the controller of FilterBarTransactions
 */
public class FilterBarTransactionsController extends FilterBarBase {

    /**
     * New transaction event handler
     */
    @FXML
    private void openNewTransactionDialog() {
        if(getInfo().getText().equals("Loading...")) return;
        openDialogBuilder("dialogs/NewTransactionDialog", "New Transaction");
    }

    /**
     * update transaction event handler
     */
    @FXML
    private void openUpdateTransactionDialog() {
        if(getService().getFilterManager().getTransaction() == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/dialogs/NewTransactionDialog.fxml"));

            Parent root = loader.load();
            TransactionDialogController controller = loader.getController();
            setData(controller);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update Transaction");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);
            controller.setStage((Stage) dialog.getDialogPane().getScene().getWindow());

            dialog.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete transaction event handler
     */
    @FXML
    private void deleteTransaction() {
        if(getService().getFilterManager().getTransaction() == null) return;

        try {
            getService().getGeneralManager().getTransactionDAO().delete(getService().getFilterManager().getTransaction().getTransactionId());
            getInfo().setText("Transaction deleted.");
        } catch (Exception e) {
            getInfo().setText("Error while deleting Transaction.");
        }
        getService().getFilterManager().setTransaction(null);
        getService().update();
    }

    private void setData(TransactionDialogController controller) {
        if(!getService().getFilterManager().getTransaction().getTags().isEmpty())
            controller.setCategory(getService().getFilterManager().getTransaction().getTags().stream().findFirst().get().getCategory());
        controller.setDescription(getService().getFilterManager().getTransaction().getDescription());
        controller.setAccount(getService().getFilterManager().getTransaction().getAccount());
        controller.setActiveTags(getService().getFilterManager().getTransaction().getTags());
        controller.setDate(getService().getFilterManager().getTransaction().getDate());
        controller.setAmount(getService().getFilterManager().getTransaction().getAmount().getAmount().toString());
        controller.setApplyButton("Update");
    }
}
