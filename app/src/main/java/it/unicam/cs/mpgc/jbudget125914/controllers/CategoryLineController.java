package it.unicam.cs.mpgc.jbudget125914.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CategoryLineController {

    @FXML
    private Label categoryName;

    @FXML
    private Label amount;

    public void setLabel(String label) {
        this.categoryName.setText(label);
    }

    public void setAmount(String amount) {
        this.amount.setText(amount);
    }
}
