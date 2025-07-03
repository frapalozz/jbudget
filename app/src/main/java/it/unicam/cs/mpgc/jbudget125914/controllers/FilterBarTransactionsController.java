package it.unicam.cs.mpgc.jbudget125914.controllers;

import javafx.fxml.FXML;

public class FilterBarTransactionsController extends FilterBarBase{

    @FXML
    private void openNewTransactionDialog() {
        openDialogBuilder("NewTransactionDialog", "New Transaction");
    }

    @FXML
    private void openUpdateTransactionDialog() {
        openDialogBuilder("UpdateTransactionDialog", "Update Transaction");
    }

    @FXML
    private void deleteTransaction() {
        if(getService().getTransaction() == null) return;

        try {
            getService().getTransactionService().delete(getService().getTransaction());
            getInfo().setText("Transaction deleted.");
        } catch (Exception e) {
            getInfo().setText("Error while deleting Transaction.");
        }
        getService().setTransaction(null);
        getService().update();
    }

}
