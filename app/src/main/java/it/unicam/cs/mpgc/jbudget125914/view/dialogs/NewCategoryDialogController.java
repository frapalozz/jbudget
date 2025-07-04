package it.unicam.cs.mpgc.jbudget125914.view.dialogs;

import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.HashSet;

public class NewCategoryDialogController {

    @FXML
    private TextField name;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @FXML
    public void apply() {
        FinancialCategory category = new FinancialCategory(name.getText(), new HashSet<>());
        service.getGeneralManager().getCategoryDAO().create(category);
        service.getFilterManager().getGroup().getCategories().add(category);
        service.getGeneralManager().getGroupDAO().update(service.getFilterManager().getGroup());
        service.getFetchManager().updateGroup(service.getGeneralManager(), service.getFilterManager());
    }
}
