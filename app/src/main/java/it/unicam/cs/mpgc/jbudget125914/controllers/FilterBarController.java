package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FilterBarController extends FilterBarBase implements Initializable {


    @FXML
    public void openDialogGroup() {
        openDialogBuilder("NewGroupDialog", "New Group");
    }

    @FXML
    public void deleteGroup() {
        try {
            getService().getGroupService().delete(getService().getGroup());
            getInfo().setText(getService().getGroup().getName()+"'s group has been deleted.");
        } catch (Exception e) {
            getInfo().setText("Error while deleting group.");
        }
        getService().setGroup(getService().getGroupService().findAll().getFirst());
        getService().update();
    }
}
