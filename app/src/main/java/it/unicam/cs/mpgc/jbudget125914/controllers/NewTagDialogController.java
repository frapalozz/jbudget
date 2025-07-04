package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.Action;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Setter;

public class NewTagDialogController {

    @FXML
    private TextField name;

    @Setter
    private FinancialCategory category;
    private final FinancialServiceManager service = FinancialServiceManager.getInstance();
    @Setter
    Action action;

    @FXML
    public void apply() {
        FinancialTag tag = new FinancialTag(name.getText(), category);
        service.getGeneralManager().getTagService().create(tag);
        service.getFilterManager().getGroup().getTags().add(tag);
        service.getGeneralManager().getGroupService().update(service.getFilterManager().getGroup());
        action.execute();
    }

}
