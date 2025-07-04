package it.unicam.cs.mpgc.jbudget125914.view.dialogs;

import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.interfaces.Action;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.FinancialServiceManager;
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
        service.getGeneralManager().getTagDAO().create(tag);
        service.getFilterManager().getGroup().getTags().add(tag);
        service.getGeneralManager().getGroupDAO().update(service.getFilterManager().getGroup());
        service.getFetchManager().updateGroup(service.getGeneralManager(), service.getFilterManager());
        action.execute();
        System.out.println(service.getFilterManager().getGroup().getTags());
    }

}
