package it.unicam.cs.mpgc.jbudget125914.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class FilterBarTransactionsController extends FilterBarBase{

    @FXML
    private void openNewTransactionDialog() {
        openDialogBuilder("NewTransactionDialog", "New Transaction");
    }

    @FXML
    private void openUpdateTransactionDialog() {
        if(getService().getFilterManager().getTransaction() == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/NewTransactionDialog.fxml"));

            Parent root = loader.load();
            TransactionDialogController controller = loader.getController();

            controller.setCategory(getService().getFilterManager().getTransaction().getTags().stream().findFirst().get().getCategory());
            controller.setDescription(getService().getFilterManager().getTransaction().getDescription());
            controller.setAccount(getService().getFilterManager().getTransaction().getAccount());
            controller.setActiveTags(getService().getFilterManager().getTransaction().getTags());
            controller.setDate(getService().getFilterManager().getTransaction().getDate());
            controller.setAmount(getService().getFilterManager().getTransaction().getAmount().getAmount().toString());
            controller.setApplyButton("Update");

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update Transaction");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);

            dialog.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void deleteTransaction() {
        if(getService().getFilterManager().getTransaction() == null) return;

        try {
            getService().getGeneralManager().getTransactionService().delete(getService().getFilterManager().getTransaction());
            getInfo().setText("Transaction deleted.");
        } catch (Exception e) {
            getInfo().setText("Error while deleting Transaction.");
        }
        getService().getFilterManager().setTransaction(null);
        getService().update();
    }

}
