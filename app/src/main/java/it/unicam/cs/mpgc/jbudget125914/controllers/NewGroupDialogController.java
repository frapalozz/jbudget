package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class NewGroupDialogController extends Dialog<ButtonType> {

    @FXML
    private TextField groupName;

    @FXML
    private TextField currency;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();


    @FXML
    public void apply() {
        String groupName = this.groupName.getText();
        String currency = this.currency.getText();
        if(groupName.isEmpty() || currency.isEmpty()) {
            return;
        }
        FinancialGroup group = new FinancialGroup(groupName, currency);

        service.getGeneralManager().getGroupService().create(group);
        service.getFilterManager().setGroup(group);
        service.update();
    }
}
