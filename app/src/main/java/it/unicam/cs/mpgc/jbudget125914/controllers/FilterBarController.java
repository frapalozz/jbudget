package it.unicam.cs.mpgc.jbudget125914.controllers;

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
            getService().getGeneralManager().getGroupService().delete(getService().getFilterManager().getGroup());
            getInfo().setText(getService().getFilterManager().getGroup().getName()+"'s group has been deleted.");
        } catch (Exception e) {
            getInfo().setText("Error while deleting group.");
        }
        getService().getFilterManager().setGroup(null);
        getService().update();

    }
}
