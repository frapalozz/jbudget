package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.controllers.util.ControllerUtil;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class NewAccountDialogController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField initialAmount;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.formatAmount(initialAmount);
    }

    @FXML
    public void apply() {
        FinancialAccount account = new FinancialAccount(
                name.getText(),
                new FinancialAmount(BigDecimal.valueOf(Double.parseDouble(initialAmount.getText()))),
                service.getFilterManager().getGroup()
                );
        service.getGeneralManager().getAccountService().create(account);
        service.getFilterManager().getGroup().getAccounts().add(account);
    }
}
